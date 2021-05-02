
package acme.features.manager.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Task;
import acme.entities.Word;
import acme.entities.roles.Manager;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {

	@Autowired
	protected ManagerTaskRepository repository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "start", "end", "title", "text", "link", "manager", "visibility");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("start", "");
			model.setAttribute("end", "");
			model.setAttribute("title", "");
			model.setAttribute("text", "");
			model.setAttribute("link", "");
			model.setAttribute("manager", "");
			model.setAttribute("visibility", "");
		} else {
			request.transfer(model, "start", "end", "title", "text", "link", "manager", "visibility");
		}

	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		final Date start = new Date(System.currentTimeMillis() + 1);
		final Date end = new Date(System.currentTimeMillis() + 2);

		final Manager manager = this.repository.findById(request.getPrincipal().getActiveRoleId());

		Task result;
		result = new Task();

		result.setStart(start);
		result.setEnd(end);
		result.setTitle("");
		result.setText("");
		result.setLink("");
		result.setWorkLoad(5.0);
		result.setManager(manager);
		result.setVisibility(false);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Date end = request.getModel().getDate("end");
		final Date start = request.getModel().getDate("start");
		if (end == null || start == null) {
			errors.state(request, !(start == null), "start", "manager.task.error.task-date");
			errors.state(request, !(end == null), "end", "manager.task.error.task-date");
		} else {
			errors.state(request, end.after(start), "end", "manager.task.error.task-dateAfter");
		}

		final int textLength = request.getModel().getString("text").length();
		errors.state(request, textLength > 0 && textLength <= 500, "text", "acme.validation.length", 0, 500);

		final int titleLength = request.getModel().getString("title").length();
		errors.state(request, titleLength >= 1 && titleLength <= 80, "title", "acme.validation.length", 1, 80);

		final List<Word> palabrasSpam = this.repository.spWords();

		final String pal = entity.getTitle().trim();
		final Integer tamA = entity.getTitle().split(" ").length;

		final String pal2 = entity.getText().trim();
		final Integer tamT = entity.getText().split(" ").length;

		Integer acumA = 0;
		Integer acumT = 0;

		for (int i = 0; i < palabrasSpam.size(); i++) {

			if (pal.contains(palabrasSpam.get(i).getWord())) {
				acumA++;
			}
			if (pal2.contains(palabrasSpam.get(i).getWord())) {
				acumT++;
			}

		}
		final double spamA = acumA * 100 / tamA;
		final double spamT = acumT * 100 / tamT;

		final Double umbral = this.repository.findSpam().getThreshold();
		final boolean isShoutSpamA = spamA > umbral;
		final boolean isShoutSpamT = spamT > umbral;
		errors.state(request, !isShoutSpamA, "title", "manager.task.error.task-spam");
		errors.state(request, !isShoutSpamT, "text", "manager.task.error.task-spam");

	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}

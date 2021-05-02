
package acme.features.manager.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Task;
import acme.entities.Word;
import acme.entities.roles.Manager;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {

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

		request.unbind(entity, model, "start", "end", "title", "text", "link", "visibility");

	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		Task result;
		Integer id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOnebyId(id);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

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
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		final Double actualWorkload = entity.getWorkLoad(); 
		final Integer minutes = this.getMinutes(actualWorkload);
		
		entity.setWorkLoad(this.toHours(minutes));
		
		this.repository.save(entity);
		
	}
	
	public Integer getMinutes(final Double workload) {
		final Double parteEntera = Math.floor(workload);
		final Double parteDecimal = workload - parteEntera;

		return (int) (parteEntera * 60 + parteDecimal * 100);
	}

	public Double toHours(final Integer minutes) {
		return minutes / 60 + (double) (minutes % 60) / 100;
	}

}

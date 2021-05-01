
package acme.features.manager.task;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Task;
import acme.entities.Word;
import acme.entities.roles.Manager;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {

	@Autowired
	protected ManagerTaskRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepo;


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
//				final Instant ld = LocalDateTime.now().toInstant(ZoneOffset.of("+02:00"));
//				final Date now = Date.from(ld);
				if(end == null) {
					errors.state(request, false, "end", "acme.validation.notNullDate");
				}else if(start == null) {
					errors.state(request, false, "start", "acme.validation.notNullDate");
				}else{
				errors.state(request, end.after(start), "end", "acme.validation.date");
//				errors.state(request, now.before(start), "start", "acme.validation.dateStart");
				}
				
				final int titleLength = request.getModel().getString("title").length();
				errors.state(request, titleLength >= 5 && titleLength <= 25, "title", "acme.validation.length", 5, 25);
				
				final int textLength = request.getModel().getString("text").length();
				errors.state(request, textLength > 0 && textLength <= 100, "text", "acme.validation.length", 1, 100);
				
				final List<Word> l = this.spamRepo.findMany().stream().collect(Collectors.toList()).get(0).getWords();


		//		final int spamText = (int) (Arrays.asList(l).stream().filter(x -> entity.getText().toLowerCase().contains(l.).count() * 100 / l.size());
		//		final int spamAuthor = (int) (Arrays.asList(sp).stream().filter(x -> entity.getTitle().toLowerCase().contains(x.toLowerCase().trim())).count() * 100 / sp.length);
		//
		//		final boolean isShoutSpam = (spamText) < l.get(0).getWord();
		//		errors.state(request, isShoutSpam, "text", "anonymous.shout.error.shout-spam");

	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		
		this.repository.save(entity);

	}

}

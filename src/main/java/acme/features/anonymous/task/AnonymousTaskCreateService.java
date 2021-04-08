package acme.features.anonymous.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Task;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousTaskCreateService implements AbstractCreateService<Anonymous, Task>{
	
	
	@Autowired
	AnonymousTaskRepository repository;

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

		request.unbind(entity, model, "moment");
		
		
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("start", "");
			model.setAttribute("end", "");
//			model.setAttribute("executionPeriod", "");
//			model.setAttribute("workLoad", "");
			model.setAttribute("title", "");
			model.setAttribute("text", "");
			model.setAttribute("link", "");
			model.setAttribute("visibility", "");
		} else {
			request.transfer(model, "start", "end", "title", "text", "link", "visibility");
		}
		
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;
		result = new Task();

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;


//		Date start = (Date) request.getModel().getAttribute("start");
//		Date end = (Date) request.getModel().getAttribute("end");
//		errors.state(request, false, null, null, null);
		
		final int textLength = request.getModel().getString("text").length();
		errors.state(request,  textLength <= 500, "text", "acme.validation.length", 500);

		final int titleLength = request.getModel().getString("title").length();
		errors.state(request, titleLength <= 80, "author", "acme.validation.length", 25);
		
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}

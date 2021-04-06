//package acme.features.anonymous.task;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.Task;
//import acme.framework.components.Errors;
//import acme.framework.components.Model;
//import acme.framework.components.Request;
//import acme.framework.entities.Anonymous;
//import acme.framework.services.AbstractCreateService;
//
//@Service
//public class AnonymousTaskCreateService implements AbstractCreateService<Anonymous, Task>{
//	
//	
//	@Autowired
//	AnonymousTaskRepository repository;
//
//	@Override
//	public boolean authorise(final Request<Task> request) {
//		assert request != null;
//
//		return true;
//	}
//
//	@Override
//	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
//		assert request != null;
//		assert entity != null;
//		assert errors != null;
//
//		request.bind(entity, errors);
//		
//	}
//
////	@Override
////	public void unbind(final Request<Task> request, final Task entity, final Model model) {
////		assert request != null;
////		assert entity != null;
////		assert model != null;
////
////		request.unbind(entity, model, "moment");
////		
////		
////		if (request.isMethod(HttpMethod.GET)) {
////			model.setAttribute("start", "");
////			model.setAttribute("end", "");
////			model.setAttribute("executionPeriod", "");
////			model.setAttribute("workLoad", "");
////			model.setAttribute("title", "");
////			model.setAttribute("text", "");
////			model.setAttribute("link", "");
////			model.setAttribute("visibility", "");
////		} else {
////			request.transfer(model, "author", "text", "info");
////		}
////		
////	}
//
//	@Override
//	public Task instantiate(final Request<Task> request) {
//		assert request != null;
//
//		Task result;
//		result = new Task();
//
//		return result;
//	}
//
////	@Override
////	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
////		assert request != null;
////		assert entity != null;
////		assert errors != null;
////
////
////		final int textLength = request.getModel().getString("text").length();
////		errors.state(request,  textLength >0 && textLength <= 100, "text", "acme.validation.length", 1, 100);
////
////		final int authorLength = request.getModel().getString("author").length();
////		errors.state(request,  authorLength >=5 && authorLength <= 25, "author", "acme.validation.length", 6, 25);
////		
////	}
//
//	@Override
//	public void create(final Request<Task> request, final Task entity) {
//		assert request != null;
//		assert entity != null;
//
//		this.repository.save(entity);
//		
//	}
//
//	@Override
//	public void unbind(final Request<Task> request, final Task entity, final Model model) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}

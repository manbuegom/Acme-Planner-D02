package acme.features.anonymous.shout;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Shout;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout>{
	
	
	@Autowired
	AnonymousShoutRepository repository;

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "author","moment");		
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author","moment", "text", "info");
		
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {

		Shout result;
		result = new Shout();

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;


		final int textLength = request.getModel().getString("text").length();
		errors.state(request,  textLength >0 && textLength <= 100, "text", "acme.validation.length", 1, 100);

		final int authorLength = request.getModel().getString("author").length();
		errors.state(request,  authorLength >=5 && authorLength <= 25, "author", "acme.validation.length", 6, 25);
		
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		Date moment;
		final String author = "anonymous";
		entity.setAuthor(author);
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
		
	}
	
//	@Override
//	public void onSuccess(final Request<Shout> request, final Response<Shout> response) {
//		assert request != null;
//		assert response != null;
//
//		if (request.isMethod(HttpMethod.POST)) {
//			PrincipalHelper.handleUpdate();
//		}
//	}

}

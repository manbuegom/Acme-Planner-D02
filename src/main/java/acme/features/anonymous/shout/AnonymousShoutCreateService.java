package acme.features.anonymous.shout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Shout;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
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

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment");
		
		
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("author", "");
			model.setAttribute("title", "");
			model.setAttribute("text", "");
			model.setAttribute("info", "");
		} else {
			request.transfer(model, "author", "text", "info");
		}
		
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

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
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}

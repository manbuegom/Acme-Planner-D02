
package acme.features.anonymous.shout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousShoutShowService implements AbstractShowService<Anonymous, Shout> {

	@Autowired
	protected AnonymousShoutRepository repository;


	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "author", "text");

	}


	@Override
	public Shout findOne(final Request<Shout> request) {
		assert request != null;
		Shout result;
		Integer id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOnebyId(id);

		return result;
	}

}

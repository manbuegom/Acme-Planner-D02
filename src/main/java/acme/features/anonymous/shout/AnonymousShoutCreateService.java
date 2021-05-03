
package acme.features.anonymous.shout;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Shout;
import acme.entities.Word;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	@Autowired
	AnonymousShoutRepository	repository;

	@Autowired
	AdministratorSpamRepository	spRep;


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

		final Date moment = new Date(System.currentTimeMillis() - 1);

		Shout result;
		result = new Shout();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final int textLength = request.getModel().getString("text").length();
		errors.state(request, textLength > 0 && textLength <= 100, "text", "acme.validation.length", 1, 100);

		final int authorLength = request.getModel().getString("author").length();
		errors.state(request, authorLength >= 5 && authorLength <= 25, "author", "acme.validation.length", 5, 25);

		if (!errors.hasErrors("text") && !errors.hasErrors("author")) {

			final List<Word> palabrasSpam = this.repository.spWords();

			final String pal = entity.getAuthor().trim();
			final Integer tamA = entity.getAuthor().split(" ").length;

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
			errors.state(request, !isShoutSpamA, "author", "anonymous.shout.error.shout-spam");
			errors.state(request, !isShoutSpamT, "text", "anonymous.shout.error.shout-spam");
		}
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}

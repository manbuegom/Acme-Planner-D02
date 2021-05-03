
package acme.features.administrator.spam;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Spam;
import acme.entities.Word;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamShowService implements AbstractShowService<Administrator, Spam> {

	@Autowired
	AdministratorSpamRepository repository;


	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String palabras = "";
		final Collection<Word> words = entity.getWords();

		for (final Word word : words) {
			palabras += word.getWord() + ", ";
		}

		model.setAttribute("palabras", palabras);
		request.unbind(entity, model, "threshold");

	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;

		final List<Spam> l = this.repository.findMany().stream().collect(Collectors.toList());
		return l.get(0);

	}

}

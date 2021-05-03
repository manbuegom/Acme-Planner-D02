package acme.features.administrator.spam;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Spam;
import acme.entities.Word;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorUpdateRemoveService implements AbstractUpdateService<Administrator, Spam> {

	@Autowired
	protected AdministratorSpamRepository repository;


	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
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
		model.setAttribute("newword", "");
		request.unbind(entity, model, "threshold");

	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;

		final List<Spam> l = this.repository.findMany().stream().collect(Collectors.toList());
		return l.get(0);
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final String palabra = request.getModel().getString("newword");
		final List<Word> palabras = entity.getWords();
		boolean exists = false;
		if (palabra.trim().equals("")) {
			errors.state(request, false, "newword", "administrator.word.error.empty");
		} else {
			for (final Word pal : palabras) {
				if (pal.getWord().equals(palabra)) {
					exists= true;
					break;
				}
			}
			errors.state(request, exists, "newword", "administrator.word.error.notfound");
		}

	}

	@Override
	public void update(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;

		final String newWord = request.getModel().getString("newword");
		final List<Word> palabras = entity.getWords();

		if (!newWord.equals("")) {
			for (final Word pal : palabras) {
				if (pal.getWord().equals(newWord)) {
					palabras.remove(pal);
					break;
				}
			}
			entity.setWords(palabras);
		}
		this.repository.save(entity);
	}

}

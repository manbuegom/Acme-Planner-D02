/*
 * AdministratorUserAccountUpdateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.spam;

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
public class AdministratorSpamUpdateService implements AbstractUpdateService<Administrator, Spam> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamRepository repository;

	// AbstractUpdateService<Administrator, UserAccount> interface -------------


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
		
		boolean exists = false;
		final List<Word> palabras = entity.getWords();

		for (final Word pal : palabras) {
			if (pal.getWord().equals(palabra)) {
				exists = true;
				break;
			}
		}
		errors.state(request, !exists, "newword", "administrator.word.error.exists");

	}

	@Override
	public void update(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;

		final String newWord = request.getModel().getString("newword");
		final String palabra = newWord.trim();
		final List<Word> palabras = entity.getWords();
		if (!palabra.equals("")) {
			final Word word = new Word(newWord.trim().toLowerCase());
			palabras.add(word);
			entity.setWords(palabras);
		}
		
		this.repository.save(entity);
	}

}

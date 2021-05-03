package acme.features.manager.task;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Task;
import acme.entities.roles.Manager;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ManagerTaskListService implements AbstractListService<Manager, Task>{

	@Autowired
	protected ManagerTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {

		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!= null;
		
		request.unbind(entity, model,  "start", "end", "title", "text","link", "workLoad", "visibility");
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;

		final Collection<Task> result;
				
		final Integer id = request.getPrincipal().getActiveRoleId();
		
		result = this.repository.findMyPrivateTasks(id).stream()
			.sorted(Comparator.comparing(Task::getWorkLoad)).collect(Collectors.toList());
		
		return result;
	}

}

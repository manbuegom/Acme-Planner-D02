package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import acme.entities.Task;
import acme.entities.roles.Manager;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager, Task>{

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
		
		request.unbind(entity, model,  "start", "end", "title", "text", "link", "workLoad");
		
	}

	
	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		Task result;
		Integer id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOnebyId(id);
		final Integer idrol= request.getPrincipal().getActiveRoleId();
		Assert.state(!this.repository.findMyNullTasks().contains(result),"default.error.not-authorised" );
		Assert.state(result.getManager().getId() == idrol  , "default.error.not-authorised");

		return result;
	}

}

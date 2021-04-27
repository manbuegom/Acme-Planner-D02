  package acme.features.authenticated.manager.task;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Task;
import acme.entities.roles.Manager;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/authenticated/manager/task")
public class ManagerTaskController extends AbstractController<Manager, Task> {

	
	@Autowired
	protected ManagerTaskListService listService;
	
	@Autowired
	protected ManagerTaskCreateService createService;
	
	@Autowired
	private ManagerTaskShowService showService;

	@Autowired
	private ManagerTaskUpdateService updateService;

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}

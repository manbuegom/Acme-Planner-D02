  package acme.features.manager.task;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Task;
import acme.entities.roles.Manager;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manager/task")
public class ManagerTaskController extends AbstractController<Manager, Task> {


	@Autowired
	protected ManagerTaskListService listService;
	
	@Autowired
	protected ManagerTaskCreateService createService;
	
	@Autowired
	protected ManagerTaskShowService showService;

	@Autowired
	protected ManagerTaskUpdateService updateService;
	
	@Autowired
	protected ManagerTaskDeleteService deleteService;

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}

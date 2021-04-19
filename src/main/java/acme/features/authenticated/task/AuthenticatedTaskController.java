package acme.features.authenticated.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/task/")
public class AuthenticatedTaskController extends AbstractController<Authenticated, Task> {
    
        
        @Autowired
        protected AuthenticatedTaskListService    listService;
        
        @Autowired
        private AuthenticatedTaskCreateService createService;
        
    	@Autowired
    	private AuthenticatedTaskShowService showService;

        @PostConstruct
        protected void initialise() {
            super.addBasicCommand(BasicCommand.LIST, this.listService);
            super.addBasicCommand(BasicCommand.CREATE, this.createService);
    		super.addBasicCommand(BasicCommand.SHOW, this.showService);
        }

}
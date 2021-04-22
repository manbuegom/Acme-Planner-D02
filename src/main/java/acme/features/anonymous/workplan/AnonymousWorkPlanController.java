package acme.features.anonymous.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/workplan/")
public class AnonymousWorkPlanController extends AbstractController<Anonymous, WorkPlan>{

	@Autowired
	protected AnonymousWorkPlanListService listService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}
	
}

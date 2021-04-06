package acme.features.anonymous.task;
//
//package acme.features.anonymous.task;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import acme.entities.Shout;
//import acme.features.anonymous.shout.AnonymousShoutCreateService;
//import acme.framework.components.BasicCommand;
//import acme.framework.controllers.AbstractController;
//import acme.framework.entities.Anonymous;
//
//@Controller
//@RequestMapping("/anonymous/task/")
//public class AnonymousTController extends AbstractController<Anonymous, Shout> {
//
//	@Autowired
//	private AnonymousShoutCreateService createService;
//
//
//	@PostConstruct
//	private void initialise() {
//		super.addBasicCommand(BasicCommand.CREATE, this.createService);
//	}
//
//}

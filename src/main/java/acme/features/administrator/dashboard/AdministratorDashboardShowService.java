package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfFinishedTasks", "numberOfPrivateTasks", "numberOfPublicTasks", 
			"numberOfNonFinishedTasks","avgWorkload","devWorkload", "minWorkload", "maxWorkload");

	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		final Dashboard res = new Dashboard();

		res.setNumberOfPublicTasks(this.repository.numberOfPublicTasks());
		res.setNumberOfFinishedTasks(this.repository.numberOfFinishedTasks());
		res.setNumberOfPrivateTasks(this.repository.numberOfPrivateTasks());
		res.setNumberOfNonFinishedTasks(this.repository.numberOfNonFinishedTasks());
		res.setAvgWorkload(this.repository.avgWorkload());
		res.setDevWorkload(this.repository.devWorkload());
		res.setMinWorkload(this.repository.minWorkload());
		res.setMaxWorkload(this.repository.maxWorkload());
//		res.setAvgWorkload(this.repository.avgWorkload());
//		res.setDevWorkload(this.repository.devWorkload());
//		res.setMinWorkload(this.repository.minWorkload());
//		res.setMaxWorkload(this.repository.maxWorkload());
		
		res.setId(20);

		return res;
	}

}

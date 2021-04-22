package acme.features.anonymous.workplan;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousWorkPlanListService implements AbstractListService<Anonymous, WorkPlan>{

	@Autowired
	protected AnonymousWorkPlanRepository repository;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {

		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!= null;
		
		request.unbind(entity, model, "start", "end", "tasks");
	}

	@Override
	public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
		assert request != null;

		final Collection<WorkPlan> result;
				
		final Date date = Date.valueOf(LocalDate.now());
		
		//ordena los resultados de mayor a menor carga de workload, si se quiere al reves añadir el .reversed() en el comparator
		result = this.repository.findPublicWorkPlans(date).stream().sorted(Comparator.comparing(WorkPlan::getExecutionPeriod)).collect(Collectors.toList());
		
		return result;
	}

	
}

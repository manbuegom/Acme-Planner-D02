package acme.features.anonymous.workplan;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkPlanRepository extends AbstractRepository{
	
	void save(WorkPlan workplan);
	
	 @Query("select w from WorkPlan w where w.visibility = true and w.end > ?1")
	 Collection<WorkPlan> findPublicWorkPlans(Date date);
	 
	 @Query("select w from WorkPlan w where w.id = ?1")
	 WorkPlan findOnebyId(Integer id);
}

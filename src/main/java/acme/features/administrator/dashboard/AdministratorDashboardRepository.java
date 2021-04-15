
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Task t where t.visibility = true ")
	Integer numberOfPublicTasks();

	@Query("select count(t) from Task t where t.visibility = false ")
	Integer numberOfPrivateTasks();

	@Query("select count(t) from Task t where t.end >= CURRENT_TIMESTAMP ")
	Integer numberOfFinishedTasks();

}

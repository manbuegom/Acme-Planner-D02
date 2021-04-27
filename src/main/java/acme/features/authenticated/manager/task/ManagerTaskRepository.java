package acme.features.authenticated.manager.task;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Spam;
import acme.entities.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {

    void save(Task task);
    
    @Query("select t from Task t")
   	Collection<Task> findMany();

    @Query("select t from Task t where t.id = ?1")
    Task findOnebyId(Integer id);
    
    @Query("select t from Task t where t.visibility = true and t.end > ?1")
    Collection<Task> findPublicTasks(Date date);

    @Query("select p from Spam p where p.threshold = 10")
    Spam findSpam();

}


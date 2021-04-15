package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
//@Transactional(TxType.SUPPORTS)
public interface AnonymousTaskRepository extends AbstractRepository {

    void save(Task task);
    
    @Query("select t from Task t")
   	Collection<Task> findMany();

    @Query("select t from Task t where t.id = ?1")
	Task findOnebyId(Integer id);
}


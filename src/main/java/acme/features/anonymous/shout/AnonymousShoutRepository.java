package acme.features.anonymous.shout;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {


    void save(Shout shout);

    @Query("select s from Shout s where s.id = ?1")
	Shout findOnebyId(Integer id);

    @Query("select s from Shout s")
	Collection<Shout> findMany();

    

}

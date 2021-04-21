package acme.features.anonymous.shout;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Shout;
import acme.entities.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {


    void save(Shout shout);

    @Query("select s from Shout s where s.id = ?1")
	Shout findOnebyId(Integer id);

    @Query("select s from Shout s where s.moment > ?1")
	Collection<Shout> findMany(Date d);
    
    @Query("select p from Spam p where p.threshold = 10")
    Spam findSpam();
    
    
 
  

}

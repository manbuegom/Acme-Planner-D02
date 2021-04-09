package acme.features.anonymous.shout;

import java.util.Collection;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import acme.entities.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {


    void save(Shout shout);

    @Query("select s from Shout s")
	Collection<Shout> findMany();

    

}

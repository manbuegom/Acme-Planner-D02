package acme.features.anonymous.shout;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import acme.entities.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
@Transactional(TxType.SUPPORTS)
public interface AnonymousShoutRepository extends AbstractRepository {

    void save(Shout shout);

}


package acme.features.anonymous.shout;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Shout;
import acme.entities.Spam;
import acme.entities.Word;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {

	@Query("select w from Word w")
	List<Word> spWords();
	
	@Query("select p from Spam p")
	Spam findSpam();

	void save(Shout shout);

	@Query("select s from Shout s where s.id = ?1")
	Shout findOnebyId(Integer id);

	@Query("select s from Shout s where s.moment > ?1")
	Collection<Shout> findMany(Date d);

	@Query("select s from Shout s where s.moment > ?1 order by s.moment")
	Collection<Shout> findManySorted(Date d);
	
}

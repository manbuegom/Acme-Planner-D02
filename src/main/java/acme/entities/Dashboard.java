
package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dashboard extends DomainEntity {

	protected static final long	serialVersionUID	= 1L;

	@NotNull
	protected Integer			numberOfPrivateTasks;

	@NotNull
	protected Integer			numberOfPublicTasks;

	@NotNull
	protected Integer			numberOfFinishedTasks;
}

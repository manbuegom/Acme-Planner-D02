
package acme.entities;

import java.time.Period;

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

	protected Integer			numberOfFinishedTasks;
	
	protected Integer			numberOfNonFinishedTasks;
	
	protected Double			avgWorkload;
	
	protected Double			minWorkload;
	
	protected Double			maxWorkload;
	
	protected Double			devWorkload;
	
	protected Period			executionPeriod;
	
	protected Double			avgPeriod;
	
	protected Double			minPeriod;
	
	protected Double			maxPeriod;
	
	protected Double			devPeriod;
	
}


package acme.entities;

import javax.persistence.Entity;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dashboard extends DomainEntity {

	protected static final long	serialVersionUID	= 1L;

	protected Integer			numberOfPrivateTasks;

	protected Integer			numberOfPublicTasks;

	protected Integer			numberOfFinishedTasks;
	
	protected Integer			numberOfNonFinishedTasks;
	
	protected Double			avgWorkload;
	
	protected Double			minWorkload;
	
	protected Double			maxWorkload;
	
	protected Double			devWorkload;
	
	protected Double			avgPeriod;
	
	protected Double			minPeriod;
	
	protected Double			maxPeriod;
	
	protected Double			devPeriod;
	
}

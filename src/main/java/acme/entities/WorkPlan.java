
package acme.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity {

	protected static final long	serialVersionUID	= 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	protected Date				start;

	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	protected Date				end;

	@ManyToMany
	protected List<Task>		tasks;

	@NotNull
	protected Boolean			visibility;


	@Transient
	public Long getExecutionPeriod() {

		final int milisecondsByHour = 3600000;
		final long hours = (this.end.getTime() - this.start.getTime()) / milisecondsByHour;

		return hours;
	}

}

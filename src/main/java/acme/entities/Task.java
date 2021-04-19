package acme.entities;

import java.time.Duration;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {

	protected static final long	serialVersionUID	= 1L;

	@Temporal(TemporalType.TIMESTAMP)
    @Future
    @NotNull
    protected Date 		start;

	@Temporal(TemporalType.TIMESTAMP)
    @Future
    @NotNull
    protected Date 		end;

	@NotBlank
	@Length(max = 80)
	protected String			title;

	@NotBlank
	@Length(max = 500)
	protected String			text;

	@URL
	protected String			link;

	@NotNull
	protected Boolean			visibility;
	
	@NotNull
	protected Double 			workLoad;

	//Derived
	@Transient
	public Long getExecutionPeriod() {
		return Duration.between(this.end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
			this.start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getSeconds();
	}
	
	//Derived
	
//	@Transient
//	public Double getWorkLoad() {
//		return (double) ChronoUnit.HOURS.between(this.end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
//			this.start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//
//	}

}

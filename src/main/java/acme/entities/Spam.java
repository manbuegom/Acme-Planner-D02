package acme.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity {

    private static final long    serialVersionUID    = 1L;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Word>                words;

    @Range(min = 0, max = 100)
    @NotNull
    private double                threshold;

}
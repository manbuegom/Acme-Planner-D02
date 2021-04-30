package acme.entities;

import javax.persistence.Entity;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Word extends DomainEntity {

	public Word(final String word) {
		this.word = word;
	}
		
	public Word() {

	}


	@Override
	public String toString() {
		return  this.word;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.word == null) ? 0 : this.word.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Word other = (Word) obj;
		if (this.word == null) {
			if (other.word != null)
				return false;
		} else if (!this.word.equals(other.word))
			return false;
		return true;
	}


	private static final long    serialVersionUID    = 1L;
    
    private String word;

    
    

}

package ar.edu.unnoba.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (!(obj instanceof AbstractEntity)) {
			return false;
		} else if (((AbstractEntity) obj).id == null) {
			return false;
		} else
			return ((AbstractEntity) obj).id.equals(this.id);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
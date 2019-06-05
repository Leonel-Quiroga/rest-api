package ar.edu.unnoba.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@SuppressWarnings("serial")
@Entity(name = "User")
@Table(name = "users")
@NamedQueries({
		@NamedQuery(
				name = "User.findByEmailAndPassword", 
				query = "SELECT u FROM User u WHERE u.email = :email"
				),
		@NamedQuery(
				name = "User.findUserById", 
				query = "SELECT u FROM User u WHERE u.id = :id"
				) 
})

public class User extends AbstractEntity {

	@NotNull(message = "Debe ingresar una direccion de correo electronico")
	@Column(name = "email")
	private String email;
	@Column(name = "pass")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + "]";
	}

}

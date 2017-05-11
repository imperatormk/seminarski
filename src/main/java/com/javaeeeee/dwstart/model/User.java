package com.javaeeeee.dwstart.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public abstract class User extends BaseEntity implements Serializable {
	@NotEmpty(message = "Username cannot be empty!")
	@Size(min = 4, message = "Username must must be more than 4 characters")
	@Column(name = "uName", unique = true)
	protected String username;

	@NotEmpty(message = "Password field cannot be empty!")
	@Size(min = 2, message = "Password must be more than 4 characters")
	@Column(name = "pass")
	protected String password;

	@Column(name = "fName")
	@NotEmpty(message = "First name cannot be empty")
	@Size(min = 4, max = 20, message = "First name must be between 4 and 20 letters long")
	protected String firstName;

	@Column(name = "lName")
	@NotEmpty(message = "Last name cannot be empty")
	@Size(min = 4, max = 20, message = "Last name must be between 4 and 20 letters long")
	protected String lastName;

	@Column(name = "email")
	@NotEmpty(message = "Email cannot be empty")
	@Email()
	protected String email;

	//@NotNull
	//private int enabled = 1;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setSecondName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}*/

	public abstract int userType();

	@Override
	public String toString() {
		return username + ": " + firstName + " " + lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}

package com.fikt.seminarski.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "users")
@DiscriminatorValue("student")
public class Student extends User implements Serializable {
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@ElementCollection(targetClass = Upload.class)
	@JsonBackReference
	@Column(nullable = true)
	private List<Upload> uploads;
	
	public List<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(List<Upload> uploads) {
		this.uploads = uploads;
	}
	
	@Column(name = "stuNo")
	@NotEmpty(message = "Student number cannot be empty!")
	protected int stuNumber;
	
	@Column(name = "stuProg")
	@NotEmpty(message = "Student program cannot be empty!")
	protected String stuProgram;

	/*@Override
	public int userType() {
		return 0;
	}*/
	
	public int getStudentNumber() {
		return stuNumber;
	}
	
	public void setStudentNumber(int stuNumber) {
		this.stuNumber = stuNumber;
	}
	
	public String getStudentProgram() {
		return stuProgram;
	}

	public void setStudentProgram(String stuProgram) {
		this.stuProgram = stuProgram;
	}

	@Override
	public String toString() {
		return getUsername() + " " + getFirstName() + " " + getLastName();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}

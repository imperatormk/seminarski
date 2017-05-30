package com.fikt.seminarski.model;

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
@DiscriminatorValue("teacher")
public class Teacher extends User {

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	@ElementCollection(targetClass = Subject.class)
	@JsonBackReference
	@Column(nullable = true)
	private List<Subject> subjects;
	
	@Column(name = "title")
	@NotEmpty(message = "Teacher title cannot be empty!")
	protected String title; 
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	/*@Override
	public int userType() {
		return 1;
	}*/

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
}

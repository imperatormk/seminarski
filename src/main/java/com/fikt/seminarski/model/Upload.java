package com.fikt.seminarski.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "uploads")
public class Upload extends BaseEntity implements Serializable {
	@Column(name = "studentID")
	@NotNull(message = "Student ID cannot be empty!")
	private int studentID;
	
	public int getStudentID() {
		return studentID;
	}
	
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	public Upload() {
	}
	
	public Upload(int studentID, int workID, String storeURL, String title, int state, Date subDate, int grade) {
		super();
		this.studentID = studentID;
		this.workID = workID;
		this.storeURL = storeURL;
		this.title = title;
		this.state = state;
		this.subDate = subDate;
		this.grade = grade;
	}
	
	public Upload(int studentID, int workID, String storeURL, String title, int state, int grade) {
		super();
		this.studentID = studentID;
		this.workID = workID;
		this.storeURL = storeURL;
		this.title = title;
		this.state = state;
		this.grade = grade;
	}

	@ManyToOne(targetEntity = Student.class)
	@JoinColumn(name = "studentID", insertable = false, updatable = false)
	@JsonManagedReference
	private Student student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Column(name = "workID")
	@NotNull(message = "Work ID cannot be empty!")
	private int workID;
	
	public int getWorkID() {
		return workID;
	}
	
	public void setWorkID(int workID) {
		this.workID = workID;
	}
	
	@ManyToOne(targetEntity = Work.class)
	@JoinColumn(name = "workID", insertable = false, updatable = false)
	@JsonManagedReference
	private Work work;
	
	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}
	
	@Column(name = "storeURL")
	@NotEmpty(message = "URL cannot be empty!")
	private String storeURL;
	
	public String getURL() {
		return storeURL;
	}

	public void setURL(String storeURL) {
		this.storeURL = storeURL;
	}
	
	@Column(name = "title")
	@NotEmpty(message = "Title cannot be empty!")
	private String title; 	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "grade")
	private int grade;

	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Column(name = "state")
	@NotNull(message = "State value cannot be empty!")
	private int state;

	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public String getStateStr() {	
		switch (state) {
		case 0:
			return "Upload is awating review";
		case 1:
			return "A revision has been requested";
		case 2:
			return "Revision is awating review";
		case 3:
			return "Graded with " + grade;
		default:
			return "";
		}
	}
	
	@Column(name = "subDate")
	private Date subDate;

	public Date getDate() {
		return subDate;
	}
	
	public void setDate(Date subDate) {
		this.subDate = subDate;
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

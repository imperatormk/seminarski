package com.fikt.seminarski.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "works")
public class Work extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Column(name = "subjectID")
	@NotEmpty(message = "Subject ID cannot be empty!")
	private int subjectID;
	
	public int getSubjectID() {
		return subjectID;
	}
	
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	
	@OneToMany(mappedBy = "work", cascade = CascadeType.ALL)
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
	
	@ManyToOne(targetEntity = Subject.class)
	@JoinColumn(name = "subjectID", insertable = false, updatable = false)
	@JsonManagedReference
	private Subject subject;
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@Column(name = "description")
	@NotEmpty(message = "Description cannot be empty!")
	private String description;
	
	/*@Column(name = "path")
	@NotEmpty(message = "Path cannot be empty!")
	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}*/
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 31 * hash + id;
		hash = 31 * hash + (String.valueOf(id).hashCode());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}

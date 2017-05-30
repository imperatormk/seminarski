package com.fikt.seminarski.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity {
	@Column(name = "title")
	@NotEmpty(message = "Title cannot be empty!")
	private String subjectName;
	
	@Column(name = "teacherID")
	@NotNull(message = "Teacher ID cannot be empty!")
	private int teacherID;
	
	public int getTeacherID() {
		return teacherID;
	}
	
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	
	@ManyToOne(targetEntity = Teacher.class)
	@JoinColumn(name = "teacherID", insertable=false, updatable=false)
	@JsonManagedReference
	private Teacher teacher;
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	@ElementCollection(targetClass = Work.class)
	@JsonBackReference
	@Column(nullable = true)
	private List<Work> works;
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

/*	public List<Subject> getSubjects() { -->>> this will be uploads list
		Collections.sort(subjects, new Comparator<Subject>() {
		    @Override
		    public int compare(Subject o1, Subject o2) {
		        return o1.getSubjectName().compareTo(o2.getSubjectName());
		    }
		});
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}*/
	
	public List<Work> getWorks() {
		return works;
	}

	public void setWorks(List<Work> works) {
		this.works = works;
	}

	@Override
	public String toString() {
		return subjectName + " by " + teacher;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
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
		Subject other = (Subject) obj;
		if (subjectName == null) {
			if (other.subjectName != null)
				return false;
		} else if (!subjectName.equals(other.subjectName))
			return false;
		return true;
	}
}

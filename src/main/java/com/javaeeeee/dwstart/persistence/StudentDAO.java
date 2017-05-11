package com.javaeeeee.dwstart.persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.javaeeeee.dwstart.model.Student;

public interface StudentDAO {
	public Student save(Student s);

	public void delete(Student s);

	public Optional<Student> getById(int id);

	public List<Student> getAll();

	// public Set<Subject> getSubjects(int id);
}
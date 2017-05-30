package com.fikt.seminarski.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fikt.seminarski.model.Student;
import com.fikt.seminarski.model.Work;

public interface StudentService {
	public Student save(Student s);

	public void delete(Student s);

	public Optional<Student> getById(int id);

	public List<Student> getAll();

	//public Set<Work> getWorks (int id);
}

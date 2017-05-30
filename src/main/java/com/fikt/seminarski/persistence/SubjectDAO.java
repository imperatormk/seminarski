package com.fikt.seminarski.persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.model.Teacher;
import com.fikt.seminarski.model.Work;

public interface SubjectDAO {
	public Subject save(Subject s);

	public void delete(Subject s);

	public Optional<Subject> getById(int id);

	public List<Subject> getAll();
}

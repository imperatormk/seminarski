package com.fikt.seminarski.persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.model.Teacher;
import com.fikt.seminarski.model.Work;

public interface TeacherDAO {
	public Teacher save(Teacher s);

	public void delete(Teacher s);

	public Optional<Teacher> getById(int id);

	public List<Teacher> getAll();

}

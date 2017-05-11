package com.javaeeeee.dwstart.persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.model.Teacher;
import com.javaeeeee.dwstart.model.Work;

public interface SubjectDAO {
	public Subject save(Subject s);

	public void delete(Subject s);

	public Optional<Subject> getById(int id);

	public List<Subject> getAll();
}

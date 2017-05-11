package com.javaeeeee.dwstart.service;

import java.util.List;
import java.util.Optional;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.model.Work;

public interface SubjectService {

	public Subject save(Subject s);

	public void delete(Subject s);

	public Optional<Subject> getById(int id);

	public List<Subject> getAll();
}

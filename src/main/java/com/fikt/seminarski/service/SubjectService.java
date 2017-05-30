package com.fikt.seminarski.service;

import java.util.List;
import java.util.Optional;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.model.Work;

public interface SubjectService {

	public Subject save(Subject s);

	public void delete(Subject s);

	public Optional<Subject> getById(int id);

	public List<Subject> getAll();
}

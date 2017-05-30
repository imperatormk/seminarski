package com.fikt.seminarski.service.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.persistence.SubjectDAO;
import com.fikt.seminarski.persistence.implementation.SubjectDAOImpl;
import com.fikt.seminarski.service.SubjectService;

public class SubjectServiceImpl implements SubjectService {
	
	private SubjectDAO subjectDAO;
	
	public SubjectServiceImpl(SessionFactory sessionFactory) {
		this.subjectDAO = new SubjectDAOImpl(sessionFactory);
	}

	public Subject save(Subject s) {
		return subjectDAO.save(s);
	}

	public void delete(Subject s) { //cascade delete
		subjectDAO.delete(s);
	}

	public Optional<Subject> getById(int id) {
		return subjectDAO.getById(id);
	}

	public List<Subject> getAll() {
		return subjectDAO.getAll();
	}
}

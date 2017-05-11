package com.javaeeeee.dwstart.service.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.persistence.SubjectDAO;
import com.javaeeeee.dwstart.persistence.implementation.SubjectDAOImpl;
import com.javaeeeee.dwstart.service.SubjectService;

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

package com.fikt.seminarski.persistence.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.persistence.SubjectDAO;

import io.dropwizard.hibernate.AbstractDAO;

public class SubjectDAOImpl extends AbstractDAO<Subject> implements SubjectDAO {
	
	public SubjectDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

	public Subject save(Subject s) {
		return persist(s);
	}

	public void delete(Subject s) {
		currentSession().delete(s.getId());
	}

	public Optional<Subject> getById(int id) {
		return Optional.ofNullable(currentSession().get(Subject.class, id));
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getAll() {
		return (List<Subject>) currentSession().createQuery("from Subject").list();
	}
}

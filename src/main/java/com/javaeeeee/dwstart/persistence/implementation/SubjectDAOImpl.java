package com.javaeeeee.dwstart.persistence.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.persistence.SubjectDAO;

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

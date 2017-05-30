package com.fikt.seminarski.persistence.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Work;
import com.fikt.seminarski.persistence.WorkDAO;

import io.dropwizard.hibernate.AbstractDAO;

public class WorkDAOImpl extends AbstractDAO<Work> implements WorkDAO {
	
	SessionFactory sessionFactory;
	Session openSession;
	
	public WorkDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
        openSession = sessionFactory.openSession();
    }
	
	public Work save(Work s) {
		return persist(s);
	}

	public void delete(Work s) {
		currentSession().delete(s.getId());
	}

	public Optional<Work> getById(int id) {
		return Optional.ofNullable(currentSession().get(Work.class, id));
	}

	@SuppressWarnings("unchecked")
	public List<Work> getAll() {
		return (List<Work>) currentSession().createQuery("from Work").list();
	}
}

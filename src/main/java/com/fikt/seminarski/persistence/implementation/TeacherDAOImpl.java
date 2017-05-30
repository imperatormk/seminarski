package com.fikt.seminarski.persistence.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Teacher;
import com.fikt.seminarski.persistence.TeacherDAO;

import io.dropwizard.hibernate.AbstractDAO;

public class TeacherDAOImpl extends AbstractDAO<Teacher> implements TeacherDAO {
	
	public TeacherDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
	
	public Teacher save(Teacher s) {
		return persist(s);
	}

	public void delete(Teacher s) {
		currentSession().delete(s.getId());
	}

	public Optional<Teacher> getById(int id) {
		return Optional.ofNullable(currentSession().get(Teacher.class, id));
	}

	@SuppressWarnings("unchecked")
    public List<Teacher> getAll() {
        List<Teacher> teachers = (List<Teacher>) currentSession().createQuery("from Teacher").list();
        return teachers;
    }
}

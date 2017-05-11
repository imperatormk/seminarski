package com.javaeeeee.dwstart.persistence.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;

import com.javaeeeee.dwstart.model.Upload;
import com.javaeeeee.dwstart.persistence.UploadDAO;

public class UploadDAOImpl extends AbstractDAO<Upload> implements UploadDAO {
	
	public UploadDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

	public Upload save(Upload s) {
		return persist(s);
	}

	public void delete(Upload s) {
		currentSession().delete(s.getId());
	}

	public Optional<Upload> getById(int id) {
		return Optional.ofNullable(currentSession().get(Upload.class, id));
	}

	@SuppressWarnings("unchecked")
	public List<Upload> getAll() {
		return (List<Upload>) currentSession().createQuery("from Upload").list();
	}
}

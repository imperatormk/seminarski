package com.fikt.seminarski.service.implementation;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Upload;
import com.fikt.seminarski.persistence.UploadDAO;
import com.fikt.seminarski.persistence.implementation.UploadDAOImpl;
import com.fikt.seminarski.service.UploadService;

public class UploadServiceImpl implements UploadService {
	
	private UploadDAO uploadDAO;
	
	public UploadServiceImpl(SessionFactory sessionFactory) {
		this.uploadDAO = new UploadDAOImpl(sessionFactory);
	}

	public Upload save(Upload s) {
		return uploadDAO.save(s);
	}

	public void delete(Upload s) { //cascade delete
		uploadDAO.delete(s);
	}

	public Optional<Upload> getById(int id) {
		return uploadDAO.getById(id);
	}

	public List<Upload> getAll() {
		return uploadDAO.getAll();
	}
}

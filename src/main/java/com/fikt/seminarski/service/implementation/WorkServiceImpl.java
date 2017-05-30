package com.fikt.seminarski.service.implementation;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Work;
import com.fikt.seminarski.persistence.WorkDAO;
import com.fikt.seminarski.persistence.implementation.WorkDAOImpl;
import com.fikt.seminarski.service.WorkService;

public class WorkServiceImpl implements WorkService {
	
	private WorkDAO workDAO;
	
	public WorkServiceImpl(SessionFactory sessionFactory) {
		this.workDAO = new WorkDAOImpl(sessionFactory);
	}

	public Work save(Work s) {
		return workDAO.save(s);
	}

	public void delete(Work s) { //cascade delete
		workDAO.delete(s);
	}

	public Optional<Work> getById(int id) {
		return workDAO.getById(id);
	}

	public List<Work> getAll() {
		return workDAO.getAll();
	}
}

package com.fikt.seminarski.service.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.model.Teacher;
import com.fikt.seminarski.persistence.TeacherDAO;
import com.fikt.seminarski.persistence.implementation.TeacherDAOImpl;
import com.fikt.seminarski.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
	
	private TeacherDAO teacherDAO; //static?

	public TeacherServiceImpl(SessionFactory sessionFactory) {
		this.teacherDAO = new TeacherDAOImpl(sessionFactory);
	}

	public Teacher save(Teacher s) {
		return teacherDAO.save(s);
	}

	public void delete(Teacher s) {
		teacherDAO.delete(s);
	}

	public Optional<Teacher> getById(int id) {
		return teacherDAO.getById(id);
	}

	public List<Teacher> getAll() {
		return teacherDAO.getAll();
	}
	
	public void deleteSubjectFromTeacher(Teacher t, Subject subject) {
		
	}
}

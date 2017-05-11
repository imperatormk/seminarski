package com.javaeeeee.dwstart.service.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.model.Teacher;
import com.javaeeeee.dwstart.persistence.TeacherDAO;
import com.javaeeeee.dwstart.persistence.implementation.TeacherDAOImpl;
import com.javaeeeee.dwstart.service.TeacherService;

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

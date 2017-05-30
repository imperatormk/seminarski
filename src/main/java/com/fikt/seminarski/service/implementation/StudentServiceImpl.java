package com.fikt.seminarski.service.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Student;
import com.fikt.seminarski.persistence.StudentDAO;
import com.fikt.seminarski.persistence.implementation.StudentDAOImpl;
import com.fikt.seminarski.service.StudentService;

public class StudentServiceImpl implements StudentService {
	
	private StudentDAO studentDAO; //static?
	
	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.studentDAO = new StudentDAOImpl(sessionFactory);
	}

	public Student save(Student s) {
		return studentDAO.save(s);
	}

	public void delete(Student s) {
		studentDAO.delete(s);
	}

	public Optional<Student> getById(int id) {
		return studentDAO.getById(id);
	}

	public List<Student> getAll() {
		return studentDAO.getAll();
	}

	/*public Set<Course> getCourses(long id) {
		return studentRepository.getCourses(id);
	}*/

}

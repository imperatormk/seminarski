package com.javaeeeee.dwstart.service.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

import com.javaeeeee.dwstart.persistence.StudentDAO;
import com.javaeeeee.dwstart.persistence.implementation.StudentDAOImpl;
import com.javaeeeee.dwstart.service.StudentService;
import com.javaeeeee.dwstart.model.Student;

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

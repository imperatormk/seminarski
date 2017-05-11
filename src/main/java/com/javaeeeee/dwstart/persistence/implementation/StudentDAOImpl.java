package com.javaeeeee.dwstart.persistence.implementation;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

import com.javaeeeee.dwstart.model.Student;
import com.javaeeeee.dwstart.persistence.StudentDAO;

public class StudentDAOImpl extends AbstractDAO<Student> implements StudentDAO {
	
	public StudentDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
	
	public Student save(Student s) {
		return persist(s);
	}

	public void delete(Student s) {
		currentSession().delete(s);
	}

	public Optional<Student> getById(int id) {
		return Optional.ofNullable(currentSession().get(Student.class, id));
	}

	@SuppressWarnings("unchecked")
	    public List<Student> getAll() {
	        List<Student> students = (List<Student>) currentSession().createQuery("from Student").list();
	        return students;
	    }

	/*public Set<Work> getWorks(int id) {
		Student student = getById(id);
		Set<Work> works = new HashSet<Work>();
		for (Work work : student.) {
			works.add(joined.getCourse());
		}
		return works;
	}*/

}

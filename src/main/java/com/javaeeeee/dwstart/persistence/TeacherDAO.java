package com.javaeeeee.dwstart.persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.model.Teacher;
import com.javaeeeee.dwstart.model.Work;

public interface TeacherDAO {
	public Teacher save(Teacher s);

	public void delete(Teacher s);

	public Optional<Teacher> getById(int id);

	public List<Teacher> getAll();

}

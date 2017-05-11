package com.javaeeeee.dwstart.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.model.Teacher;

public interface TeacherService {
	public Teacher save(Teacher s);

	public void delete(Teacher s);

	public Optional<Teacher> getById(int id);

	public List<Teacher> getAll();
	
	public void deleteSubjectFromTeacher(Teacher t, Subject subject);
}

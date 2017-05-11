package com.javaeeeee.dwstart.views;

import io.dropwizard.views.View;

import java.util.List;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.model.Teacher;

public class TeacherView extends View {
    private final Teacher teacher;

    public TeacherView(Teacher teacher) {
        super("teacher.mustache");
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    
    public String getFirstName() {
    	return teacher.getFirstName();
    }
    
    public String getLastName() {
    	return teacher.getLastName();
    }
    
    public List<Subject> getSubjectList() {
        return teacher.getSubjects();
    }
}
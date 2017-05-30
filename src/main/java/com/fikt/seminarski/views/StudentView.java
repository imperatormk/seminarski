package com.fikt.seminarski.views;

import io.dropwizard.views.View;

import java.util.List;

import com.fikt.seminarski.model.Student;
import com.fikt.seminarski.model.Upload;

public class StudentView extends View {
    private final Student student;

    public StudentView(Student student) {
        super("student.mustache");
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
    
    public String getName() {
    	return student.getFirstName();
    }
    
    public String getSurname() {
    	return student.getLastName();
    }
    
    public List<Upload> getUploadList() {
    	return student.getUploads();
    }
}
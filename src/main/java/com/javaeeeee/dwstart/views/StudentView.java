package com.javaeeeee.dwstart.views;

import io.dropwizard.views.View;

import java.util.List;

import com.javaeeeee.dwstart.model.Student;
import com.javaeeeee.dwstart.model.Upload;

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
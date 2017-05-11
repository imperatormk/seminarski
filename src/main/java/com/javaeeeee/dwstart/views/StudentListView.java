package com.javaeeeee.dwstart.views;

import io.dropwizard.views.View;

import java.util.List;

import com.javaeeeee.dwstart.model.Student;

public class StudentListView extends View {
    private final List<Student> studentList;

    public StudentListView(List<Student> studentList) {
        super("studentList.mustache");
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
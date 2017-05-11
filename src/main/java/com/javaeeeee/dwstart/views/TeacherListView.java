package com.javaeeeee.dwstart.views;

import io.dropwizard.views.View;
import java.util.List;

import com.javaeeeee.dwstart.model.Teacher;

public class TeacherListView extends View {
    private final List<Teacher> teacherList;

    public TeacherListView(List<Teacher> teacherList) {
        super("teacherList.mustache");
        this.teacherList = teacherList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }
}
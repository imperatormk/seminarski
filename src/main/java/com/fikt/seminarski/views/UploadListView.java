package com.fikt.seminarski.views;

import io.dropwizard.views.View;

import java.util.List;

import com.fikt.seminarski.model.Student;
import com.fikt.seminarski.model.Upload;

public class UploadListView extends View {
    private final Student student;

    public UploadListView(Student student) {
        super("uploadList.mustache");
        this.student = student;
    }

    public List<Upload> getUploadList() {
        return student.getUploads();
    }
}
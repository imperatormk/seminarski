package com.fikt.seminarski.views;

import io.dropwizard.views.View;

import java.util.List;

import com.fikt.seminarski.model.Subject;

public class SubjectListView extends View {
    private final List<Subject> subjectList;

    public SubjectListView(List<Subject> subjectList) {
        super("subjectList.mustache");
        this.subjectList = subjectList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }
}
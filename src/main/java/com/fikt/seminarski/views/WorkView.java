package com.fikt.seminarski.views;

import io.dropwizard.views.View;

import java.util.List;
import java.util.stream.Collectors;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.model.Upload;
import com.fikt.seminarski.model.Work;

public class WorkView extends View {
    private final Work work;
    private boolean isStudent = true; //temp

    public WorkView(Work work) {
        super("work.mustache");
        this.work = work;
    }

    public Work getWork() {
        return work;
    }
    
    public String getTitle() {
    	return work.getTitle();
    }
    
    public String getDescription() {
    	return work.getDescription();
    }
    
    public Subject getSubject() {
    	return work.getSubject();
    }
    
    public List<Upload> getUploadList() {
    	if (isStudent) {
    		return work.getUploads().stream().filter(it -> 1 == it.getStudent().getId()).collect(Collectors.toList());
    	}
    	else {
    		return work.getUploads();
    	}   	
    }
};
package com.fikt.seminarski.views;

import io.dropwizard.views.View;

import java.util.List;
import java.util.stream.Collectors;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.model.Upload;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.model.Work;

public class WorkView extends View {
    private final Work work;
    private final User user;

    public WorkView(Work work, User user) {
        super("work.mustache");
        this.work = work;
        this.user = user;
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
    
    public boolean getIsStudent() {
    	return user.getRole().equals("student");
    }
    
    public boolean getIsRevision() {
    	return getUploadList().size() > 0;
    }
    
    public List<Upload> getUploadList() {
    	if (getIsStudent()) {
    		return work.getUploads().stream().filter(it -> user.getId() == it.getStudent().getId()).collect(Collectors.toList());
    	}
    	else {
    		return work.getUploads();
    	}   	
    }
};
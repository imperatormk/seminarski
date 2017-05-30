package com.fikt.seminarski.views;

import javax.management.relation.Role;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.model.Upload;
import com.fikt.seminarski.model.Work;

import io.dropwizard.views.View;

public class UploadView extends View {
    private final Upload upload;
    private final String role;
    public UploadView(Upload upload, String role) {
        super("upload.mustache");
        this.upload = upload;
        this.role = role;
    }

    public Upload getUpload() {
        return upload;
    }
    
    public String getUploadFile() {
    	return upload.getURL().substring(upload.getURL().lastIndexOf('/') + 1).split("\\?")[0].split("#")[0];
    }
    
    public Work getWork() {
    	return upload.getWork();
    }
    
    public Subject getSubject() {
    	return upload.getWork().getSubject();
    }
    
    public boolean getIsStudent() {
    	return (role.equals("student"));
    }
    
    public boolean getIsGraded() {
    	return upload.getState() == 3;
    }
    
    public boolean isRequested() {
    	return !(upload.getState() == 0 || upload.getState() == 2);
    }
}
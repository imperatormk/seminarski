package com.javaeeeee.dwstart.views;

import io.dropwizard.views.View;

import java.util.List;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.model.Upload;
import com.javaeeeee.dwstart.model.Work;

public class WorkView extends View {
    private final Work work;

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
    	return work.getUploads();
    }
}
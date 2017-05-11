package com.javaeeeee.dwstart.views;

import io.dropwizard.views.View;

import com.javaeeeee.dwstart.model.Subject;
import com.javaeeeee.dwstart.model.Upload;
import com.javaeeeee.dwstart.model.Work;

public class UploadView extends View {
    private final Upload upload;

    public UploadView(Upload upload) {
        super("upload.mustache");
        this.upload = upload;
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
}
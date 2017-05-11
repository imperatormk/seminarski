package com.javaeeeee.dwstart.views;

import com.javaeeeee.dwstart.model.Work;

import io.dropwizard.views.View;

public class UploadFileView extends View {
	int id;
	String title;

	public UploadFileView(Work work) {
        super("uploadFile.mustache");
        this.id = work.getId();
        this.title = work.getTitle();
    }
	
	public int getWorkID() {
		return id;
	}

	public void setWorkID(int id) {
		this.id = id;
	}
	
	public String getWorkTitle() {
		return title;
	}

	public void setWorkTitle(String title) {
		this.title = title;
	}
}
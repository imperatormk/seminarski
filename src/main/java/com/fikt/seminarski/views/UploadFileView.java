package com.fikt.seminarski.views;

import com.fikt.seminarski.model.Work;

import io.dropwizard.views.View;

public class UploadFileView extends View {
	int id;
	int oldID = -1;
	String title;

	public UploadFileView(Work work, int oldID) {
        super("uploadFile.mustache");
        this.id = work.getId();
        this.title = work.getTitle();
        this.oldID = oldID;
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
	
	public int getOldID() {
		return oldID;
	}
	
	public boolean getIsRevision() {
		return oldID >= 0;
	}
}
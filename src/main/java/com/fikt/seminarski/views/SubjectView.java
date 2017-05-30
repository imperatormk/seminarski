package com.fikt.seminarski.views;

import io.dropwizard.views.View;

import java.util.List;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.model.Work;

public class SubjectView extends View {
    private final Subject subject;

    public SubjectView(Subject subject) {
        super("subject.mustache");
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }
    
    public String getSubjectName() {
    	return subject.getSubjectName();
    }
    
    public int getID() {
    	return subject.getId();
    }
    
    public List<Work> getWorkList() {
    	/*List<Object> disList = subject.getWorks().stream()
    		    .distinct()
    		    .collect(Collectors.toList());
    	
    	List <Work> disListWork = new ArrayList<Work>();
    	
    	for (Iterator<Object> i = disList.iterator(); i.hasNext();) {
    	    Work work = (Work) i.next();
    	    disListWork.add(work);
    	}
    	
    	disListWork.sort(new Comparator<Work>() {
    	    public int compare(Work obj1, Work obj2) {
    	        return obj1.getId() - obj2.getId();
    	    }
    	});
    	
    	return disListWork;*/
    	
    	return subject.getWorks();
    }
}
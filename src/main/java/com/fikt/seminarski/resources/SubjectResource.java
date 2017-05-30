/*
 * The MIT License
 *
 * Copyright 2015 Dmitry Noranovich.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.fikt.seminarski.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.service.SubjectService;
import com.fikt.seminarski.service.implementation.SubjectServiceImpl;
import com.fikt.seminarski.views.SubjectListView;
import com.fikt.seminarski.views.SubjectView;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

@Path("/subjects")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class SubjectResource {

    /**
     * The DAO object to manipulate employees.
     */
    private SubjectService subjectService;
    
    public SubjectResource(SessionFactory sessionFactory) {
        this.subjectService = new SubjectServiceImpl(sessionFactory);
    }
    
    @GET
    @UnitOfWork
    public SubjectListView getAll() {
    	List<Subject> lst = subjectService.getAll();
    	return new SubjectListView(lst);
    }
    
    @GET
    @Path("/{id}")
    @UnitOfWork
    public SubjectView findById(@PathParam("id") IntParam id) {
    	Optional<Subject> subID = subjectService.getById(id.get());
    	if (subID.isPresent()) {
    		Subject sub = subID.get();
            return new SubjectView(sub);
    	}
    	else {
    		System.out.println("null");
    		return null;
    	}
    }
}

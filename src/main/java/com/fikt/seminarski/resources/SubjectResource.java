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
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Subject;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.service.SubjectService;
import com.fikt.seminarski.service.implementation.SubjectServiceImpl;
import com.fikt.seminarski.views.SubjectListView;
import com.fikt.seminarski.views.SubjectView;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

@RolesAllowed({"admin", "teacher", "student"})
@Path("/subjects")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class SubjectResource {

    private SubjectService subjectService;
    
    public SubjectResource(SessionFactory sessionFactory) {
        this.subjectService = new SubjectServiceImpl(sessionFactory);
    }
    
    @GET
    @UnitOfWork
    public SubjectListView getAll(@Auth User user) {
    	List<Subject> lst = null;
    	if (!user.getRole().equals("teacher")) {
    		lst = subjectService.getAll();
    	}
    	else {
    		lst = subjectService.getAll().stream().filter(it -> user.getId() == it.getTeacherID()).collect(Collectors.toList());
    	} 	
    	return new SubjectListView(lst);
    }
    
    @GET
    @RolesAllowed({"admin", "teacher", "student"}) //add enroll?
    @Path("/{id}")
    @UnitOfWork
    public SubjectView findById(@Auth User user, @PathParam("id") IntParam id) {
    	Optional<Subject> subID = subjectService.getById(id.get());
    	if (subID.isPresent() && (user.getId() == subID.get().getTeacherID() || !user.getRole().equals("teacher"))) {
    		Subject sub = subID.get();
            return new SubjectView(sub);
    	}
    	else {
    		return null;
    	}
    }
}

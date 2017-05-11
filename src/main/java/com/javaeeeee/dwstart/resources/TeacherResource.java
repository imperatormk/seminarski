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
package com.javaeeeee.dwstart.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.SessionFactory;

import com.javaeeeee.dwstart.model.Teacher;
import com.javaeeeee.dwstart.service.TeacherService;
import com.javaeeeee.dwstart.service.implementation.TeacherServiceImpl;
import com.javaeeeee.dwstart.views.TeacherListView;
import com.javaeeeee.dwstart.views.TeacherView;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

@Path("/teachers")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class TeacherResource {

    /**
     * The DAO object to manipulate employees.
     */
    private TeacherService teacherService;
    
    public TeacherResource(SessionFactory sessionFactory) {
        this.teacherService = new TeacherServiceImpl(sessionFactory);
    }
    
    @GET
    @UnitOfWork
    public TeacherListView getAll() {
    	List<Teacher> lst = teacherService.getAll();
    	return new TeacherListView(lst);
    }
    
    @GET
    @Path("/{id}")
    @UnitOfWork
    public TeacherView findById(@PathParam("id") IntParam id) {
    	Optional<Teacher> teaID = teacherService.getById(id.get());
    	if (teaID.isPresent()) {
    		Teacher tea = teaID.get();
            return new TeacherView(tea);
    	}
    	else {
    		System.out.println("null");
    		return null;
    	}
    }
}

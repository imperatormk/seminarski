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

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fikt.seminarski.model.Upload;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.service.StudentService;
import com.fikt.seminarski.service.UploadService;
import com.fikt.seminarski.service.implementation.StudentServiceImpl;
import com.fikt.seminarski.service.implementation.UploadServiceImpl;
import com.fikt.seminarski.views.UploadListView;
import com.fikt.seminarski.views.UploadView;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.Priorities;
import org.hibernate.SessionFactory;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

@Priority(Priorities.AUTHENTICATION)
@Path("/uploads")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class UploadResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadResource.class);

    private UploadService uploadService;
    private StudentService studentService;
    
    public UploadResource(SessionFactory sessionFactory) {
        this.uploadService = new UploadServiceImpl(sessionFactory);
        this.studentService = new StudentServiceImpl(sessionFactory);
    }
    
    @GET
    @UnitOfWork
	@RolesAllowed("student")
    public Response getAll(@Auth User user) throws URISyntaxException {
    	return Response.ok(new UploadListView(studentService.getById(user.getId()).get())).build();
    }
    
    @GET
    @Path("/{id}")
	@RolesAllowed({"student", "teacher"})
    @UnitOfWork
    public UploadView findById(@Auth User user, @PathParam("id") IntParam id) {
    	Optional<Upload> uploadID = uploadService.getById(id.get());
    	if (uploadID.isPresent()) {
    		Upload upload = uploadID.get();
			if (upload.getStudent().getId() == user.getId() || upload.getWork().getSubject().getTeacher().getId() == user.getId()) {
				return new UploadView(upload, user.getRole());
			}
			else {
				return null;
			}
    	}
    	else {
    		return null;
    	}
    }
    
    //alternative: http://stackoverflow.com/questions/29712554/how-to-download-a-file-using-a-java-rest-service-and-a-data-stream
    @GET
    @Path("/{id}/download")
	@RolesAllowed({"student", "teacher"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @UnitOfWork
	public Response getFile(@Auth User user, @PathParam("id") IntParam id) { 	
    	Optional<Upload> uploadID = uploadService.getById(id.get());
    	if (uploadID.isPresent()) {
    		Upload upload = uploadID.get();
			
    		if (upload.getStudent().getId() == user.getId() || upload.getWork().getSubject().getTeacher().getId() == user.getId()) {				
	    		File file = new File(upload.getURL());		
				ResponseBuilder response = Response.ok((Object) file);
    		response.header("Content-Disposition",
    				"attachment; filename=" + file.getName());
    		response.header(HttpHeaders.LOCATION, "/" + id.get());
    		return response.build();
			}
			else {
				return null;
			}	
    	}
    	else {
    		return Response.noContent().build();
    	}    	
	}
}

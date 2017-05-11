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

import java.io.*;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.hibernate.SessionFactory;

import com.javaeeeee.dwstart.model.Upload;
import com.javaeeeee.dwstart.service.UploadService;
import com.javaeeeee.dwstart.service.implementation.UploadServiceImpl;
import com.javaeeeee.dwstart.views.UploadView;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

@Path("/uploads")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class UploadResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadResource.class);

    private UploadService uploadService;
    
    public UploadResource(SessionFactory sessionFactory) {
        this.uploadService = new UploadServiceImpl(sessionFactory);
    }
    
    @GET
    @UnitOfWork
    public UploadView getAll() {
    	return null;
    }
    
    /*@GET
    @Path("/{id}")
    @UnitOfWork
    public List<Subject> findById(@PathParam("id") IntParam id) {
    	Optional<Teacher> teaID = teacherService.getById(id.get());
    	teaID.get().setSubjects(subjectService.getByTeacher(teaID.get().getId()));
		return teaID.get().getSubjects();
    }*/
    
    
    @GET
    @Path("/{id}")
    @UnitOfWork
    public UploadView findById(@PathParam("id") IntParam id) {
    	Optional<Upload> uploadID = uploadService.getById(id.get());
    	if (uploadID.isPresent()) {
    		Upload upload = uploadID.get();
            return new UploadView(upload);
    	}
    	else {
    		System.out.println("null");
    		return null;
    	}
    }
    
    //alternative: http://stackoverflow.com/questions/29712554/how-to-download-a-file-using-a-java-rest-service-and-a-data-stream
    @GET
    @Path("/{id}/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @UnitOfWork
	public Response getFile(@PathParam("id") IntParam id) { 	
    	Optional<Upload> uploadID = uploadService.getById(id.get());
    	if (uploadID.isPresent()) {
    		Upload upload = uploadID.get();
    		File file = new File(upload.getURL());

    		ResponseBuilder response = Response.ok((Object) file);
    		response.header("Content-Disposition",
    				"attachment; filename=" + file.getName());
    		response.header(HttpHeaders.LOCATION, "/" + id.get());
    		return response.build();
    	}
    	else {
    		return Response.noContent().build();
    	}    	
	}
}

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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;

import com.javaeeeee.dwstart.model.Upload;
import com.javaeeeee.dwstart.model.Work;
import com.javaeeeee.dwstart.service.UploadService;
import com.javaeeeee.dwstart.service.WorkService;
import com.javaeeeee.dwstart.service.implementation.UploadServiceImpl;
import com.javaeeeee.dwstart.service.implementation.WorkServiceImpl;
import com.javaeeeee.dwstart.views.UploadFileView;
import com.javaeeeee.dwstart.views.WorkView;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

@Path("/works")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class WorkResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkResource.class);

    /**
     * The DAO object to manipulate employees.
     */
    private WorkService workService;
    private UploadService uploadService;
    
    public WorkResource(SessionFactory sessionFactory) {
        this.workService = new WorkServiceImpl(sessionFactory);
        this.uploadService = new UploadServiceImpl(sessionFactory);
    }
    
    @GET
    @UnitOfWork
    public WorkView getAll() {
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
    public WorkView findById(@PathParam("id") IntParam id) {
    	Optional<Work> workID = workService.getById(id.get());
    	if (workID.isPresent()) {
    		Work work = workID.get();
            return new WorkView(work);
    	}
    	else {
    		System.out.println("null");
    		return null;
    	}
    }
    
    @GET
    @Path("/{id}/upload")
    @UnitOfWork
    public UploadFileView upload(@PathParam("id") IntParam id) {
    	Optional<Work> workID = workService.getById(id.get());
    	if (workID.isPresent()) {
    		Work work = workID.get();
    		return new UploadFileView(work);
    	}
    	else {
    		System.out.println("null");
    		return null;
    	}
    }
    
    @POST
    @Path("/{id}/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @UnitOfWork
    public Response uploadFile(
            @FormDataParam("filename") InputStream uploadedInputStream,
            @FormDataParam("filename") FormDataContentDisposition fileDetail,
            @FormDataParam("uploadName") String uploadName,
            @PathParam("id") IntParam id) throws URISyntaxException {
    	
    	try { // kick this?
    		// TODO: uploadFileLocation should come from config.yml
            String uploadedFileLocation = "F:/DWUploads/" + fileDetail.getFileName();
            // save it
            writeToFile(uploadedInputStream, uploadedFileLocation);
            
            Upload upload = new Upload(2, id.get(), uploadedFileLocation, uploadName, 0, new Date());
            upload = uploadService.save(upload);
            
            if (upload != null) {
            	return Response.seeOther(new URI("/uploads/" + upload.getId())).entity(upload).build();     
            }
            else
            {
            	return Response.seeOther(new URI("/works/" + id.get())).build();     
            }			   
    	} catch (Exception e) {
    		LOGGER.error("Error: ", e);
    		return Response.seeOther(new URI("/works/" + id.get())).build();
    	}
    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];
        OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
        while ((read = uploadedInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    }
}

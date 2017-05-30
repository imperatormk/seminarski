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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Student;
import com.fikt.seminarski.service.StudentService;
import com.fikt.seminarski.service.implementation.StudentServiceImpl;
import com.fikt.seminarski.views.StudentListView;
import com.fikt.seminarski.views.StudentView;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

@Path("/students")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class StudentResource {

    /**
     * The DAO object to manipulate employees.
     */
    private StudentService studentService;
    
    public StudentResource(SessionFactory sessionFactory) {
        this.studentService = new StudentServiceImpl(sessionFactory);
    }
    
    @GET
    @UnitOfWork
    public StudentListView getAll() {
    	List<Student> lst = studentService.getAll();

    	return new StudentListView(lst);
    }
    
/*
    @GET
    @UnitOfWork
    public StudentListView findByName(
            @QueryParam("name") Optional<String> name
    ) {
        if (name.isPresent()) {
            List<Student> stuList = studentService.findByName(name.get());
            return new EmployeeListView(empName);
        } else {
        	List<Employee> lst = employeeDAO.findAll();
        	//lst.get(0).setFirstName("test");
            //return employeeDAO.findAll();
        	
        	return new EmployeeListView(lst);
        }
    }
*/
/*    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Employee> findById(@PathParam("id") LongParam id) {
        return employeeDAO.findById(id.get());
    }*/
    
    @GET
    @Path("/{id}")
    @UnitOfWork
    public StudentView findById(@PathParam("id") IntParam id) {
    	Optional<Student> stuID = studentService.getById(id.get());
        return new StudentView(stuID.get());
    }
    
    
}

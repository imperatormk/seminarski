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

import javax.annotation.security.PermitAll;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fikt.seminarski.model.OptionalUser;
import com.fikt.seminarski.model.Token;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.persistence.TokenDAO;
import com.fikt.seminarski.persistence.UserDAO;
import com.fikt.seminarski.persistence.implementation.TokenDAOImpl;
import com.fikt.seminarski.persistence.implementation.UserDAOImpl;
import com.fikt.seminarski.views.LoginView;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.views.View;

@Path("/login")
public class LoginResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);
	
	SessionFactory sessionFactory;
	UserDAO userDAO;
	TokenDAO tokenDAO;
	
	public LoginResource(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		userDAO = new UserDAOImpl(sessionFactory);
		tokenDAO = new TokenDAOImpl(sessionFactory, userDAO);
	}
	
	@GET
	@PermitAll
	@UnitOfWork
	@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON })
	public Response login(@Auth OptionalUser user) throws URISyntaxException {
		if (user.getRole() == "guest") {
			return Response.ok(new LoginView()).build();
		}
		else {
			return Response.seeOther(new URI("/uploads/")).build();
		}
	}
	
	@POST
    @UnitOfWork
	public Response loginVer(
			@FormParam("j_username") String uName,
			@FormParam("j_password") String pass,
            @Context HttpServletResponse response) throws URISyntaxException {
		
		Optional<User> user = userDAO.getUserByCred(uName, pass);
		
		Token token = null;
		if (user.isPresent()) {
			int id = user.get().getId();
			token = tokenDAO.findOrCreateTokenForUser(id);
		}
		else {
			return Response.seeOther(new URI("/login")).build();
		}
		
		response.addCookie(new Cookie("auth_token", token.getId().toString()));
		response.addCookie(new Cookie("auth_user", String.valueOf(user.get().getId())));
		
		return Response.seeOther(new URI("/uploads")).build();
	}
}

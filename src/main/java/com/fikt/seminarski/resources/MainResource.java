package com.fikt.seminarski.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.hibernate.SessionFactory;

import com.fikt.seminarski.model.Token;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.persistence.TokenDAO;
import com.fikt.seminarski.persistence.UserDAO;
import com.fikt.seminarski.persistence.implementation.TokenDAOImpl;
import com.fikt.seminarski.persistence.implementation.UserDAOImpl;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/")
public class MainResource {
	
	private TokenDAO tokenDAO;
	private UserDAO userDAO;
    
    public MainResource(SessionFactory sessionFactory) {
    	this.userDAO = new UserDAOImpl(sessionFactory);
        this.tokenDAO = new TokenDAOImpl(sessionFactory, userDAO);
    }
	
	@GET
	public Response main() {
		return Response.status(200).build();
	}
	
	@PermitAll
	@GET 
	@UnitOfWork
	@Path("/logout")
	public Response logout(@Auth User user) throws URISyntaxException {
		if (user.getRole() == "guest") {
			return Response.seeOther(new URI("/login")).build();
		}
		else {
			Optional<Token> token = tokenDAO.findTokenForUser(user);
			if (token.isPresent()) {
				Optional<Token> tokenUpd = tokenDAO.expireToken(token.get());
				
				if (tokenUpd.isPresent()) {
					return Response.seeOther(new URI("/login")).build();
				}
				else {
					return Response.seeOther(new URI("/")).build();
				}	
			}
			else {
				return Response.seeOther(new URI("/login")).build();
			}	
		}
		/*return Response.status(401).build().seeOther(new URI("/")).build();
		Response res;
		res.getHeaders().add("ClearAuthenticationCache", value);*/
		
		//return new LogoutView();
	}
}

package com.fikt.seminarski.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.Optional;
import java.sql.Timestamp;

import com.fikt.seminarski.model.Token;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.model.OptionalUser;
import com.fikt.seminarski.persistence.TokenDAO;
import com.fikt.seminarski.persistence.UserDAO;

public class OptionalAuthenticator implements Authenticator<CustomCredentials, OptionalUser> {
	  private TokenDAO tokenDAO;
	  private UserDAO userDAO;

	  public OptionalAuthenticator(TokenDAO tokenDAO, UserDAO userDAO) {
	    this.tokenDAO = tokenDAO;
	    this.userDAO = userDAO;
	  }

	  @Override
	  @UnitOfWork
	  public Optional<OptionalUser> authenticate(CustomCredentials credentials) throws AuthenticationException {
		OptionalUser authenticatedUser = new OptionalUser();
	
	    Optional<User> retUser = userDAO.getUser(credentials.getUserId());
	    
	    OptionalUser oUser = null;
	    if (retUser.isPresent()) {
			User uUser = retUser.get();
	    	oUser = new OptionalUser(uUser);
	    }
	    
	    Optional<OptionalUser> user = Optional.ofNullable(oUser);

	    if (user.isPresent()) {
	    	OptionalUser userModel = user.get();
	      Optional<Token> token = tokenDAO.findTokenForUser(userModel, 0);

	      if (token.isPresent()) {
	    	  Token tokenModel = token.get();

	        if (tokenModel.getId().equals(credentials.getToken()) && tokenModel.getDate().after(new Timestamp(System.currentTimeMillis()))) {
	          authenticatedUser = user.get();
	        }
	      }
	    }

	    return Optional.ofNullable(authenticatedUser);
	  }
	}
package com.fikt.seminarski.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.Optional;
import java.sql.Timestamp;

import com.fikt.seminarski.model.Token;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.persistence.TokenDAO;
import com.fikt.seminarski.persistence.UserDAO;

public class CustomAuthenticator implements Authenticator<CustomCredentials, User> {
  private TokenDAO tokenDAO;
  private UserDAO userDAO;

  public CustomAuthenticator(TokenDAO tokenDAO, UserDAO userDAO) {
    this.tokenDAO = tokenDAO;
    this.userDAO = userDAO;
  }

  @Override
  @UnitOfWork
  public Optional<User> authenticate(CustomCredentials credentials) throws AuthenticationException {
	User authenticatedUser = null;
	
    Optional<User> user = userDAO.getUser(credentials.getUserId());

    if (user.isPresent()) {
      User userModel = user.get();
      Optional<Token> token = tokenDAO.findTokenForUser(userModel);

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
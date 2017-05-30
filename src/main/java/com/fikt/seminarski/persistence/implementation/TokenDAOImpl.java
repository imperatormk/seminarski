package com.fikt.seminarski.persistence.implementation;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.fikt.seminarski.model.Token;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.model.OptionalUser;
import com.fikt.seminarski.persistence.TokenDAO;
import com.fikt.seminarski.persistence.UserDAO;

import io.dropwizard.hibernate.AbstractDAO;
import java.sql.Timestamp;

public class TokenDAOImpl extends AbstractDAO<Token> implements TokenDAO {
	  private UserDAO userDAO;

	  public TokenDAOImpl(SessionFactory sessionFactory, UserDAO userDAO) {
	    super(sessionFactory);
	    this.userDAO = userDAO;
	  }
	  
	  private Long dayToMiliseconds(int days){ //util?
		    Long result = Long.valueOf(days * 24 * 60 * 60 * 1000);
		    return result;
		}

	  public Token findOrCreateTokenForUser(int userId) {
	    Optional<User> foundUser = userDAO.getUser(userId);
	    Optional<Token> token = Optional.empty();

	    if (foundUser.isPresent()) {
	      User user = foundUser.get();
	      token = findTokenForUser(user);

	      if (!token.isPresent()) {
	        Token model = new Token();
	        model.setUser(user);
	        model.setId(UUID.randomUUID());
	        model.setDate(new Timestamp(System.currentTimeMillis() + dayToMiliseconds(2)));
	        return persist(model);
	      }
	      else {
	    	  Token model = token.get();
	    	  model.setDate(new Timestamp(System.currentTimeMillis() + dayToMiliseconds(2)));
		      return persist(model);
	      }
	    }

	    return token.orElse(null);
	  }

	  public Optional<Token> findTokenForUser(User user) {
	    Criteria criteria = criteria()
	      .createAlias("user", "u")
	      .add(Restrictions.eq("u.id", user.getId())).add(Restrictions.gt("expiryDate", new Timestamp(System.currentTimeMillis())));

	    return Optional.ofNullable(uniqueResult(criteria));
	  }
	  
	  public Optional<Token> findTokenForUser(OptionalUser user, int par1) {
	    Criteria criteria = criteria()
	      .createAlias("user", "u")
	      .add(Restrictions.eq("u.id", user.getId())).add(Restrictions.gt("expiryDate", new Timestamp(System.currentTimeMillis())));

	    return Optional.ofNullable(uniqueResult(criteria));
	  }
	  
	  public Optional<Token> expireToken(Token token) {
		token.setDate(new Timestamp(500));
		return Optional.ofNullable(persist(token));
	  }
} 
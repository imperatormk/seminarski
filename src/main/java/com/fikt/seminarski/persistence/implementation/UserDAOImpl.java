package com.fikt.seminarski.persistence.implementation;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.fikt.seminarski.model.Token;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.persistence.UserDAO;

import java.util.Optional;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
  public UserDAOImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public Optional<User> getUser(int userId) {
    return Optional.ofNullable(get(userId));
  }

  public User createUser(User userToCreate) {
    return persist(userToCreate);
  }
  
  public void delete(User u) {
	currentSession().delete(u.getId());
  }
  
  public Optional<User> getUserByCred(String uName, String pass) {  
	  Criteria criteria = criteria().add(Restrictions.eq("username", uName)).add(Restrictions.eq("password", pass));
	  return Optional.ofNullable(uniqueResult(criteria));
  }
}
package com.fikt.seminarski.persistence;

import java.util.Optional;

import com.fikt.seminarski.model.User;

public interface UserDAO {
	public Optional<User> getUser(int userId);
	
	public User createUser(User userToCreate);

	public void delete(User u);
	
	public Optional<User> getUserByCred(String uName, String pass); 
}

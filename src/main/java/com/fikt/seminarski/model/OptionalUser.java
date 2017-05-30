package com.fikt.seminarski.model;

public class OptionalUser extends User {
	User user = null;
	String role = "guest";
	int id = -1;
	
	public OptionalUser() {
    }
	
	public OptionalUser(User user) {
        this.user = user;
		this.role = user.getRole();
		this.id = user.getId();
    }

	public String getRole() {
		return role;
	}
	
	public int getId() {
		return id;
	}
}

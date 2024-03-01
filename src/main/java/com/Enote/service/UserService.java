package com.Enote.service;


import com.Enote.entity.User;


public interface UserService {
	public User saveUser(User user);

	public void removSessionMessage();
	
	public boolean existEmailChack(String email);
}

package com.nativapps.prueba.service;

import com.nativapps.prueba.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.User;

public interface UserService {

	User findById(int id);
	
	User findBySso(String sso);
	
}
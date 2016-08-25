package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.User;

public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
}


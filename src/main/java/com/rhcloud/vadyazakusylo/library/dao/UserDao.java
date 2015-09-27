package com.rhcloud.vadyazakusylo.library.dao;

import java.util.List;

import com.rhcloud.vadyazakusylo.library.entity.User;

public interface UserDao {

	User getUser(String login);

	List<User> getUserList();
	
	void addUser(User user);

}

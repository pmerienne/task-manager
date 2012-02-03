package com.pmerienne.taskmanager.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pmerienne.taskmanager.shared.exception.UserAlreadyExistsException;
import com.pmerienne.taskmanager.shared.model.User;

@RemoteServiceRelativePath("userService.rpc")
public interface UserService extends RemoteService {

	User login(String login, String password);

	User save(User user) throws UserAlreadyExistsException;

	void delete(User user);

	User findById(String id);

	User getCurrentUser();

	void logout();
	
	List<User> findAll();
}

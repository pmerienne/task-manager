package com.pmerienne.taskmanager.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pmerienne.taskmanager.shared.model.User;

public interface UserServiceAsync {

	void delete(User user, AsyncCallback<Void> callback);

	void login(String login, String password, AsyncCallback<User> callback);

	void save(User user, AsyncCallback<User> callback);

	void findById(String id, AsyncCallback<User> callback);

	void getCurrentUser(AsyncCallback<User> callback);

	void logout(AsyncCallback<Void> callback);

	void findAll(AsyncCallback<List<User>> callback);

}

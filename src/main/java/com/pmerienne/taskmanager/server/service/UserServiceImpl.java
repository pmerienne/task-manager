package com.pmerienne.taskmanager.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.pmerienne.taskmanager.server.repository.UserRepository;
import com.pmerienne.taskmanager.server.security.LoggedUser;
import com.pmerienne.taskmanager.shared.exception.UserAlreadyExistsException;
import com.pmerienne.taskmanager.shared.model.User;
import com.pmerienne.taskmanager.shared.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService, InitializingBean {

	private final static String ADMIN_LOGIN = "admin";

	@Autowired
	private UserRepository userRepository;

	@Override
	public User login(String login, String password) {
		User user = this.userRepository.findByLogin(login);
		if (user != null && user.getPassword().equals(password)) {
			UserDetails userDetails = buildUserDetails(user);
			Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, password,
					userDetails.getAuthorities());
			SecurityContext sc = new SecurityContextImpl();
			sc.setAuthentication(auth);
			SecurityContextHolder.setContext(sc);
			return user;
		} else {
			return null;
		}
	}

	private UserDetails buildUserDetails(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(),
				user.getPassword(), true, true, true, true, authorities);
		return userDetails;
	}

	@Override
	public void logout() {
		LoggedUser.clearAuthentication();
	}

	@Override
	public User save(User user) throws UserAlreadyExistsException {
		if(StringUtils.isEmpty(user.getId())) {
			User existingUser = this.userRepository.findByLogin(user.getLogin());
			if(existingUser != null) {
				throw new UserAlreadyExistsException("L'utilisateur " + user.getLogin() + " existe déjà");
			}
		}
		return this.userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		this.userRepository.delete(user);
	}

	@Override
	public User findById(String id) {
		return this.userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User getCurrentUser() {
		User user = null;
		String login = LoggedUser.getLogin();
		if (login != null && !login.isEmpty()) {
			user = this.userRepository.findByLogin(login);
		}
		return user;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.userRepository.findByLogin(ADMIN_LOGIN) == null) {
			User admin = new User(ADMIN_LOGIN, ADMIN_LOGIN);
			this.userRepository.save(admin);
		}
	}

}

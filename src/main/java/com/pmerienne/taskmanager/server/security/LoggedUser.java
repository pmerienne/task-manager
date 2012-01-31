package com.pmerienne.taskmanager.server.security;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * Represente l'utilisateur connecte dans le context de securite relatif a la
 * session courante.
 * 
 * 
 */
public class LoggedUser {

	protected LoggedUser() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Retourne la collection de {@link GrantedAuthority} que l'utilisateur
	 * connecté possède.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Collection<GrantedAuthority> getAuthorities() {
		return (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}

	/**
	 * Retourne l'identifiant de l'utilisateur connecté. Cette methode retourne
	 * null si aucun utilisateur n'est connecte.
	 * 
	 * @return
	 */
	public static String getLogin() {
		if (LoggedUser.getConnectedUser() != null) {
			return LoggedUser.getConnectedUser().getUsername();
		} else {
			return null;
		}
	}

	private static User getConnectedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof User) {
			return (User) authentication.getPrincipal();
		} else {
			return null;
		}
	}

	public static void clearAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	/**
	 * Récupère la session de l'utilisateur connecté. Cette méthode retourne
	 * <code>null</code> si aucune session n'a été initialisé. Le cycle de vie
	 * de cette session est lié au cycle de vie de la connection assurée par
	 * Spring Security.
	 * 
	 * @see HttpSession
	 * @return
	 */
	public static HttpSession getSession() {
		ServletRequestAttributes attr;
		try {
			attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		} catch (IllegalStateException ex) {
			return null;
		}
		return attr.getRequest().getSession(false);
	}

}
package es.uvigo.esei.dgss.teama.microstories.jsf.controllers;

import java.io.Serializable;
import java.security.Principal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import es.uvigo.esei.dgss.teama.microstories.service.UserEJB;


@Named(value = "loginController")
@RequestScoped
public class LoginController implements Serializable {

	@EJB
	private UserEJB userEJB;
	
	@Inject
	private Principal currentUser;
	
	@Inject
	private HttpServletRequest request;
	
	private String username;
	private String password;
	private boolean error = false;

	public LoginController() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	// login and logout using request is not working
	/*
	public String doLogin() {
		try {
			request.login(this.getUsername(), this.getPassword());
			this.error = false;
			return "index";
			
		} catch (ServletException e) {
			this.error = true;
			return "login";
		}
	}
	
	public String doLogout() throws ServletException {
		request.logout();
		return "login";
	}*/
	
	public String getCurrentUser() {
		HttpSession session = request.getSession();
		return (String) session.getAttribute("username");
	}

	public String login() {
		boolean valid = userEJB.validateUser(username, password);
		
		if (valid) {
			HttpSession session = request.getSession();
			session.setAttribute("username", this.username);
			return "index";
		} else {
			this.error = true;
			return "login";
		}
	}

	public String logout() {
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}

}

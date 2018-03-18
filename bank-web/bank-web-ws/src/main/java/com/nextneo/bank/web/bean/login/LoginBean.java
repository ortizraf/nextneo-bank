package com.nextneo.bank.web.bean.login;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import com.nextneo.bank.integration.dto.UserDto;
import com.nextneo.bank.integration.wrapper.UserLoggedWrapper;
import com.nextneo.bank.web.service.login.LoginService;

@SuppressWarnings("serial")
@Controller
public class LoginBean implements Serializable {
	
	private static final Logger LOGGER = Logger.getLogger( LoginBean.class.getName() );
	
	@Inject
	LoginService loginService;
	
	private UserDto user = new UserDto();
	
	/**
	 * Login
	 * @return
	 */
	public String login() {
		LOGGER.info("user login");

		FacesContext context = FacesContext.getCurrentInstance();

		UserLoggedWrapper userLoggedWrapper = null;;
		try {
			userLoggedWrapper = loginService.login(user);
		} catch (Exception e) {
			e.printStackTrace();
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Unknown error"));
			return null;
		}
		if(userLoggedWrapper != null && userLoggedWrapper.hasPermissionCustomer()) {
			context.getExternalContext().getSessionMap().put("userLogged", userLoggedWrapper);
			return "home?faces-redirect=true";	
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("User not found"));

		return "login?faces-redirect=true";
	}
	
	/**
	 * Logoff
	 * @return
	 */
	public String logoff() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("userLogged");
		return "login?faces-redirect=true";
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

}

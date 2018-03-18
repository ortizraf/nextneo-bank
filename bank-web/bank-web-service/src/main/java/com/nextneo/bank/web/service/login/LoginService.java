package com.nextneo.bank.web.service.login;

import javax.inject.Inject;
import javax.xml.ws.http.HTTPException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.nextneo.bank.integration.dto.UserDto;
import com.nextneo.bank.integration.wrapper.LoginWrapper;
import com.nextneo.bank.integration.wrapper.UserLoggedWrapper;
import com.nextneo.bank.utils.exception.BusinessException;

@Service
public class LoginService {
	
	@Inject
	RestTemplate restTemplate;
	
	public UserLoggedWrapper login(UserDto user) throws BusinessException, Exception {
		LoginWrapper loginWrapper = new LoginWrapper();
		loginWrapper.setUserName(user.getLogin());
		loginWrapper.setPassword(user.getPassword());
		
		UserLoggedWrapper userLoggedWrapper = null;
		try {
			userLoggedWrapper = restTemplate.postForObject("http://localhost:8080/bank-customer-ws/login/doLogin", loginWrapper, UserLoggedWrapper.class);
		} catch (HttpStatusCodeException e) {
			throw new HTTPException(404);
		}
		if(userLoggedWrapper != null && userLoggedWrapper.getLogin() != null) {
			return userLoggedWrapper;
		}	
		return null;
	}

}

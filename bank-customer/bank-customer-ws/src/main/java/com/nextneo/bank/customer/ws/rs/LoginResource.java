package com.nextneo.bank.customer.ws.rs;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nextneo.bank.customer.service.LoginService;
import com.nextneo.bank.customer.service.mapper.UserMapper;
import com.nextneo.bank.integration.dto.UserDto;
import com.nextneo.bank.integration.wrapper.LoginWrapper;
import com.nextneo.bank.integration.wrapper.UserLoggedWrapper;
import com.nextneo.bank.models.entity.User;
import com.nextneo.bank.utils.exception.BusinessException;
import com.nextneo.bank.utils.path.ResourcePath;

@Path(ResourcePath.LOGIN)
@Stateless
public class LoginResource {
	
	private static final Logger LOGGER = Logger.getLogger( LoginResource.class.getName() );
	
	@Inject
	private LoginService service;
	
	@Inject
	private UserMapper userMapper;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String user() {
		return "Login Web Service REST";
	}
	
	@POST
	@Path(ResourcePath.Login.DO_LOGIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doLogin(LoginWrapper loginWrapper) throws BusinessException, Exception {
		LOGGER.info(" doLogin ");
			
		UserDto userDto = null;
		UserLoggedWrapper userLoggedWrapper = null;
		
		try {
			User user = service.doLogin(loginWrapper);
			userDto = userMapper.userToUserDto(user);
			if (userDto != null && userDto.getId() != null) {
				userLoggedWrapper = service.getInfoAccount(userDto);
			}
		} catch (BusinessException be) {
			LOGGER.warning(" AccountWS - addAccount "+be);
			return Response.status(422).entity(be.getErrors()).build();
		} catch (Exception e) {
			LOGGER.severe(" AccountWS - addAccount "+e);
			return Response.status(500).build();
		}
		return Response.ok().entity(userLoggedWrapper).build();
		
	}
	
}

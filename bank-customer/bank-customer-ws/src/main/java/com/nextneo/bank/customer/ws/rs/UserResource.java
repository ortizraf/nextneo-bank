package com.nextneo.bank.customer.ws.rs;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nextneo.bank.customer.service.UserService;
import com.nextneo.bank.customer.service.mapper.UserMapper;
import com.nextneo.bank.integration.dto.UserDto;
import com.nextneo.bank.models.entity.User;
import com.nextneo.bank.utils.exception.BusinessException;
import com.nextneo.bank.utils.path.ResourcePath;

@Path(ResourcePath.USER)
@Stateless
public class UserResource {
	
	private static final Logger LOGGER = Logger.getLogger( UserResource.class.getName() );

	@Inject
	private UserService service;
	
	@Inject
	private UserMapper userMapper;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String user() {
		return "User Web Service REST";
	}
	
	@GET
	@Path(ResourcePath.User.FIND_BY_ID)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto findById(@PathParam("id") long id) throws BusinessException, Exception {
		LOGGER.info(" findById "+id);
		
		User user = service.findById(id);
		UserDto userDto = userMapper.userToUserDto(user);
		
		return userDto;
	}
	
	@POST
	@Path(ResourcePath.User.ADD_CLIENT)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserDto addClient(UserDto userDto) throws BusinessException, Exception {
		LOGGER.info(" addClient ");
		
		User user = userMapper.userDtoToUser(userDto);
		
		user = service.addClient(user);
		userDto = userMapper.userToUserDto(user);

		return userDto;	
	}

}

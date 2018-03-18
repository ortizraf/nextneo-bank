package com.nextneo.bank.customer.ws;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.nextneo.bank.customer.service.UserService;
import com.nextneo.bank.customer.service.mapper.UserMapper;
import com.nextneo.bank.integration.dto.UserDto;
import com.nextneo.bank.models.entity.User;
import com.nextneo.bank.utils.exception.BusinessException;

@WebService
@Stateless
public class UserWS {
	
	private static final Logger LOGGER = Logger.getLogger( UserWS.class.getName() );

	@Inject
	private UserService service;
	
	@Inject
	private UserMapper userMapper;
	
	@WebResult(name="user")
	public UserDto findById(@WebParam(name="id") long id) throws BusinessException, Exception {
		LOGGER.info(" findById "+id);
		
		User user = service.findById(id);
		UserDto userDto = userMapper.userToUserDto(user);
		
		return userDto;
	}
	
	@WebResult(name="user")
	public UserDto addClient(@WebParam(name="user") UserDto userDto) throws BusinessException, Exception {
		LOGGER.info(" addClient ");
		
		User user = userMapper.userDtoToUser(userDto);
		
		user = service.addClient(user);
		userDto = userMapper.userToUserDto(user);

		return userDto;	
	}

}

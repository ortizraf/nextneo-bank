package com.nextneo.bank.customer.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nextneo.bank.integration.dto.GroupRoleDto;
import com.nextneo.bank.integration.dto.UserDto;
import com.nextneo.bank.integration.dto.enums.UserTypeDto;
import com.nextneo.bank.models.entity.GroupRole;
import com.nextneo.bank.models.entity.User;

/**
* The UserMapper class converts Entity to Data Transfer Object
*
* @author  Rafael M Ortiz
* @version 1.0
*/
@Stateless
public class UserMapper {
	
	@Inject
	private GroupRoleMapper groupRoleMapper;
	
	public UserDto userToUserDto(User user) {
		UserDto userDto = null;	
		if(user!=null){	
			userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setLogin(user.getLogin());
			userDto.setPassword(user.getPassword());
			if (user.getType() != null) {
				userDto.setType(UserTypeDto.valueOf(user.getType().name()));
			}
			if (user.getGroupRoles() != null && !user.getGroupRoles().isEmpty()) {
				Set<GroupRoleDto> groupRolesDto = groupRoleMapper.groupRoleListToGroupRoleDtoList(user.getGroupRoles());
				userDto.setGroupRoles(groupRolesDto);
			}
		}
		return userDto;	
	}
	
	public User userDtoToUser(UserDto userDto) {
		User user = null;	
		if(userDto!=null){	
			user = new User();
			user.setId(userDto.getId());
			user.setLogin(userDto.getLogin());
			user.setPassword(userDto.getPassword());
			if (userDto.getGroupRoles() != null && !userDto.getGroupRoles().isEmpty()) {
				Set<GroupRole> groupRoles = groupRoleMapper.groupRoleDtoListToGroupRoleList(userDto.getGroupRoles());
				user.setGroupRoles(groupRoles);
			}
		}
		return user;	
	}
	
	public List<UserDto> userListToUserDtoList(List<User> userList) {
		List<UserDto> userListDto = null;
		if(userList!=null && !userList.isEmpty()){
			userListDto = new ArrayList<>();
			for(User user : userList){
				UserDto userDto = userToUserDto(user);
				userListDto.add(userDto);
			}
		}		
		return userListDto;
	}

	
	public List<User> userDtoListToUserList(List<UserDto> userDtoList) {
		List<User> userList = null;
		if(userDtoList!=null && !userDtoList.isEmpty()){
			userList = new ArrayList<>();
			for(UserDto userDto : userDtoList){
				User user = userDtoToUser(userDto);
				userList.add(user);
			}
		}		
		return userList;
	}

}

package com.nextneo.bank.customer.service.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.nextneo.bank.integration.dto.AccountMovementDto;
import com.nextneo.bank.integration.dto.enums.AccountMovementTypeDto;
import com.nextneo.bank.models.entity.AccountMovement;

/**
* The AccountMapper class converts Entity to Data Transfer Object
*
* @author  Rafael M Ortiz
* @version 1.0
*/
@Stateless
public class AccountMovementMapper {
	
	public AccountMovementDto accountMovementToAccountMovementDto(AccountMovement accountMovement) {		
		AccountMovementDto accountMovementDto = null;	
		if(accountMovement!=null){	
			accountMovementDto = new AccountMovementDto();
			accountMovementDto.setAccountBalance(accountMovement.getAccountBalance());
			accountMovementDto.setLaunchDate(accountMovement.getLaunchDate());
			accountMovementDto.setAuth(accountMovement.getAuth());
			accountMovementDto.setDescription(accountMovement.getDescription());
			accountMovementDto.setValue(accountMovement.getValue());
			accountMovementDto.setType(AccountMovementTypeDto.valueOf(accountMovement.getType().name()));
			accountMovementDto.setAccountId(accountMovement.getAccount().getId());
		}
		return accountMovementDto;
	}
	
	public List<AccountMovementDto> accountMovementListToAccountMovementDtoList(List<AccountMovement> accountMovementList){
		List<AccountMovementDto> accountMovementDtoList = null;
		if(accountMovementList!=null && !accountMovementList.isEmpty()){
			accountMovementDtoList = new ArrayList<>();
			for(AccountMovement accountMovement : accountMovementList){
				AccountMovementDto accountMovementDto = accountMovementToAccountMovementDto(accountMovement);
				accountMovementDtoList.add(accountMovementDto);
			}
		}
		return accountMovementDtoList;	
	}

}

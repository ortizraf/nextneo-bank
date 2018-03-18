package com.nextneo.bank.customer.ws.rs;

import java.util.List;
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

import com.nextneo.bank.customer.service.AccountMovementService;
import com.nextneo.bank.customer.service.mapper.AccountMovementMapper;
import com.nextneo.bank.integration.dto.AccountMovementDto;
import com.nextneo.bank.integration.wrapper.AccountMovementAddWrapper;
import com.nextneo.bank.models.entity.AccountMovement;
import com.nextneo.bank.models.enums.AccountMovementType;
import com.nextneo.bank.utils.exception.BusinessException;
import com.nextneo.bank.utils.path.ResourcePath;

@Path(ResourcePath.ACCOUNT_MOVEMENT)
@Stateless
public class AccountMovementResource {
	
	private static final Logger LOGGER = Logger.getLogger( AccountMovementResource.class.getName() );

	@Inject
	private AccountMovementService service;
	
	@Inject
	private AccountMovementMapper mapper;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String accountMovement() {
		return "Account Movement Web Service REST";
	}
	
	@POST
	@Path(ResourcePath.AccountMovement.ADD_ACCOUNT_MOVEMENT)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AccountMovementDto addAccountMovement(AccountMovementAddWrapper accountMovementAddDto) throws BusinessException, Exception {
		LOGGER.info(" AccountMovementWS - addAccountMovement "+accountMovementAddDto.getValue());
				
		AccountMovementDto accountMovementDto = null;
		
		AccountMovement accountMovement = service.addAccountMovement(accountMovementAddDto.getAccountId(), accountMovementAddDto.getValue(), accountMovementAddDto.getDescription(), AccountMovementType.valueOf(accountMovementAddDto.getType().name()));
		
		accountMovementDto = mapper.accountMovementToAccountMovementDto(accountMovement);
		
		return accountMovementDto;
	}
	
	@GET
	@Path(ResourcePath.AccountMovement.FIND_MOVEMENTS_BY_ACCOUNT_FILTER)
	@Produces(MediaType.APPLICATION_JSON)
	public List<AccountMovementDto> findMovementsByAccount(@PathParam("accountId") long accountId, @PathParam("page") int page, @PathParam("maxResult") int maxResult) throws BusinessException, Exception {
		LOGGER.info(" AccountMovementWS - findMovementsByAccount "+accountId);
		
		List<AccountMovementDto> accountMovementDtoList = null;
		
		List<AccountMovement> accountMovementList = service.findMovementsByAccount(accountId, page, maxResult);
		accountMovementDtoList = mapper.accountMovementListToAccountMovementDtoList(accountMovementList);
		
		return accountMovementDtoList;
	}

}

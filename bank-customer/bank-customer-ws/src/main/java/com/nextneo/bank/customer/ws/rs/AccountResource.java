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
import javax.ws.rs.core.Response;

import com.nextneo.bank.customer.service.AccountService;
import com.nextneo.bank.customer.service.mapper.AccountMapper;
import com.nextneo.bank.integration.dto.AccountDto;
import com.nextneo.bank.models.entity.Account;
import com.nextneo.bank.utils.exception.BusinessException;
import com.nextneo.bank.utils.path.ResourcePath;

@Path(ResourcePath.ACCOUNT)
@Stateless
public class AccountResource {
	
	private static final Logger LOGGER = Logger.getLogger( AccountResource.class.getName() );
	
	@Inject
	private AccountService service;
	
	@Inject
	private AccountMapper accountMapper;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String account() {
		return "Account Web Service REST";
	}
	
	@GET
	@Path(ResourcePath.Account.FIND_ALL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<AccountDto> findAll() throws BusinessException, Exception {
		LOGGER.info(" AccountWS - findAll ");
		List<AccountDto> accountListDto = null;
				
		List<Account> accountList = service.findAll();
		accountListDto = accountMapper.accountListToAccountDtoList(accountList);
		
		return accountListDto;
	}	
	
	@GET
	@Path(ResourcePath.Account.FIND_BY_ID)
	@Produces(MediaType.APPLICATION_JSON)
	public AccountDto findById(@PathParam("id") long id, @PathParam("customerId") long customerId) throws BusinessException, Exception {
		LOGGER.info(" AccountWS - findById "+id);
		AccountDto accountDto = null;
				
		Account account = service.findById(id, customerId);
		accountDto = accountMapper.accountToAccountDto(account);
		
		return accountDto;
	}


	@GET
	@Path(ResourcePath.Account.FIND_BY_CUSTOMER)
	@Produces(MediaType.APPLICATION_JSON)
	public List<AccountDto> findByUser(@PathParam("customerId") long customerId) throws BusinessException, Exception {
		LOGGER.info(" findByUser "+customerId);	
		List<AccountDto> accountListDto = null;
		
		List<Account> accountList = service.findByCustomer(customerId);
		accountListDto = accountMapper.accountListToAccountDtoList(accountList);

		return accountListDto;
	}
	
	@POST
	@Path(ResourcePath.Account.ADD_ACCOUNT)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAccount(AccountDto accountDto) throws BusinessException, Exception {
		LOGGER.info(" AccountWS - addAccount "+accountDto);
		
		try {
			Account account = accountMapper.accountDtoToAccount(accountDto);
			account = service.addAccount(account);	
			accountDto = accountMapper.accountToAccountDto(account);
		} catch (BusinessException be) {
			LOGGER.warning(" AccountWS - addAccount "+be);
			return Response.status(422).entity(be.getErrors()).build();
		} catch (Exception e) {
			LOGGER.severe(" AccountWS - addAccount "+e);
			return Response.status(500).build();
		}
	    return Response.ok().entity(accountDto).build();
	}

}

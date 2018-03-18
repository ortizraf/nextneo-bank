package com.nextneo.bank.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.nextneo.bank.integration.dto.AccountMovementDto;
import com.nextneo.bank.integration.wrapper.UserLoggedWrapper;
import com.nextneo.bank.web.service.HomeService;

@SuppressWarnings("serial")
@Controller
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class HomeBean implements Serializable {
	
	private static final Logger LOGGER = Logger.getLogger( HomeBean.class.getName() );
	
	@Inject
	HomeService homeService;
	
	private BigDecimal accountBalance = new BigDecimal(0);
	
	private List<AccountMovementDto> accountMovementList = new ArrayList<>();
	
	private List<AccountMovementDto> accountMovementFutureList = new ArrayList<>();
	
	public HomeBean() {
		super();
	}

	@PostConstruct
	public void init() {
		UserLoggedWrapper userLogged = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null && context.getExternalContext() != null) {
			userLogged = (UserLoggedWrapper) context.getExternalContext().getSessionMap().get("userLogged");
			if (userLogged != null) {
				LOGGER.info("userId = "+userLogged.getLogin());
			}
			
			try {
				long accountId = userLogged.getAccountsId().iterator().next();
				
				List<AccountMovementDto> accountMovements = homeService.findLastAccountMovements(accountId);
				if (accountMovements != null && !accountMovements.isEmpty()) {
					boolean hasLastAccountBalance = false;
					for (AccountMovementDto accountMovementDto : accountMovements) {
						if (accountMovementDto.getLaunchDate().before(Calendar.getInstance())) {
							accountMovementList.add(accountMovementDto);
							if (!hasLastAccountBalance) {
								accountBalance = accountMovementDto.getAccountBalance();
								hasLastAccountBalance = true;
							}
						} else {
							accountMovementFutureList.add(accountMovementDto);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<AccountMovementDto> getAccountMovementList() {
		return accountMovementList;
	}

	public void setAccountMovementList(List<AccountMovementDto> accountMovementList) {
		this.accountMovementList = accountMovementList;
	}

	public List<AccountMovementDto> getAccountMovementFutureList() {
		return accountMovementFutureList;
	}

	public void setAccountMovementFutureList(List<AccountMovementDto> accountMovementFutureList) {
		this.accountMovementFutureList = accountMovementFutureList;
	}

}

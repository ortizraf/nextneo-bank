package com.nextneo.bank.web.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.xml.ws.http.HTTPException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.nextneo.bank.integration.dto.AccountMovementDto;
import com.nextneo.bank.utils.exception.BusinessException;
import com.nextneo.bank.utils.path.ResourcePath;

@Service
public class HomeService {
	
	@Inject
	RestTemplate restTemplate;
	
	public List<AccountMovementDto> findLastAccountMovements(Long accountId) throws BusinessException, Exception {
		
		AccountMovementDto[] accountMovementList = null;
		try {
			accountMovementList = restTemplate.getForObject("http://localhost:8080/bank-customer-ws"+ResourcePath.ACCOUNT_MOVEMENT+"/findMovementsByAccount/"+accountId+"/1/10", AccountMovementDto[].class);
		} catch (HttpStatusCodeException e) {
			throw new HTTPException(404);
		}
		if(accountMovementList != null && accountMovementList.length > 0) {
			return Arrays.asList(accountMovementList);
		}	
		return null;
	}

}

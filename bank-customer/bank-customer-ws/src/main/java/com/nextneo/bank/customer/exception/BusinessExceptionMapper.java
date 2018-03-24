package com.nextneo.bank.customer.exception;

import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nextneo.bank.utils.errors.Errors;
import com.nextneo.bank.utils.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
	
	private static final Logger LOGGER = Logger.getLogger( BusinessExceptionMapper.class.getName() );
	
	public Response toResponse(BusinessException businessException) {	
		LOGGER.severe(businessException.getMessage());
		if (businessException.getErrors() == null) {	
			businessException.setErrors(new Errors());		
		}
		if (!businessException.getErrors().hasErrors()) {
			businessException.getErrors().addError(businessException.getMessage());
		}
		
        return Response.status(422).entity(businessException.getErrors()).build();
    }

}
package com.nextneo.bank.customer.exception;

import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {
	
	private static final Logger LOGGER = Logger.getLogger( CustomExceptionMapper.class.getName() );

	public Response toResponse(Exception exception) {
		
		LOGGER.severe(exception.getMessage());
		
        return Response.status(500).entity("500 Internal Server Error").type("text/plain").build();
    }

}
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
import javax.ws.rs.core.Response;

import com.nextneo.bank.customer.service.CustomerService;
import com.nextneo.bank.customer.service.mapper.CustomerMapper;
import com.nextneo.bank.integration.dto.CustomerDto;
import com.nextneo.bank.models.entity.Customer;
import com.nextneo.bank.utils.exception.BusinessException;
import com.nextneo.bank.utils.path.ResourcePath;

@Path(ResourcePath.CUSTOMER)
@Stateless
public class CustomerResource {
	
	private static final Logger LOGGER = Logger.getLogger( CustomerResource.class.getName() );

	@Inject
	private CustomerService service;
	
	@Inject
	private CustomerMapper customerMapper;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String user() {
		return "User Web Service REST";
	}
	
	@GET
	@Path(ResourcePath.Customer.FIND_BY_ID)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerDto findById(@PathParam("id") long id) throws BusinessException, Exception {
		LOGGER.info(" findById "+id);
		
		Customer customer = service.findById(id);
		CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
		
		return customerDto;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(CustomerDto customerDto) throws BusinessException, Exception {
		LOGGER.info(" addClient ");
		
		Customer customer = customerMapper.customerDtoToCustomer(customerDto);
		try {
			customer = service.addCustomer(customer);
			customerDto = customerMapper.customerToCustomerDto(customer);
		} catch (BusinessException be) {
			LOGGER.warning(" AccountWS - addAccount "+be);
			return Response.status(422).entity(be.getErrors()).build();
		} catch (Exception e) {
			LOGGER.severe(" AccountWS - addAccount "+e);
			return Response.status(500).build();
		}
		return Response.ok().entity(customerDto).build();
	}

}

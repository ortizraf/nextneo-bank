package com.nextneo.bank.customer.service.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.nextneo.bank.integration.dto.CustomerDto;
import com.nextneo.bank.integration.dto.UserDto;
import com.nextneo.bank.integration.dto.enums.UserTypeDto;
import com.nextneo.bank.models.entity.Customer;

/**
* The UserMapper class converts Entity to Data Transfer Object
*
* @author  Rafael M Ortiz
* @version 1.0
*/
@Stateless
public class CustomerMapper {
	
	public CustomerDto customerToCustomerDto(Customer customer) {
		CustomerDto customerDto = null;	
		if(customer!=null){	
			customerDto = new CustomerDto();
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setLastName(customer.getLastName());
			customerDto.setDocument(customer.getDocument());
			if(customer.getUser()!=null){
				customerDto.setUser(new UserDto(customer.getUser().getId(), customer.getUser().getLastAccess(),
						customer.getUser().getLogin(), customer.getUser().getPassword(),
						UserTypeDto.valueOf(customer.getUser().getType().name())));
			}
		}
		return customerDto;	
	}
	
	public Customer customerDtoToCustomer(CustomerDto customerDto) {
		Customer customer = null;	
		if(customerDto!=null){	
			customer = new Customer();
			customer.setId(customerDto.getId());
			customer.setName(customerDto.getName());
			customer.setLastName(customerDto.getLastName());
			customer.setDocument(customerDto.getDocument());
		}
		return customer;	
	}
	
	public List<CustomerDto> customerListToCustomerDtoList(List<Customer> customerList) {
		List<CustomerDto> customerListDto = null;
		if(customerList!=null && !customerList.isEmpty()){
			customerListDto = new ArrayList<>();
			for(Customer customer : customerList){
				CustomerDto customerDto = customerToCustomerDto(customer);
				customerListDto.add(customerDto);
			}
		}		
		return customerListDto;
	}

	
	public List<Customer> customerDtoListToCustomerList(List<CustomerDto> customerDtoList) {
		List<Customer> customerList = null;
		if(customerDtoList!=null && !customerDtoList.isEmpty()){
			customerList = new ArrayList<>();
			for(CustomerDto customerDto : customerDtoList){
				Customer customer = customerDtoToCustomer(customerDto);
				customerList.add(customer);
			}
		}
		return customerList;
	}

}

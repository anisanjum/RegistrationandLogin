package com.learn.fundtransfer1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.fundtransfer1.Exception.DuplicateEntryException;
import com.learn.fundtransfer1.entity.Customer;
import com.learn.fundtransfer1.repository.CustomerRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	/*
	 * @PostMapping("/customer/register") public Status
	 * registerUser(@Valid @RequestBody Customer newCustomer) { List<Customer>
	 * customers = customerRepository.findAll(); System.out.println("New customer: "
	 * + newCustomer.toString());
	 * 
	 * for (Customer customer : customers) { System.out.println("Registered user: "
	 * + newCustomer.toString()); if (customer.equals(newCustomer)) {
	 * System.out.println("User Already exists!"); return
	 * Status.USER_ALREADY_EXISTS; } } customerRepository.save(newCustomer); return
	 * Status.SUCCESS; }
	 */

	@PostMapping("/customer/register") 
	public ResponseEntity<?> registerUser(@Valid @RequestBody Customer newCustomer) throws DuplicateEntryException { 
		List<Customer> customers = customerRepository.findAll(); 
		System.out.println("New customer: " + newCustomer.toString());  
		for(Customer customer : customers) { 
			System.out.println("Registered user: " + newCustomer.toString()); 
			if (customer.equals(newCustomer)) { 
				System.out.println("Customer Already exists!");
				throw new DuplicateEntryException("Duplicate customer: Customer Already Available!"); 
				//return new ResponseEntity<>("Customer already available",HttpStatus.NOT_FOUND);
			} 
		} 
		customerRepository.save(newCustomer); 
		return new ResponseEntity<>("Customer added",HttpStatus.OK); 
	}
	
	@PostMapping("/customer/login")
	public ResponseEntity<?> loginCustomer(@Valid @RequestBody Customer customer) throws Exception{
		List<Customer> customers = customerRepository.findAll();
		for (Customer other : customers) {		
			if (other.equals(customer)) {				
				customer.setLoggedIn(true);
				customerRepository.save(customer);
				System.out.println("Success");
				return new ResponseEntity<>("Success", HttpStatus.OK); 
				//return Status.SUCCESS;
			}			 
		}
		throw new Exception("Student not registered for login");
		//return Status.FAILURE;
	}

	@PostMapping("/customer/logout")
	public Status logoutCustomer(@Valid @RequestBody Customer customer) {
		List<Customer> customers = customerRepository.findAll();
		for (Customer other : customers) {			
			if (other.equals(customer)) {
				customer.setLoggedIn(false);
				customerRepository.save(customer);
				System.out.println("Success");
				return Status.SUCCESS;
			}			 
		}
		System.out.println("FAILURE");
		return Status.FAILURE;
	}
}

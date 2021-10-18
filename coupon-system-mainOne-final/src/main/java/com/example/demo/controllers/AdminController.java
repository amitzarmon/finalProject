package com.example.demo.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Company;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.services.AdminFacade1;

@RestController
@CrossOrigin("*")
///api
@RequestMapping("/api/admin")
public class AdminController {

	AdminFacade1 adminFacade1;

	
	
	
	/**add company
	 * @param company
	 * @return
	 */
	@PostMapping("/companies")
	public ResponseEntity<?> createCompany(@RequestBody Company company, @RequestHeader String token) {
		try {
		Company c =	adminFacade1.addCompany(company);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	/**Find by id one company 
	 * @param companyId
	 * @return company 
	 */
	@GetMapping("/companies/{companyId}")
	public ResponseEntity<?> getCompanyById(@PathVariable int companyId, @RequestHeader String token) {
		try {
			Company company = adminFacade1.getOneCompany(companyId);
			return new ResponseEntity<>(company, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	
	/**get all companies in the system 
	 * @return
	 */
	@GetMapping("/companies")
	public ResponseEntity<?> getAllCompanies( @RequestHeader String token) {
		try {
			List<Company> companies = adminFacade1.getAllCompanies();
			return new ResponseEntity<>(companies, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	/**update company - works
	 * @param company
	 * @return 
	 */
	@PutMapping("/companies")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,  @RequestHeader String token) {
		try {
			adminFacade1.updateCompany(company);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	/**delete company by id - works  
	 * @param companyId
	 * @return
	 */
	@DeleteMapping("/companies/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable int companyId,  @RequestHeader String token) {
		try {
			adminFacade1.deleteCompany(companyId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	

	/**
	 * CUSTOMER methods
	 */
	@PostMapping("/customers")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer,  @RequestHeader String token) {
		try {
			adminFacade1.addCustomer(customer);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}


	@GetMapping("/customers/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable int customerId,  @RequestHeader String token) {
		try {
			Customer customer = adminFacade1.getOneCustomer(customerId);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/customers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader String token ) {
		try {
			Collection<Customer> customers = adminFacade1.getAllCustomers();
			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
//works
	@PutMapping("/customers")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,  @RequestHeader String token) throws Exception {
		try {
			adminFacade1.updateCustomer(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	//work
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId,  @RequestHeader String token) {
		try {
			adminFacade1.deleteCustomer(customerId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	
	public AdminController(AdminFacade1 adminFacade1, TokenUtil tokenUtil) {
		super();
		this.adminFacade1 = adminFacade1;
	}	
}

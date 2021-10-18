package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Company;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.CouponRepository;
import com.example.demo.repositories.CustomerRepositroy;

@Service
@Transactional
public class AdminFacade1 extends ClientFacade1 {
	@Autowired
	public AdminFacade1(CompanyRepository companyRepository, CouponRepository couponRepository,
			CustomerRepositroy customerRepositroy) {
		super(companyRepository, couponRepository, customerRepositroy);
		// TODO Auto-generated constructor stub
	}

	private static final String Adminemail = "admin@admin.com";
	private static final String Adminpassword = "admin";

	/**Done.
	 * Login method for administrator using hard coded credentials for learning
	 * purpose only; real system will check credentials stored in database.
	 */
	@Override
	public int login(String email, String password) throws CouponSystemCore {
		if (email.equals(Adminemail) && password.equals(Adminpassword)) {
			System.out.println("admin logged in sucssesfully");
			return 1;
		} else {
			throw new CouponSystemCore("ERROR!: login Failed." + " please enter valid Email and Password");
		}

	}

	/**Done.
	 * The method adds new company to the database and return the instance after
	 * CouponSystemCore
	 * 
	 * @param {Company} company
	 * @return {Company} company
	 * @throws CouponSystemException
	 */
	public Company addCompany(Company company) throws CouponSystemCore {
		if (company != null) {
			if (!companyRepository.existsByNameOrEmail(company.getName(), company.getEmail())) {
				companyRepository.save(company);
			} else {
				throw new CouponSystemCore("ERROR!: this Email or Name is already in use");
			}

		}
		return company;
	}

	/**Done.
	 * The method updates company email and password in the database; 
	 * or throws exception.
	 * 
	 * @param {@link Company} company
	 * @return void
	 * @throws CouponSystemCore
	 */
	public void updateCompany(Company company) throws CouponSystemCore {
			
			Company companyDB = companyRepository.getById(company.getId());
			if(companyDB.getId()!=company.getId()) {
				throw new CouponSystemCore("ERROR!: this company id do not exsist");
				
			}
			
			if((companyDB.getName()).equals(company.getName())){
				if(!(companyDB.getEmail().equals(company.getEmail())) && companyRepository.existsByEmail(company.getEmail())) {
					throw new CouponSystemCore("ERROR!: Email is already use by another Company");
				}else {
					companyRepository.save(company);					
				}
				
				} else {
					throw new CouponSystemCore("ERROR!: company name cant be changed");
				}
	}
	
	/**
	 * The method delete specific company from database cascading all company
	 * coupons.
	 * 
	 * @param Integer id
	 * @return {@link Company} company
	 * @throws CouponSystemException
	 */
	public void deleteCompany(int companyid) throws CouponSystemCore {
		if (!(companyid > 0)) {
			throw new CouponSystemCore("ERROR!: insert valid id");
		}
			Company company = companyRepository.findById(companyid);
		if (company == null) {
				throw new CouponSystemCore("ERROR!: Company not found insert valid id");
			}
				companyRepository.deleteById(companyid);

	}

	/**Done.
	 * The method returns collection of all customers from database.
	 * 
	 * @return List<Customer> companies
	 * @throws CouponSystemCore
	 */
	public List<Company> getAllCompanies() throws CouponSystemCore {
	List<Company> companies = (List<Company>) companyRepository.findAll();
	if(!(companies.isEmpty())) {
	return companies;
	}else {
		throw new CouponSystemCore("Empty List... Add new Company");
	}
	
	}

	/**
	 * done
	 * 
	 * @param companyID
	 * @return company
	 * @throws CouponSystemCore
	 */
	public Company getOneCompany(int companyID) throws CouponSystemCore {
		Company c = companyRepository.findById(companyID);
		if(c!=null) {
			return c;			
		}
		else {
			throw new CouponSystemCore("Error : this company is not exsist insert valid id ");
		}
	}

	/**Done.
	 * The method adds new customer to the database and return the instance after
	 * assigning its id.
	 * 
	 * @param {@link Customer} customer
	 * @return {@link Customer} customer
	 * @throws CouponSystemException
	 */
	public void addCustomer(Customer customer) throws CouponSystemCore {

		Optional<Customer> opt = Optional.ofNullable((customerRepositroy.findByEmail(customer.getEmail())));
		if (!(opt.isPresent())) {

			customerRepositroy.save(customer);

		} else {
			throw new CouponSystemCore("ERROR!: Customer cant have the same Email, this Email address is already in use");
		}
	}

	/**Done.
	 * The method updates customer details in the database; throws
	 * exception.
	 * 
	 * @param {@link Customer} customer
	 * @return 
	 * @throws CouponSystemCore
	 */
	public void updateCustomer(Customer customer) throws CouponSystemCore {
		 	if(customer!=null) {
			Customer customerFromDB = customerRepositroy.findById(customer.getId());
			if (customerFromDB != null) {
				if (!(customerFromDB.getEmail().equals(customer.getEmail()))
						&& customerRepositroy.existsByEmail(customer.getEmail())) {
					throw new CouponSystemCore("ERROR!: Email already exists, owned by another customer");
				}else {
					customerRepositroy.save(customer);
					
				}
			} else {
				throw new CouponSystemCore("ERROR!: This Id does not exist in the system");
			}
		} else {
			throw new CouponSystemCore("ERROR!: Cannot update empty Customer");
		}
	}

	/**
	 * The method delete specific customer from database.
	 * 
	 * 
	 * @param int id
	 * @return {@link Customer} customer
	 * @throws CouponSystemCore
	 */
	public Customer deleteCustomer(int customerId) throws CouponSystemCore {
		Optional<Customer> opt = Optional.ofNullable(customerRepositroy.findById(customerId));
		if (opt.isPresent()) {
			customerRepositroy.delete(opt.get());
			return opt.get();
		}
		throw new CouponSystemCore("ERROR!: This Customer not found, insert valid id");
	}

	

	/**Done.
	 * The method returns collection of all customers from database.
	 * 
	 * @return List<Customer> companies
	 * @throws CouponSystemCore
	 */
	public List<Customer> getAllCustomers() throws CouponSystemCore {
		List<Customer> customers = (List<Customer>) customerRepositroy.findAll();
		if(!(customers.isEmpty())) {
			return customers;
		}else {
			throw new CouponSystemCore("Empty List... Add new Customer");
		}
		
	}

	/**Done.
	 *The method returns specific customer from database using its id.
	 * 
	 * @param customerID
	 * @return customer
	 * @throws CouponSystemCore
	 */
	public Customer getOneCustomer(int customerID) throws CouponSystemCore {
		Customer c = customerRepositroy.findById(customerID);
		if(c!=null) {
			return c;
		}else {
			throw new CouponSystemCore("ERROR!: this Customer is not exsist insert valid id");
		}
	}
}

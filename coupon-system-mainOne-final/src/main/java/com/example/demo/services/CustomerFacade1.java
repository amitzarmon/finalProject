package com.example.demo.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.demo.category.Category;
import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.CouponRepository;
import com.example.demo.repositories.CustomerRepositroy;
@Transactional
@Service
@Scope("prototype")
public class CustomerFacade1 extends ClientFacade1 {
	
	@Autowired
	public CustomerFacade1(CompanyRepository companyRepository, CouponRepository couponRepository,
			CustomerRepositroy customerRepositroy) {
		super(companyRepository, couponRepository, customerRepositroy);
		// TODO Auto-generated constructor stub
	}

//	
//	/**
//	 * @param companyRepository
//	 * @param couponRepository
//	 * @param customerRepositroy
//	 * @param customerID
//	 */
//	public CustomerFacade1(CompanyRepository companyRepository, CouponRepository couponRepository,
//			CustomerRepositroy customerRepositroy, int customerID) {
//		super(companyRepository, couponRepository, customerRepositroy);
//	}

	/**
	 * Login method check credentials of a customer in a database.
	 * 
	 * @param String email
	 * @param String password
	 * @return boolean
	 * @throws CouponSystemException
	 */
	@Override
	public int login(String email, String password) throws CouponSystemCore {
		boolean is = customerRepositroy.existsByEmailAndPassword(email, password);
			if (is) {
				int customerid = customerRepositroy.findByEmail(email).getId();
				System.out.println("Logged in succsesfully");
				return customerid;
			}
			throw new CouponSystemCore("ERROR!: login Failed." + " please enter valid Email and Password");

		}

	
			public Coupon findById(int couponID, int userid) throws CouponSystemCore {
				Optional<Coupon> optCoupon = couponRepository.findById(couponID);
				if (optCoupon.isPresent()) {
					return optCoupon.get();
				} else {
					throw new CouponSystemCore("ERROR!: this coupon is not exists");
				}

			}	
		
			/**
			 * The method adds coupon to logged in customer purchase.
			 * 
			 * @param Coupon coupon
			 * @return boolean
			 * @throws CouponSystemException
			 * 
			 */
			public Coupon purchaseCoupon(int couponID, int userid) throws CouponSystemCore {
					Coupon coupon = findById(couponID,userid);
					System.out.println(couponID);
					
					if(couponRepository.findByCustomersId(userid).contains(coupon)){
						throw new CouponSystemCore("ERROR!: This Coupon is already purchased");
					}
					if (coupon.getAmount() == 0) {
						throw new CouponSystemCore("ERROR!: No Coupon left amount is 0");
					}
					if (coupon.getEndDate().isBefore(LocalDate.now())) {
						throw new CouponSystemCore("ERROR!: This coupon is expaired");
					}
					// reduce coupon amount
					Coupon c = couponRepository.findById(couponID).orElse(null);
					c.setAmount(c.getAmount()-1);
					// add the coupon to the customer
					Customer customer = customerRepositroy.findById(userid);
					customer.addCoupon(coupon);
					return coupon;
					}


//			/**The method returns collection of all the coupons belonging to the customer.
//			 * @List<Coupon> coupons
//			 * @throws CouponSystemCore
//			 */
			public List<Coupon> getAllCustomerCoupons(int userid) throws CouponSystemCore {
				List <Coupon> customercoupons = customerRepositroy.findById(userid).getCoupons();
				System.out.println(customercoupons);
				if (customercoupons.size() > 0) {
					return customercoupons;
				}else {
					throw new CouponSystemCore("ERROR!: customer dosent have coupons");
				}
			}
			
			
			/**
			 * The method returns collection of all the coupons from specific category
			 * belonging to the customer.
			 * 
			 * @param Category category
			 * @return List<Coupon> coupons
			 * @throws CouponSystemException
			 * 
			 */
			public List<Coupon> getCustomerCouponsByCategory(Category category, int userid) throws CouponSystemCore {
				List<Coupon> c = couponRepository.getByCategoryAndCustomersId(category,userid);
				if(c.isEmpty()) {
					throw new CouponSystemCore("ERROR!: Customer you dont have coupons of this category type");
				}
				return c;
			}

			/**
			 * The method gets all coupons belonging to a customer by max price.
			 * 
			 * @param double maxPrice
			 * @return Collection<Coupon> coupons
			 * @throws CouponSystemException
			 * 
			 */
			public Collection<Coupon> getCustomerCouponsByPrice(double price, int userid) throws CouponSystemCore {
				
				Collection<Coupon> c =  couponRepository.findByCustomersIdAndPriceLessThan(userid, price);
				if(c.isEmpty()) {
					throw new CouponSystemCore("ERROR!: Customer it seem like you dont have any Coupons under this price: " + price);
				}
				return c;

			}

			/**
			 * The method returns logged in customer.
			 * 
			 * @return Customer customer
			 * @throws CouponSystemException
			 * 
			 */

			public Customer getCustomerDetails(int userid) throws CouponSystemCore {
			Optional<Customer> c =Optional.ofNullable(customerRepositroy.findById(userid));
				if(c.isPresent()) {
					return  c.get();
				}
				
				throw new CouponSystemCore("You are not logged in");

			}
			
			/**
			 * The method delete coupon from logged in customer purchase.
			 * 
			 * @param Coupon coupon
			 * @return boolean
			 * @throws CouponSystemException
			 * 
			 */
			public boolean deletePurchase(Integer id, int userid) throws CouponSystemCore {
				Coupon couponToDelete = null;
				//get from db
				Optional<Coupon> optCoupon = Optional.ofNullable(couponRepository.getByIdAndCustomersId(id,userid));
				if (!optCoupon.isPresent()) {
					throw new CouponSystemCore("ERROR!: This Coupon is not belong to customer");
				}
				
				couponToDelete = optCoupon.get();
				couponToDelete.setAmount(couponToDelete.getAmount() + 1);
				return getCustomerDetails(userid).getCoupons().remove(couponToDelete);
					
				}


		}

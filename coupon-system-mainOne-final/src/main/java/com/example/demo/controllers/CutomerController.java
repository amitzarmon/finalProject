package com.example.demo.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.category.Category;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.loginManager.LogginMannger;
import com.example.demo.services.CompanyFacade1;
import com.example.demo.services.CustomerFacade1;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/customer")
public class CutomerController {

	CustomerFacade1 customerFacade1;

	@Autowired
	TokenUtil tokenUtil;

	

	@GetMapping("/coupons/{couponId}")
	private ResponseEntity<?> purchaseCoupon(@PathVariable int couponId, @RequestHeader String token) {

		try {
			int userid = tokenUtil.extractUserId(token);
			Coupon c = customerFacade1.purchaseCoupon(couponId, userid);
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping("/coupons")
	private ResponseEntity<?> getAllCustomerCoupons(@RequestHeader String token) {
		int userid = tokenUtil.extractUserId(token);
		try {
			Collection<Coupon> coupons = customerFacade1.getAllCustomerCoupons(userid);
			System.out.println(coupons);
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("coupons-by-category/{category}")
	private ResponseEntity<?> getPurchasedCouponsByCategory(@PathVariable Category category,
			@RequestHeader String token) {
		try {
			int userid = tokenUtil.extractUserId(token);
			Collection<Coupon> coupons = customerFacade1.getCustomerCouponsByCategory(category, userid);
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	
	@GetMapping("/coupons-by-price/{price}")
	private ResponseEntity<?> getPurchasedCouponsByPrice(@PathVariable double price, @RequestHeader String token) {
		try {
			int userid = tokenUtil.extractUserId(token);
			Collection<Coupon> coupons = customerFacade1.getCustomerCouponsByPrice(price, userid);
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	/**
	 * Get customer details
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping("/get/details")
	private ResponseEntity<?> getDetails(@RequestHeader String token) {
		try {
			int userid = tokenUtil.extractUserId(token);
			Customer c = customerFacade1.getCustomerDetails(userid);
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping("/coupon/{id}")
	private ResponseEntity<?> getCoupon(@RequestHeader String token, @PathVariable("id") Integer id) {
		try {
			int userid = tokenUtil.extractUserId(token);
			Coupon c = customerFacade1.findById(id, userid);
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@DeleteMapping("/purchase/delete/{id}")
	private ResponseEntity<?> deletePurchase(@RequestHeader String token, @PathVariable("id") Integer id) {
		try {
			int userid = tokenUtil.extractUserId(token);
			boolean b = customerFacade1.deletePurchase(id, userid);
			System.out.println("is deleted  ? : " + b);
			return new ResponseEntity<>(b, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public CutomerController(CustomerFacade1 customerFacade1, TokenUtil tokenUtil) {
		super();
		this.customerFacade1 = customerFacade1;
		this.tokenUtil = tokenUtil;
	}
}

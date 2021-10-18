package com.example.demo.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.category.Category;
import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.services.CompanyFacade1;

import io.jsonwebtoken.Claims;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/company")
public class CompanyController {

	CompanyFacade1 companyfacade1;

	@Autowired
	TokenUtil tokenUtil;


	/**
	 * COUPON methods
	 */
	@PostMapping("/coupons/{companyId}")
	private ResponseEntity<?> createCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			int userid = tokenUtil.extractUserId(token);
			Coupon c = companyfacade1.addCoupon(coupon, userid);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/coupons/{couponId}")
	private ResponseEntity<?> getCouponById(@PathVariable int couponId, @RequestHeader String token) {
		try {
			int userid = tokenUtil.extractUserId(token);
			Coupon coupon = companyfacade1.findCouponById(couponId, userid);
			return new ResponseEntity<>(coupon, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/coupons")
	private ResponseEntity<?> getAllCoupons(@RequestHeader String token) {
		try {
			int userid = tokenUtil.extractUserId(token);
			List<Coupon> coupons = companyfacade1.getAllCompanyCoupons(userid);
			System.out.println(coupons);
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping(path = "/coupons", consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> updateCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {
		try {
			int userid = tokenUtil.extractUserId(token);
			companyfacade1.updateCoupon(coupon, userid);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/coupons/{couponId}")
	private ResponseEntity<?> deleteCoupon(@PathVariable int couponId, @RequestHeader String token) {
		try {
			int userid = tokenUtil.extractUserId(token);
			companyfacade1.deleteCoupon(couponId, userid);
			System.out.println("coupon deleted: " + couponId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("coupons/category/")
	private ResponseEntity<?> getAllByCategory(@RequestHeader String token, @RequestParam Category category) {
		try {
			int userid = tokenUtil.extractUserId(token);
			List<Coupon> c = companyfacade1.GetAllCompanyCouponsByCategory((category), userid);
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}

	@GetMapping(value = "/coupons/price/{price}")
	private ResponseEntity<?> getAllByPrice(@RequestHeader String token, @PathVariable("price") Double price) {
		try {
			int userid = tokenUtil.extractUserId(token);
			List<Coupon> c = companyfacade1.GetAllCompanyCouponsByPrice(price, userid);
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (CouponSystemCore e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/details")
	private ResponseEntity<?> getDetails(@RequestHeader String token) throws CouponSystemCore {
	try {
		int userid = tokenUtil.extractUserId(token);
		Company c = companyfacade1.getCompanyDetails(userid);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}catch(CouponSystemCore e) {
	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
}
}


	public CompanyController(CompanyFacade1 companyfacade1, TokenUtil tokenUtil) {
		super();
		this.companyfacade1 = companyfacade1;
		this.tokenUtil = tokenUtil;
	}
}


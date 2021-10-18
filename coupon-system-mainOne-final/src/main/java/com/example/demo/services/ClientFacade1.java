package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.CouponRepository;
import com.example.demo.repositories.CustomerRepositroy;


public abstract class ClientFacade1 {
	
	CompanyRepository companyRepository;

	CouponRepository couponRepository;
	
	CustomerRepositroy  customerRepositroy;
	
	public abstract int login(String email, String password) throws CouponSystemCore;

	/**
	 * @param companyRepository
	 * @param couponRepository
	 * @param customerRepositroy
	 */
	public ClientFacade1(CompanyRepository companyRepository, CouponRepository couponRepository,
			CustomerRepositroy customerRepositroy) {
		super();
		this.companyRepository = companyRepository;
		this.couponRepository = couponRepository;
		this.customerRepositroy = customerRepositroy;
	}

	
}

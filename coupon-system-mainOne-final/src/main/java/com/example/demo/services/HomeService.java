package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Coupon;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.repositories.CouponRepository;

@Service 
@Transactional
public class HomeService {

	@Autowired
	private CouponRepository repository;

	public List<Coupon> findAll() throws CouponSystemCore {
		return this.repository.findAll();
	}

}

package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Coupon;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.services.HomeService;

@RestController
@CrossOrigin("*")
public class HomeController {

	@Autowired
	private HomeService service;

	@RequestMapping(value = "/welcome/home", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		try {
		List<Coupon> service = (this.service.findAll());
		return new ResponseEntity<>(service, HttpStatus.OK);
		} catch (CouponSystemCore e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}




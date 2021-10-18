package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.controllers.TokenUtil.UserDetails;
import com.example.demo.entities.Company;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.loginManager.ClientType;
import com.example.demo.loginManager.LogginMannger;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.services.AdminFacade1;
import com.example.demo.services.ClientFacade1;
import com.example.demo.services.CompanyFacade1;
import com.example.demo.services.CustomerFacade1;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LogginController {

	@Autowired
	LogginMannger logginMannger;
	
	@Autowired
	ApplicationContext ctx;
	
	@Autowired
	private TokenUtil tokenUtil;

	@PostMapping
	public String login(@RequestParam ClientType clientType, @RequestParam String userEmail,
			@RequestParam String password) throws CouponSystemCore {

		UserDetails userdetails = new UserDetails(0, userEmail, clientType);
				

		switch (clientType) {
		case Company:
			CompanyFacade1 companyFacade1 = ctx.getBean(CompanyFacade1.class);
			try {
				int companyid = companyFacade1.login(userEmail, password);
				System.out.println("company loggin succses");
				userdetails.userid= companyid;
				String token = tokenUtil.generateToken(userdetails);
				System.out.println(userdetails);
				return token;
				
			}catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
				}
			
		case Customer:
			
			CustomerFacade1 customerFacade1 = ctx.getBean(CustomerFacade1.class);
			try {
				int customerid = customerFacade1.login(userEmail, password);
				System.out.println("customer loggin succses");
				userdetails.userid= customerid;
				String token = tokenUtil.generateToken(userdetails);
				System.out.println(userdetails);
				return token;
			}catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
			}
		
		case Administrator:
		AdminFacade1 adminFacade1 = ctx.getBean(AdminFacade1.class);
		try {
		int admin = adminFacade1.login(userEmail, password);
		userdetails.userid= admin;
		System.out.println("admin logged in sucssesfuly");
		String token = tokenUtil.generateToken(userdetails);
		System.out.println(userdetails);
		return token;

		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
			}
		default:
			return null;
		}
}

}
			
			
			

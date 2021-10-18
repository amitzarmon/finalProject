package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.services.AdminFacade1;

@Component
public class CouponSystemTestAdmin {

	@Autowired
	ApplicationContext ctx;
	
	public void startTestAdmin() {
	
	AdminFacade1 admin =ctx.getBean(AdminFacade1.class);
	
	// =================== get all customers =======================================	
//	try {
//		admin.login("admin@admin.com", "admin");
//		System.out.println(admin.getAllCustomers());
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	// =================== get all companies =======================================	
	
//	try {
//		admin.login("admin@admin.com", "admin");
//		System.out.println(admin.getAllCompanies());
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();	
//	
//	}
	// =================== delete company =======================================
//	try {
//		admin.login("admin@admin.com", "admin");
//		admin.deleteCompany(6);
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();	
//	
//	}
	
// =================== add new company =======================================
//	Company c1 = new Company(0, "aaaa", "aaa@bbb", "1234");
//	try {
//		admin.login("admin@admin.com", "admin");
//		admin.addCompany(c1);
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	
	// =================== add new customer =======================================
//	Customer c1 = new Customer(0, "aaaa", "bbbb", "ccc", "ddd");
//	try {
//		admin.login("admin@admin.com", "admin");
//		admin.addCustomer(c1);
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
	
	// =================== delete customer =======================================
	//try {
	//admin.login("admin@admin.com", "admin");
//		admin.deleteCustomer(5);
	//} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
	////}
	
	// =================== get one company =======================================
		
	//try {
	//admin.login("admin@admin.com", "admin");
//	System.out.println(admin.getOneCompany(4));
//} catch (CouponSystemCore e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
	// =================== update customer  =======================================
	//Customer c1 = new Customer(1, "aaa@aaa.com", "ddd", "fff", "sss");
	//try {
	//admin.login("admin@admin.com", "admin");
	//admin.updateCustomer(c1);
	//} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
	//}
	
	// =================== get one customer  =======================================
	//try {
	//admin.login("admin@admin.com", "admin");
//	System.out.println(c.getOneCustomer(1));
//} catch (CouponSystemCore e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
	
	
	// =================== update company  =======================================
	//Company c1= new Company(1, "glololo@gmail,com", "ggg", "1234");
	//try {
//		c.updateCompany(c1);
	//} catch (CouponSystemCore e) {
		// TODO Auto-generated catch block
//		e.printStackTrace();
	//}
	
	
	
	
	}
	
}


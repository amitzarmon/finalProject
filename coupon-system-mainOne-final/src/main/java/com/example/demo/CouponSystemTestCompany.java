package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.demo.services.AdminFacade1;
import com.example.demo.services.CompanyFacade1;

@Component
public class CouponSystemTestCompany {
	
	
	@Autowired
	ApplicationContext ctx;
	
	public void compantTest() {

		CompanyFacade1 comp =ctx.getBean(CompanyFacade1.class);
	
	
// ===========================LOGGING COMPANY==============================
//	
//try {
//		comp.login("google@google.com", "google123");
//		
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	
	
	//==========GetAllCompanyCouponsByCategory======================================
//	try {
//		comp.login("google@google.com", "google123");
//		System.out.println(c.GetAllCompanyCouponsByCategory(Category.ELECTRICITY));
//		System.out.println(c.GetAllCompanyCouponsByCategory(Category.CLOTHING));
//		System.out.println(c.GetAllCompanyCouponsByCategory(Category.RESTAURANTS));
//	} catch (CouponSystemCore e) {
		// TODO Auto-generated catch block
	//	e.printStackTrace();
	}

	
	
	//===================getCompanyDetails==============================
//	try {
//	comp.login("google@google.com", "google123");
//System.out.println(c.getCompanyDetails());
//	} catch (CouponSystemCore e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
	
	//===================ADD COUPON TO COMPANY==============================
//	Coupon coupon = new Coupon();
//	coupon.setId(57);
//	coupon.setAmount(5);
//	coupon.setCategory(Category.ELECTRICITY);
//	coupon.getCompany();
//	coupon.setDescription("discount 50%!");
//	coupon.setEndDate(LocalDate.of(2021, 8, 15));
//	coupon.setImage("image");
//	coupon.setPrice(99);
//	coupon.setStartDate(LocalDate.now());
//	coupon.setTitle("titlesss");
//
//			try {
//			comp.login("google@google.com", "google123");
//			comp.addCoupon(coupon);
//		} catch (CouponSystemCore e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	//========================DELETE COUPON OF COMPANY================
//	try {
//	comp.login("google@google.com", "google123");
//		comp.deleteCoupon(11);
//	} catch (CouponSystemCore e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	
	//=========================findCouponById=======================
	
//	try {
	//comp.login("google@google.com", "google123");
//	System.out.println(comp.findCouponById(13));
//		
//	} catch (CouponSystemCore e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
	//=========================getAllCompanyCoupons=======================
//	try {
//		comp.login("google@google.com", "google123");
////		comp.addCoupon(coupon);
//	System.out.println(c.getAllCompanyCoupons());
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	//=======================GetAllCompanyCouponsByPrice===============
//	try {
//		comp.login("google@google.com", "google123");
//		System.out.println(comp.GetAllCompanyCouponsByPrice(10));
////		comp.addCoupon(coupon);
////	System.out.println(comp.getAllCompanyCoupons());
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

	//=============updateCoupon======================================
	
//	Coupon coupon = new Coupon();
//	coupon.setId(15);
//	coupon.setAmount(5);
//	coupon.setCategory(Category.RESTAURANTS);
//	coupon.getCompany();
//	coupon.setDescription("discount 50%!!!!!!!");
//	coupon.setEndDate(LocalDate.of(2021, 8, 15));
//	coupon.setImage("image");
//	coupon.setPrice(900);
//	coupon.setStartDate(LocalDate.now());
//	coupon.setTitle("title:)");
	
//	
//try {
//		comp.login("google@google.com", "google123");
//		comp.updateCoupon(coupon);
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	}



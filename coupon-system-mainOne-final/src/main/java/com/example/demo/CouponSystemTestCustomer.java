package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.services.CustomerFacade1;

@Component
public class CouponSystemTestCustomer {

	@Autowired
	ApplicationContext ctx;
	
	public void customerTest() {	
	CustomerFacade1 c =	ctx.getBean(CustomerFacade1.class);

	
	//2	Elior@Hassid.com	Elior	Hassid	eh123
	
	//==============================Loggin==================================	

	
	//	try {
//		c.login("Elior@Hassid.com","eh123");
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//}

	//==============================get customer details ==================================	
	
//	try {
//		c.login("Elior@Hassid.com","eh123");
//		System.out.println(c.getCustomerDetails());
//		catch (Exception e) {
//	// TODO: handle exception		
//		}
	
	//==============================  purchase Coupon ==================================	
//	
//		try {
//			c.login("Elior@Hassid.com","eh123");
//			c.purchaseCoupon(5);
//		} catch (CouponSystemCore e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	//==============================  get all customer coupon ==================================	
//	try {
//	c.login("Elior@Hassid.com","eh123");
//		System.out.println(c.getAllCustomerCoupons());
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	//============================== get Coupon by category ==================================	
//	try {
	//c.login("Elior@Hassid.com","eh123");
//		System.out.println(c.getCustomerCouponsByCategory(Category.ELECTRICITY));
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	//============================== get Customer Coupons By Price ==================================	
//	try {
	//c.login("Elior@Hassid.com","eh123");
//		System.out.println(c.getCustomerCouponsByPrice(19));
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	//============================== Find coupon by id ==================================	
//	try {
		//c.login("Elior@Hassid.com","eh123");
//		System.out.println(c.findById(10));
//	} catch (CouponSystemCore e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		
	

}


}

package com.example.demo.loginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.services.AdminFacade1;
import com.example.demo.services.ClientFacade1;
import com.example.demo.services.CompanyFacade1;
import com.example.demo.services.CustomerFacade1;

@Service
@Component
@Scope("singleton")
public class LogginMannger {

	/**
	 * 
	 */
	public LogginMannger() {
	}

	private ClientFacade1 clientFaceade;

	@Autowired
	ApplicationContext ctx;

	public ClientFacade1 login(String email, String password, ClientType clientType) throws  CouponSystemCore {

		

		switch (clientType) {
		case Company:
			CompanyFacade1 companyFacade1 = ctx.getBean(CompanyFacade1.class);
			try {
				System.out.println("company loggin succses");
				return companyFacade1;
			}catch (Exception e) {
				// TODO: handle exception
				return null;			}
		
		

		case Customer:
			CustomerFacade1 customerFacade1 = ctx.getBean(CustomerFacade1.class);
			try {
				int customerid = customerFacade1.login(email, password);
				System.out.println("customer!! loggin succses");
				return customerFacade1;
			}catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		
		case Administrator:
		AdminFacade1 adminFacade1 = ctx.getBean(AdminFacade1.class);
		try {
		int admin = clientFaceade.login(email, password);
		System.out.println("admin logged in sucssesfuly@! ! ! ! ! ");
				return adminFacade1;

		}catch (Exception e) {
			// TODO: handle exception
			return null;	
			}
		default:
			return null;
		}
}

}
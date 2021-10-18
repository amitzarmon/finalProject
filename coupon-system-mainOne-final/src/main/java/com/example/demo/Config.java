 package com.example.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.category.Category;
import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.services.AdminFacade1;
import com.example.demo.services.CompanyFacade1;
import com.example.demo.services.CustomerFacade1;

@Configuration
public class Config {

	@Bean
	public CommandLineRunner restore(ApplicationContext ctx) {
		CommandLineRunner runner = new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {

				AdminFacade1 admin = ctx.getBean(AdminFacade1.class);

				Company COMPANY1 = new Company(1,"Google", "google@google.com", "google123");
				Company COMPANY2 = new Company(2,"Amazon", "amazon@amazon.com", "amazon123");
				Company COMPANY3 = new Company(3,"Apple", "apple@apple.com", "apple123");
				Company COMPANY4 = new Company(4,"Nestle", "nestle@nestle.com", "nestle123");
				Company COMPANY5 = new Company(5,"LG", "lg@lg.com", "lg123");

				Customer CUSTOMER1 = new Customer("Amit", "Zarmon", "Amit@Zarmon.com", "am123");
				Customer CUSTOMER2 = new Customer("David", "Hassid", "David@Hassid.com", "dv123");
				Customer CUSTOMER3 = new Customer("Mor", "Hod", "Mora@Hod.com", "mh123");
				Customer CUSTOMER4 = new Customer("Efi", "Elias", "Efi@Elias.com", "ee123");
				Customer CUSTOMER5 = new Customer("Daniel", "Alem", "Daniel@Alem.com", "da123");

				Coupon COUPON1 = new Coupon(1, COMPANY1, Category.TRAVEL, "flight", "50$ discount",
						LocalDate.parse("2021-07-01"), LocalDate.parse("2022-07-01"), 10, 25, "flight.google");
				Coupon COUPON2 = new Coupon(2, COMPANY1, Category.CAMPING, "camping chairs", "4 camping chairs",
						LocalDate.parse("2021-06-01"), LocalDate.parse("2022-06-01"), 15, 50, "chairs.google");
				Coupon COUPON3 = new Coupon(3, COMPANY2, Category.FOOD, "gerber", "4 cups of gerber",
						LocalDate.parse("2021-07-01"), LocalDate.parse("2022-09-01"), 40, 15, "gerber.amazon");
				Coupon COUPON4 = new Coupon(4, COMPANY2, Category.ELECTRICITY, "USB cable", "USB cable type b or c",
						LocalDate.parse("2021-06-10"), LocalDate.parse("2022-09-10"), 50, 10, "cable.amazon");
				Coupon COUPON5 = new Coupon(5, COMPANY3, Category.ELECTRICITY, "color uprade",
						"upgrade to limit colors", LocalDate.parse("2021-05-15"), LocalDate.parse("2022-09-15"), 20, 15,
						"color.apple");
				Coupon COUPON6 = new Coupon(6, COMPANY3, Category.ELECTRICITY, "warranty", "6 months more of warranty",
						LocalDate.parse("2021-01-01"), LocalDate.parse("2022-10-01"), 50, 20, "warranty.apple");
				Coupon COUPON7 = new Coupon(7, COMPANY4, Category.FOOD, "Trix", "1 box of Trix",
						LocalDate.parse("2021-01-01"), LocalDate.parse("2022-01-01"), 100, 10, "trix.nestle");
				Coupon COUPON8 = new Coupon(8, COMPANY4, Category.FOOD, "Cheerios", "0 box of Cheerios",
						LocalDate.parse("2021-06-01"), LocalDate.parse("2022-06-01"), 100, 15, "cheerios.nestle");
				Coupon COUPON9 = new Coupon(9, COMPANY5, Category.ELECTRICITY, "100$ discount",
						"100$ discount only in our website", LocalDate.parse("2021-09-01"),
						LocalDate.parse("2022-09-01"), 15, 75, "discount.lg");
				Coupon COUPON10 = new Coupon(10, COMPANY5, Category.ELECTRICITY, "gift", "surprise gift",
						LocalDate.parse("2021-01-01"), LocalDate.parse("2022-09-01"), 20, 15, "gift.lg");

				CompanyFacade1 company1 = ctx.getBean(CompanyFacade1.class);
				CompanyFacade1 company2 = ctx.getBean(CompanyFacade1.class);
				CompanyFacade1 company3 = ctx.getBean(CompanyFacade1.class);
				CompanyFacade1 company4 = ctx.getBean(CompanyFacade1.class);
				CompanyFacade1 company5 = ctx.getBean(CompanyFacade1.class);

				CustomerFacade1 customer1 = ctx.getBean(CustomerFacade1.class);
				CustomerFacade1 customer2 = ctx.getBean(CustomerFacade1.class);
				CustomerFacade1 customer3 = ctx.getBean(CustomerFacade1.class);
				CustomerFacade1 customer4 = ctx.getBean(CustomerFacade1.class);
				CustomerFacade1 customer5 = ctx.getBean(CustomerFacade1.class);

				try {
					admin.addCompany(COMPANY1);
					admin.addCompany(COMPANY2);
					admin.addCompany(COMPANY3);
					admin.addCompany(COMPANY4);
					admin.addCompany(COMPANY5);
				} catch (CouponSystemCore e) {
					e.printStackTrace();
				}

				company1.login(COMPANY1.getEmail(), COMPANY1.getPassword());
				company2.login(COMPANY2.getEmail(), COMPANY2.getPassword());
				company3.login(COMPANY3.getEmail(), COMPANY3.getPassword());
				company4.login(COMPANY4.getEmail(), COMPANY4.getPassword());
				company5.login(COMPANY5.getEmail(), COMPANY5.getPassword());

				try {
					company1.addCoupon(COUPON1 , COMPANY1.getId());
					company1.addCoupon(COUPON2, COMPANY1.getId());
					company2.addCoupon(COUPON3, COMPANY2.getId());
					company2.addCoupon(COUPON4, COMPANY2.getId());
					company3.addCoupon(COUPON5 , COMPANY3.getId());
					company3.addCoupon(COUPON6, COMPANY3.getId());
					company4.addCoupon(COUPON7 , COMPANY4.getId());
					company4.addCoupon(COUPON8,COMPANY4.getId());
					company5.addCoupon(COUPON9, COMPANY5.getId());
					company5.addCoupon(COUPON10, COMPANY5.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					admin.addCustomer(CUSTOMER1);
					admin.addCustomer(CUSTOMER2);
					admin.addCustomer(CUSTOMER3);
					admin.addCustomer(CUSTOMER4);
					admin.addCustomer(CUSTOMER5);
				} catch (CouponSystemCore e) {
					e.printStackTrace();
				}
			
	
				try {
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					customer1.login(CUSTOMER1.getEmail(), CUSTOMER1.getPassword());
					customer1.purchaseCoupon(COUPON3.getId(), CUSTOMER1.getId());
					customer1.purchaseCoupon(COUPON5.getId(),CUSTOMER1.getId());
					customer1.purchaseCoupon(COUPON7.getId(),CUSTOMER1.getId());
					
					
					
					customer2.login(CUSTOMER2.getEmail(), CUSTOMER2.getPassword());
					customer2.purchaseCoupon(COUPON1.getId(), CUSTOMER2.getId());
					customer2.purchaseCoupon(COUPON6.getId(), CUSTOMER2.getId());
					customer3.login(CUSTOMER3.getEmail(), CUSTOMER3.getPassword());
					customer3.purchaseCoupon(COUPON7.getId(), CUSTOMER3.getId());
					customer3.purchaseCoupon(COUPON8.getId(), CUSTOMER3.getId());
					customer3.purchaseCoupon(COUPON10.getId(), CUSTOMER3.getId());
					customer4.login(CUSTOMER4.getEmail(), CUSTOMER4.getPassword());
					customer4.purchaseCoupon(COUPON2.getId(),CUSTOMER4.getId());
					customer4.purchaseCoupon(COUPON4.getId(),CUSTOMER4.getId());
					customer4.purchaseCoupon(COUPON9.getId(),CUSTOMER4.getId());
					customer5.login(CUSTOMER5.getEmail(), CUSTOMER5.getPassword());
					customer5.purchaseCoupon(COUPON10.getId(),CUSTOMER5.getId());
					customer5.purchaseCoupon(COUPON5.getId(),CUSTOMER5.getId());
					customer5.purchaseCoupon(COUPON6.getId(),CUSTOMER5.getId());
					customer5.purchaseCoupon(COUPON7.getId(),CUSTOMER5.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		return runner;
	}

}
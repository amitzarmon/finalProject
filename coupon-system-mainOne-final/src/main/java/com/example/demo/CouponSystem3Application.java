package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.LoginFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class CouponSystem3Application {

	public static void main(String[] args) {
	ApplicationContext ctx =SpringApplication.run(CouponSystem3Application.class, args);
	
	
	
//	CouponSystemTestAdmin couponSystemTestAdmin = ctx.getBean(CouponSystemTestAdmin.class);
//	couponSystemTestAdmin.startTestAdmin();
//	
//	
//	CouponSystemTestCustomer couponSystemTestCustomer = ctx.getBean(CouponSystemTestCustomer.class);
//	couponSystemTestCustomer.customerTest();
//
//	
//
//	CouponSystemTestCompany couponSystemTestCompany = ctx.getBean(CouponSystemTestCompany.class);
//	couponSystemTestCompany.compantTest();
	}
	
	
	@Bean
	public FilterRegistrationBean<LoginFilter> filterRegistrationBean(LoginFilter loginFilter){
		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(loginFilter);
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}
	
	
}


package com.example.demo.daily;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.print.attribute.standard.Copies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Coupon;
import com.example.demo.repositories.CouponRepository;

@Scope("singleton")
@Component
public class CouponExpirationDailyJob extends Thread{

	@Autowired
	CouponRepository couponRepository;

	boolean quit = false;

	
	@PostConstruct
	void startThreadDaily(){
	start();
	}
	
	@PreDestroy
	void stopThread() {
		stop1();
	}
	
	
	
	public CouponExpirationDailyJob() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void run() {
		while (!this.quit) {
			try {
			 couponRepository.deleteByEndDateIsBefore(LocalDate.now());

				System.out.println("deleted expierd coupons");
				TimeUnit.DAYS.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Interrupted!");
			}
		}
	}

	/**
	 * stop task (attribute quit set true will stop deletion Thread)
	 */

	public void stop1() {
		this.quit = true;
	}
}

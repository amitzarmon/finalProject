package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.category.Category;
import com.example.demo.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
	Coupon getByCompanyId(int coupodId);
	
	Boolean existsByCompanyIdAndTitle(int companyID, String title);
	
	ArrayList<Coupon> findByCategoryAndCompanyId(Category category,int companyID);
	
	Collection<Coupon> findByCompanyId(int companyID);
	
	Collection<Coupon> findByCompanyIdAndPriceLessThan(int companyID,double maxPrice);

	Collection<Coupon> findByCustomersIdAndPriceLessThan(int customerID,double maxPrice);
	
	List<Coupon> getAllCouponByCustomersId(int customerid);
	
	List<Coupon> existsByCompanyId(int companyId);
	
	
	Coupon findCouponById (int coupodId);
	
	ArrayList<Coupon> findByCustomersId(int customersId);

	List<Coupon> getByCategoryAndCustomersId(Category category, int customerID);
	
	Coupon getByIdAndCustomersId(int id, int userid);
	
	void deleteByEndDateIsBefore(LocalDate now);
}

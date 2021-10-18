package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.exceptions.CouponSystemCore;

import lombok.NonNull;

/**
 * CompanyRepository define custom methods to work with database
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	
	/**
	 * The method seek in database company instance by its name and password.
	 * 
	 * @param String name
	 * @param String password
	 * @throws CouponSystemCore
	 */
	boolean existsByNameOrEmail(@NonNull String name, @NonNull String email);
	
	/**
	 * The method seek in database company instance by its email and password.
	 * 
	 * @param String email
	 * @param String password
	 * @throws CouponSystemCore
	 */
	Boolean	existsByEmailAndPassword (@NonNull String email, @NonNull String password);
	
	/**
	 * The method seek in database company instance by its email.
	 * 
	 * @param String email
	 * @throws CouponSystemCore
	 */
	Company findByEmail(String email);
	
	Boolean existsByEmail (String email);

	Company findById(int usedid);

	/**
	 * The method seek in database company instance by its name and password.
	 * 
	 * @param String name
	 * @param String password
	 * @throws CouponSystemCore
	 */
	Company findByNameAndEmail(String name, String email) throws CouponSystemCore;

	
	
	/**
	 * The method seek in database company instance by its name.
	 * 
	 * @param String email
	 * @throws CouponSystemException
	 */
	Company findByName(String name);


}

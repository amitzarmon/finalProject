package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Customer;

public interface CustomerRepositroy extends JpaRepository<Customer, Integer>{


	Customer findByEmail(String email);
	
	Customer findById(int userid);
				
	
	
	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);
	
		
}

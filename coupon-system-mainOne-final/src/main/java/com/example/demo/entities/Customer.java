package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode (of = "id")
@Entity
@Table (name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	@Column (nullable = false)
	@NonNull
	private String firstName;
	
	@Column(nullable = false) 
	@NonNull
	private String lastName;
	@Column (unique = true ,nullable = false)
	@NonNull
	private String email;
	
	@Column(nullable = false)
	@NonNull
	private String password;

	@ManyToMany
	@JoinTable(name = "customer_vs_coupons", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	@Exclude
	@JsonIgnore
	private List<Coupon> coupons;
	
	//add coupon
	public void addCoupon(Coupon coupon) {
		if (coupons == null) {
			coupons = new ArrayList<>();
		}
		coupons.add(coupon);
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

	
	/**
	 * 
	 */
	public Customer() {
	}

	/**
	 * @param id
	 */
	public Customer(int id) {
		super();
		this.id = id;
	}
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public Customer(int id, @NonNull String firstName, @NonNull String lastName, @NonNull String email,
			@NonNull String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}


	

}

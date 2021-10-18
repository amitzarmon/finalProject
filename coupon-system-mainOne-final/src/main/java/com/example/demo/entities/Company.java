package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table (name = "company")
public class Company {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	
	@NonNull
	@Column(unique = true , name = "name")
	private String name;
	@NonNull
	@Column(unique = true , name = "email")
	private String email;
	@NonNull
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL ,  mappedBy = "company")
	@JsonIgnore
	private List<Coupon> coupons;

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	

	public Company(int id) {
		this.id = id;
	}
	

	/**Constructor creates Company instance
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 */
	
	public Company(int id, @NonNull String name, @NonNull String email, @NonNull String password) {
		super();
		this.id = id; 
		this.name = name; 
		this.email = email; 
		this.password = password;
	} 
	
	

	/**
	 * 
	 */
	public Company() {
		super();
	}
	
	
	
	
}
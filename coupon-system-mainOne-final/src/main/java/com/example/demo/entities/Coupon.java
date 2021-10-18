package com.example.demo.entities;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

import com.example.demo.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table (name= "coupon")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated (EnumType.STRING)
	@Column(name = "category_id")
	private Category category;
	@Column (unique = true)
	private String title;
	
	private String description;
	@Check(constraints = "startDate <= endDate") 
	private LocalDate startDate;
	@Check(constraints = "endDate >= CURRENT_DATE")
	private LocalDate endDate;
	private double price;
	private int amount;
	@NonNull
	private String image;

	
	@ManyToOne
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;

// a lot of coupons to a lot of customers
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "customer_vs_coupons", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@JsonIgnore
	List<Customer> customers;


	@Override
	public String toString() {
		return "Coupon [id=" + id + ", category=" + category + ", title=" + title + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", price=" + price + ", amount=" + amount
				+ ", image=" + image + ", company id=" + company.getId() + "]";
	}

	/**
	 * @param id
	 * @param category
	 * @param title
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @param price
	 * @param amount
	 * @param image
	 */
	public Coupon(int id,Company company, Category category, String title, @NonNull String description, LocalDate startDate,
			LocalDate endDate, double price, int amount, @NonNull String image) {
		super();
		this.id = id;
		this.company= company;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.amount = amount;
		this.image = image;
	}





}

	
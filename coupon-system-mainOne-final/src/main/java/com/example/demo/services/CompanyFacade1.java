package com.example.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.demo.category.Category;
import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.exceptions.CouponSystemCore;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.CouponRepository;
import com.example.demo.repositories.CustomerRepositroy;

@Service
@Transactional
@Scope("prototype")
public class CompanyFacade1 extends ClientFacade1 {


	
	/**
	 * Login method check credentials of a company in a database.
	 * 
	 * @param String email
	 * @param String password
	 * @return int
	 * @throws CouponSystemCore
	 */
	@Override
	public int login(String email, String password) throws CouponSystemCore {
		boolean is = companyRepository.existsByEmailAndPassword(email, password);
		if (is) {
			int companyid = companyRepository.findByEmail(email).getId();
			System.out.println("Logged in succsesfully");
			return companyid;
		}
		throw new CouponSystemCore("ERROR! login Failed." + " please enter valid Email and Password");

	}

	@Autowired
	public CompanyFacade1(CompanyRepository companyRepository, CouponRepository couponRepository,
			CustomerRepositroy customerRepositroy) {
		super(companyRepository, couponRepository, customerRepositroy);
		// TODO Auto-generated constructor stub
	}


	/**
	 * The method adds coupon to logged in company. Method also should check
	 * expiration date; 
	 * 
	 * 
	 * @param Coupon coupon
	 * @return Coupon coupon
	 * @throws CouponSystemCore
	 * 
	 */
	public Coupon addCoupon(Coupon coupon, int userid) throws CouponSystemCore {
		

		Company company = this.companyRepository.getById(userid);

		coupon.setCompany(company);
		

		if (company.getCoupons().contains(coupon)) {
			throw new CouponSystemCore("ERROR!:coupon already exists");
		}

		if (!(coupon.getEndDate().isAfter(LocalDate.now()))) {
			throw new CouponSystemCore("ERROR!: coupon is expired , coupon end date is not valid");
		}

		if (!(coupon.getEndDate().isAfter(coupon.getStartDate()))) {
			throw new CouponSystemCore("ERROR!: end date cant be before start date");
		}
		if(couponRepository.existsByCompanyIdAndTitle(userid, coupon.getTitle())) {
			throw new CouponSystemCore("ERROR!: Title should be uniqe you already have coupon by this title");
		}

		couponRepository.save(coupon);
		return coupon;

	}

	/**
	 * The method delete coupon of a company.
	 * 
	 * @param couponID
	 * @throws CouponSystemCore
	 */
	public void deleteCoupon(int couponID, int userid) throws CouponSystemCore {
		

		if (!couponRepository.existsById(couponID)) {
			throw new CouponSystemCore("ERROR!: Coupon is not exsist");
		}
		Coupon c1 = findCouponById(couponID, userid);

		if (c1.getCompany().getId() != userid) {
			throw new CouponSystemCore("ERROR!: This coupon is not belong to this company");
		}

		couponRepository.deleteById(couponID);
	}

	/**
	 * The method returns logged in company.
	 * @return Company Details
	 * @throws CouponSystemCore
	 */
	public Company getCompanyDetails(int userid) throws CouponSystemCore {
	
		Optional<Company> c = Optional.ofNullable(companyRepository.findById(userid));
		System.out.println(c);

		if (c.isEmpty()) {
			throw new CouponSystemCore("ERROR!: There is no company exists");
		}
		return c.get();
	}

	/**
	 *  * The method returns collection of all the coupons from specific category
	 * belonging to the company.
	 * @param category
	 * @return ArrayList<Coupon> coupons
	 * @throws CouponSystemCore
	 */
	public ArrayList<Coupon> GetAllCompanyCouponsByCategory(Category category, int userid) throws CouponSystemCore {
	
		
		ArrayList<Coupon> c = couponRepository.findByCategoryAndCompanyId(category, userid);
	
		if(c.isEmpty()) {
			throw new CouponSystemCore("ERROR!: There is no coupons of this type belong to this company");
		}
		return c;
	}


	/**
	 * The method gets all coupons belonging to a company by max price.
	 * @param maxPrice
	 * @return ArrayList<Coupon> coupons
	 * @throws CouponSystemCore
	 */
	public ArrayList<Coupon> GetAllCompanyCouponsByPrice(double maxPrice, int userid) throws CouponSystemCore {
	
		
		Collection<Coupon> c =couponRepository.findByCompanyIdAndPriceLessThan(userid, maxPrice);
		
		if(c.isEmpty()) {
			throw new CouponSystemCore("there is no coupons low then this price");
		}
		return (ArrayList<Coupon>) c;

	}

	/**
	 * The method updates coupon of a company.
	 * 
	 * @param Coupon coupon
	 * @return boolean
	 * @throws CouponSystemCore
	 * 
	 */
	public boolean updateCoupon(Coupon coupon, int userid) throws CouponSystemCore {
	
		
		if (!couponRepository.existsById(coupon.getId())) {
			throw new CouponSystemCore("ERROR!: coupon is not exsist");
		}

		Coupon c1 = findCouponById(coupon.getId(), userid);
		
		System.out.println(c1);
		if (userid != c1.getCompany().getId()) {
			throw new CouponSystemCore("ERROR!: this coupon is not belong to this company");
		}

		if (c1.getCompany().getId() != userid) {
			throw new CouponSystemCore("ERROR!: this coupon is not belong to this company");
		}

		if ((coupon.getEndDate().isBefore(LocalDate.now()))) {
			throw new CouponSystemCore("ERROR!: coupon is expired , coupon end date is not valid");
		}
		if (coupon.getEndDate().isBefore(coupon.getStartDate())) {
			throw new CouponSystemCore("ERROR!: end date cant be before start date");
		}

		if (!(coupon.getTitle().equals(c1.getTitle())
				&& couponRepository.existsByCompanyIdAndTitle(userid, coupon.getTitle()))) {
			throw new CouponSystemCore("ERROR!: already exists (title should be unique)");
		}
		if (coupon.getAmount() < 1) {
			throw new CouponSystemCore("ERROR!: amount should be grater then 0");
		}
		if (coupon.getPrice() < 0) {
			throw new CouponSystemCore("ERROR!: price should be positive number");
		}
		coupon.setCompany(c1.getCompany());
		
		return couponRepository.save(coupon) != null;
	}

	/** The method gets specific coupon of a company by its id.
	 * @param couponID
	 * @param userid
	 * @return
	 * @throws CouponSystemCore
	 */
	public Coupon findCouponById(int couponID, int userid) throws CouponSystemCore {
	
	

		Optional<Coupon> optCoupon = couponRepository.findById(couponID);

		if (optCoupon.isEmpty()) {
			throw new CouponSystemCore("ERROR!: this coupon is not exists");
		}
		if (userid != optCoupon.get().getCompany().getId()) {
			throw new CouponSystemCore("ERROR!: this coupon is not belong to this company");
		}
		if (optCoupon.isPresent()) {
			return optCoupon.get();
		} else {
			throw new CouponSystemCore("ERROR!:coupon is not exists");
		}

	}

	/**
	 * he method returns collection of all the coupons belonging to the company.
	 * @return List - list of company coupons
	 * @throws CouponSystemCore
	 */
	public List<Coupon> getAllCompanyCoupons(int userid) throws CouponSystemCore {
		List<Coupon> companycoupons = (List<Coupon>) couponRepository.findByCompanyId(userid);
		if(companycoupons.isEmpty()) {
			throw new CouponSystemCore("Your list is empty.. add new Coupon");
		}
		return companycoupons;
	}
	
	
	public Company getCompanyByEmail(String email) {
		return this.companyRepository.findByEmail(email);
	}

	
}

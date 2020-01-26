package com.cosmetica.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetica.Entities.Brand;
import com.cosmetica.Entities.Coupon;
import com.cosmetica.Exceptions.CosmeticaException;
import com.cosmetica.IServices.IBrandService;

@RestController
@RequestMapping("COSMETICA")
public class BrandController {
	
	@Autowired
	IBrandService brandservice;
	
	@GetMapping("/brands")
	 public List<Brand> allBrands() {
		List<Brand> Brand = brandservice.getAll();
		return Brand;
		 
	 }
	 
	 @GetMapping("/brand/search/id/{brand_id}")
	 public Optional <Brand> oneBrandById(@PathVariable("brand_id")int brand_id){
		 
		 if(!brandservice.getOneById(brand_id).isPresent())
	         throw new CosmeticaException(brand_id );
		 return brandservice.getOneById(brand_id);
		 
	 }
	 
	 @GetMapping("/brand/search/un/{username}")
	 public List <Brand> oneBrandByUsername(@PathVariable("username")String username){
		 
		 if(brandservice.getOneByBrandName(username).isEmpty())
	         throw new CosmeticaException(username );
		 return brandservice.getOneByBrandName(username);
		 
	 }

	 @PostMapping("/brand")
	 public void addBrand(@RequestBody Brand Brand) {
		 brandservice.saveOrUpdate(Brand);
		 
	 }
	 
	 @DeleteMapping("/brand/remove/{brand_id}")
	 public void removeBrand(@PathVariable("brand_id")int brand_id) {
		 if(!brandservice.getOneById(brand_id).isPresent())
	         throw new CosmeticaException(brand_id );
		 Brand Brand = brandservice.getOneById(brand_id).get();
		 brandservice.delete(Brand); 
		 
	 }
	 
	 @GetMapping("/brand/coupons/id/{brand_id}")
	 public List<Coupon> brandCouponsById(@PathVariable("brand_id")int brand_id) {
		 if(!brandservice.getOneById(brand_id).isPresent())
	         throw new CosmeticaException(brand_id );
		 Brand Brand = brandservice.getOneById(brand_id).get();
		 return brandservice.getBrandCoupons(Brand); 
	 }
	 
	 @GetMapping("/brand/coupons/un/{username}")
	 public List<Coupon> brandCouponsByUsername(@PathVariable("username")String username) {
		 if(brandservice.getOneByBrandName(username).isEmpty())
	         throw new CosmeticaException(username );
		 List<Brand> Brand = brandservice.getOneByBrandName(username);
		 return brandservice.getBrandCoupons(Brand.get(0)); 
	 }

}

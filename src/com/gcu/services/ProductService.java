package com.gcu.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.ProductBusinessInterface;
import com.gcu.models.Product;


@RestController
@RequestMapping("/service")
public class ProductService {

	@Autowired
	ProductBusinessInterface productBusinessService;
	
	@GetMapping("/products")
	public List<Product> getOrders(){
		List<Product> products = productBusinessService.getProducts();
		return products;
	}
	
	@Autowired
	public void setProductService(ProductBusinessInterface productBusinessService) {
		this.productBusinessService = productBusinessService;
	}
}

package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.ProductDataService;
import com.gcu.models.Product;
import com.gcu.models.UserSession;

public class ProductBusinessService implements ProductBusinessInterface{

// Inject the recipeDataService
	@SuppressWarnings("rawtypes")
	@Autowired
	ProductDataService productDataService;
	
	@Autowired
	UserSession currentUser;
	@Override
	public List<Product> getProducts() {
		// call the recipeDataService to return the recipe list
				return productDataService.findAll();
	}

	@Override
	public int addProduct(Product product, int userId) {
		// call the create dataService function
		return productDataService.create(product, userId);
	}
	
	@Autowired
	public void setProductDataService(ProductDataService productDataService) {
		this.productDataService = productDataService;
	}

	@Override
	public Product findProductById(int productId) {
		Product product = productDataService.findById(productId);
		return product;
	}

	@Override
	public int editProduct(Product product) {
		int success = productDataService.update(product);
		return success;
	}

	@Override
	public int deleteProduct(int productId) {
		int success = productDataService.delete(productId);
		return success;
	}
}

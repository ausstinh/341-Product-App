package com.gcu.business;
import java.util.List;

import com.gcu.models.Product;
public interface ProductBusinessInterface {

		/**
		 * This method will utilize the productDataService and return a list of all the
		 * products in the database.
		 * 
		 * @return list - List(Type Product) Class
		 */
		public List<Product> getProducts();

		/**
		 * This method will take in a product object and will add it to the database,
		 * after the product is added it will return a Boolean.
		 * 
		 * @param product - Product Class (Product that will be added to the database.)
		 * @param uniqueId The foreign key of the user.
		 * @return Boolean Class - (Boolean value depending on the result of the
		 *         dataService.)
		 */
		public int addProduct(Product product, int userId);
		
		public Product findProductById(int productId);
		
		public int editProduct(Product product);
		
		public int deleteProduct(int productId);
}

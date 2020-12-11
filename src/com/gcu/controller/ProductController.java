package com.gcu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.ProductBusinessInterface;
import com.gcu.models.Product;
import com.gcu.models.User;
import com.gcu.models.UserSession;


@Controller
@RequestMapping("/product")
public class ProductController {
	    //Initialize the product Service
		@Autowired
		ProductBusinessInterface productBusinessService;
		
		@Autowired
		UserSession session;
		
		/**
		 * This method will direct the user to the recipe form.
		 * 
		 * @return ModelAndView Object that contains the view of where the user will be directed
		 */
		@RequestMapping(path = "/createProduct", method = RequestMethod.GET)
		public ModelAndView displayProductForm() {
			return new ModelAndView("productForm", "product", new Product());
		}

		/**
		 * This method will post/add the recipe to the database by calling the business service.
		 * 
		 * @param product - Product added to the database
		 * @param result - BindingResult
		 * @return ModelAndView Class
		 */
		@RequestMapping(path = "/postProduct", method = RequestMethod.POST)
		public ModelAndView postProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpSession session) {
			if (result.hasErrors()) {
				return new ModelAndView("productForm", "product", product);
			}

			try {
				User user = (User) session.getAttribute("user");
				// Access Database to post product
				int createProduct = productBusinessService.addProduct(product, user.getId());
				//check if product was created
				if(createProduct == 1) {
					//get all products from database
					List<Product> products = productBusinessService.getProducts();
					 
					return new ModelAndView("userProducts", "product", products);
				}
				else {
					//if product was not created return to product form
					return new ModelAndView("productForm", "product", product);
				}
			}

			catch (Exception e) {
				return new ModelAndView("ErrorPage");
			}
		}
		/**
		 * This method will direct the user to view the User Recipes which are unique to. 
		 * 
		 * @return ModelAndView Class
		 */
		@RequestMapping(path = "/userProducts", method = RequestMethod.GET)
		public ModelAndView viewUserProduct() {
			
			List<Product> products = productBusinessService.getProducts();
			
			return new ModelAndView("userProducts", "product", products);
		}
		
		/**
		 * This method will display the content of the recipe and give the user the ability to edit or delete the recipe.
		 * 
		 * @param recipeName - Used to find the recipe
		 * @param recipeNutritionalInformation - Used to find the recipe
		 * @param recipePrice - Used to find the recipe
		 * @return ModelAndView Class
		 */
		@RequestMapping(path = "/viewProductPost", method = RequestMethod.POST)
		public ModelAndView viewProductPost(int productId) {
			
				
				Product product = productBusinessService.findProductById(productId);
				session.setProductID(product.getId());
				session.setProduct(product);
				return new ModelAndView("ViewProduct", "product", product);
			
		}
		
		/**
		 * This method will call the business service to delete the recipe.
		 * 
		 * @return ModelAndView Class
		 */
		@RequestMapping(path="/deleteProduct", method=RequestMethod.POST)
		public ModelAndView deleteBlog()
		{
			try
			{
				productBusinessService.deleteProduct(session.getProductID());
				
				return this.viewUserProduct();
			}
			
			catch(Exception e)
			{
				return new ModelAndView("ErrorPage");
			}
		}
		
		/**
		 * This method will direct the user to the edit recipe form. 
		 * 
		 * @return ModelAndView Class
		 */
		@RequestMapping(path="/editProductForm", method=RequestMethod.GET)
		public ModelAndView displayEditProductForm()
		{
			return new ModelAndView("EditProduct", "product", productBusinessService.findProductById(session.getProductID()));
		}
		
		/**
		 * This method will call the business service to edit the recipe given and is going to send a recipe
		 * model with the parameters which are going to be updated.
		 * 
		 * @param recipe - Recipe 
		 * @param result - BindingResult
		 * @return
		 */
		@RequestMapping(path="/editProductPost", method=RequestMethod.POST)
		public ModelAndView editBlogPost(@Valid @ModelAttribute("product") Product product, BindingResult result)
		{
			if(result.hasErrors())
			{
				return new ModelAndView("EditProduct", "product", product);
			}
			
			try
			{
				productBusinessService.editProduct(product);				
				return new ModelAndView("ViewProduct", "product", productBusinessService.findProductById(session.getProductID()));
			}
			
			catch(Exception e)
			{
				return new ModelAndView("ErrorPage");
			}
			
		}
		@Autowired
		public void setProductBusinessService(ProductBusinessInterface productBusinessService) {
			this.productBusinessService = productBusinessService;
		}
}

package com.gcu.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product 
{
    private int id;
    
    //Data Validation Annotations
    @NotNull(message="Product name cannot be null.")
    @Size(min=2, max=30, message="Product name must be between 2 and 30 characters.")
    private String name;
    
    //Data Validation Annotations
    @NotNull(message="Product description cannot be null.")
    @Size(min=2, max=100, message="Product description must be between 2 and 100 characters.")
    private String desc;
    
    //Data Validation Annotations
    @NotNull(message="Product price cannot be null.")
    @Min(value=1, message="must be equal or greater than $1")  
    private int price;


    private int user_id;
    /**
     * non-default constructor to take in fields to create new product
     * @param name
     * @param desc
     * @param price
     */
    public Product(int id,String name, String desc, int price, int user_id ) {
        super();
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.user_id = user_id;
    }

    /**
     * default constructor
     */
    public Product () {
        
    }

    // GETTERS AND SETTERS
    
    @Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", desc=" + desc + ", price=" + price + ", user_id=" + user_id
				+ "]";
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
    
}
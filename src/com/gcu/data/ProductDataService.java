package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DataServiceException;
import com.gcu.models.Product;

public class ProductDataService implements DataAccessInterface<Product> {
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

    /**
	 * @see DataAccessInterface
	 */
    @Override
	public int create(Product product, int userId) {
        
        int returnNum = 0;
        
        try {
            // define sql statements
            String sqlInsertCreds = "INSERT INTO products VALUES(NULL, ?, ?, ?, ?)";
            
            try {
                // Inputs information into the database in the products table
                int rows = jdbcTemplateObject.update(sqlInsertCreds, product.getName(),
                        product.getDesc(), product.getPrice(), userId);

                // set return value to number of rows inserted
                returnNum = rows;
            }

            catch (Exception e) {
                e.printStackTrace();
                throw new DataServiceException(e);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
            throw new DataServiceException(e);
        }

        return returnNum;
    }


    /**
	 * @see DataAccessInterface
	 */
	@Override
	public List<Product> findAll() {
		
		String sqlQuery = "SELECT * FROM products";

		List<Product> productList = new ArrayList<Product>();

		try {
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery);

			while (srs.next()) {
				productList.add(new Product(srs.getInt("ID"), srs.getString("NAME"), srs.getString("DESC"),
						srs.getInt("PRICE"), srs.getInt("USER_ID")));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new DataServiceException(e);
		}

		return productList;
	}

	
	/**
	 * setDataSouce takes in a DataSource from our web.xml in order to create a
	 * dataSource and JDBC Template Object used to connect and perform CRUD action
	 * to the database
	 * 
	 * @param ds - DataSource - to connect the sql command to the databses
	 */
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}


	@Override
	public Product findBy(Product object) {
		// TODO Auto-generated method stub
		return null;
	}


	/*
     * Query the database and returns the Product model if it finds it. Returns null if a Product model is not found.
     */
    @Override
	public Product findById(int id) {
    	String sql = "SELECT * FROM products WHERE ID = ?";
    	Product product = null;
    	try {
			// Access the database and Queries for all users and is given a results set with
			// information of all users
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, id);
			// loop through all of the results with the given username
			// assign the return value to the credentials that were found with the given username
			while (srs.next()) {
				product = new Product(srs.getInt("ID"), srs.getString("NAME"), srs.getString("DESC"), srs.getInt("PRICE"), srs.getInt("USER_ID"));
			}
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataServiceException(e);
		}
    }
	/**
     * returns the int of rows that were inserted into the database. business service needs to check if this returns 1 for OK check
     * update the product where the product table in the SQL database
     * @param user
     * @return
     */
    @Override
	public int update(Product product) {
    	int returnNum = 0;
		try {
			// define sql statements
			String sqlUpdateProd = "UPDATE products SET `NAME` = ?, `DESC` = ?, `PRICE` = ? WHERE `products`.`ID` = ?";
			try {
				// Inputs information into the database in the products table
				int rows = jdbcTemplateObject.update(sqlUpdateProd, product.getName(),
						product.getDesc(), product.getPrice(), product.getId());
				// set return value to number of rows inserted
				System.out.println(rows);
				returnNum = rows;
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new DataServiceException(e);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DataServiceException(e);
		}
		return returnNum;
    }
    
    /**
     * returns the int of rows that were deleted from the database. business service needs to check if this returns 1 for OK check
     * Delete the product where the id finds a product in the product table
     * @param product
     * @return
     */
    @Override
	public int delete(int productId) {
    	int returnNum = 0;
		try {
			// define sql statements
			String sqlDeleteProd = "DELETE FROM products WHERE ID = ?";
			try {
				// Inputs information into the database in the products table
				int rows = jdbcTemplateObject.update(sqlDeleteProd, productId);
				// set return value to number of rows inserted
				returnNum = rows;
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new DataServiceException(e);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DataServiceException(e);
		}
		return returnNum;
    }

}

package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DataServiceException;
import com.gcu.models.Credentials;
import com.gcu.models.User;

public class UserDataService {
      /**
     * Default constructor. 
     */
    public UserDataService() {
        // TODO Auto-generated constructor stub
    }
    @SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
    /**
	 * @see DataAccessInterface
	 */
    public User findBy(Credentials c) throws DatabaseException {
        
        String sql = "SELECT * FROM users WHERE USERNAME = ?";
        
        User creds = null;
        
        try {
            // Access the database and Queries for all users and is given a results set with
            // information of all users
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, c.getUsername());
            // loop through all of the results with the given username
            // assign the return value to the credentials that were found with the given username
            while (srs.next()) {
                creds = new User(srs.getInt("ID"), srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"), srs.getString("USERNAME"), srs.getString("PASSWORD"));
            }
            return creds;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataServiceException(e);
        }
    }
    /**
	 * @see DataAccessInterface
	 */
    public int create(User user) {
    	int returnNum = 0;
    	System.out.println(user);
		// Checks if the user is valid
		String sqlValidUser = "SELECT * FROM credentials WHERE USERNAME=?;";
		
		try {

			// check if username exists as well
			SqlRowSet srsFind = jdbcTemplateObject.queryForRowSet(sqlValidUser, user.getUsername());
			
			// continue as long as there no usernames that already exist
			if (srsFind.next() == false) {
				// define sql statements
				
				String sqlInsertCreds = "INSERT INTO credentials(USERNAME, PASSWORD) VALUES(?, ?)";
				String sqlInsertUser = "INSERT INTO users(ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD) VALUES (NULL, ?, ?, ?, ?)";
				System.out.println(sqlInsertUser);
				try {
					// Inputs information into the database for both credentials and user
					// information
					int rows = jdbcTemplateObject.update(sqlInsertCreds, user.getUsername(),
							user.getPassword());

					rows += jdbcTemplateObject.update(sqlInsertUser, user.getFirstName(), user.getLastName(),
							user.getUsername(), user.getPassword());

					// set return value to number of rows inserted
					returnNum = rows;
				}

				catch (Exception e) {
					e.printStackTrace();
					throw new DataServiceException(e);
				}
			}

			else {
				returnNum = -1;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new DataServiceException(e);
		}
		return returnNum;
    }

    /*
     * Query all of the Credential models from the database and return them as a list
     */
    public List<User> findAll() {
    	// Creates a SQL statement to be filled in later
    			String sql = "SELECT * FROM users";
    			
    			// Creates an ArrayList of users that will be filled with all the users from the
    			// database
    			List<User> userList = new ArrayList<User>();

    			try {
    				// Access the database and Queries for all users and is given a results set with
    				// information of all users
    				SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
    				while (srs.next()) {
    					userList.add(new User(srs.getInt("ID"), srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"),
    							srs.getString("USERNAME"),srs.getString("PASSWORD")));
    				}
    			} catch (Exception e) {
    				e.printStackTrace();
    			}

    			return userList;
    }
    /**
	 * setDataSouce takes in a DataSource from our web.xml in order to create a
	 * dataSource and JDBC Template Object used to connect and perform CRUD action
	 * to the database
	 * 
	 * @param ds - DataSource - to connect the sql command to the databases
	 */
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}
}
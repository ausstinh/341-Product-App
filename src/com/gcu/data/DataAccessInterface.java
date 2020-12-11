package com.gcu.data;

import java.util.List;

public interface DataAccessInterface<T> {
	/**
     * This method is used to add an Object to the database.
	 * @param <T>
     * 
     * @param Product - Object that is going to be added to the database
     * @param ID - Used for adding to another table
     * @return integer
     */
    public int create(T object, int userId);
    
    /**
     * This method will return a List of Objects to the BusinessService.
     * @param <T>
     * 
     * @return List<T>
     */
    public List<T> findAll();
    /**
     * This method will return a credentials Object to the BusinessService.
     * @param <T>
     * 
     * @return credentials
     */
    public T findBy(T object);
    
    public T findById(int id);
    
	public int update(T object);
	
	public int delete(int id);
}

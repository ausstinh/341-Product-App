package com.gcu.data;

public class DatabaseException extends Exception {
	/**
	 * Catch database exceptions
	 */
	private static final long serialVersionUID = 2337769903979403513L;
	
	public DatabaseException(Throwable e)
	{
		super(e);
	}
}

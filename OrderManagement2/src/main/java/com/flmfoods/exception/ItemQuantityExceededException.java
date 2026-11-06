package com.flmfoods.exception;

public class ItemQuantityExceededException extends RuntimeException {
	
	
	public ItemQuantityExceededException(String message)
	{
		super(message);
	}

}

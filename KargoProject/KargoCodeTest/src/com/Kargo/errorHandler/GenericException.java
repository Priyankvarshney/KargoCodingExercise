package com.Kargo.errorHandler;

public class GenericException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor-For creating new exception of type Generic Exception
	 */

	public GenericException(String exceptionMessage){
		super(exceptionMessage);
	}

}

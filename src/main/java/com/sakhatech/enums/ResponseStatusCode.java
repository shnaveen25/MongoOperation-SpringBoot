package com.sakhatech.enums;

public enum ResponseStatusCode {

	
	// Success
	/** User profile has been generated as a PDF */
	RESPONSE_SECCESS_1(1001),
	/** User details has been stored */
	RESPONSE_SUCCESS_2(1002),
	/** User details has been reterived */
	RESPONSE_SUCCESS_3(1003),
	
	
	
	
	// Failuer
	/** Error while generation PDF */
	RESPONSE_FAILUER_1(2001),
	/** User Details is not stored */
	RESPONSE_FAILUER_2(2002),
	/** User Not Found */
	RESPONSE_FAILUER_3(2003),
	/** User already exists*/
	RESPONSE_FAILUER_4(2004),
	/** Uploaded file is not a Image */
	RESPONSE_FAILUER_5(2005),
	
	
	
	
	
	// Generic Exception
	/** Exception in UserProfileServiceImpl.addUser() */
	GENERIC_EXCEPTION_1(3001),
	/** Exception in UserProfileServiceImpl.getUserDetailsInPDF() */
	GENERIC_EXCEPTION_2(3002),
	/** Exception in UserProfileServiceImpl.getAllUsers() */
	GENERIC_EXCEPTION_3(3003),
	
	
	
	GENERIC_EXCEPTION(0000);
	
	private int code;
	
	private ResponseStatusCode(int code){
		this.code = code;
	}
	
	public int getValue(){
		return code;
	}
}

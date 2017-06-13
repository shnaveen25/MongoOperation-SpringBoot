package com.sakhatech;

/**
 * This interface contains the constants which has been
 * used throughout the application.
 * 
 * @author Naveen
 * @createdDate 6-Jun-2017
 * @modifiedDate 6-Jun-2017
 *
 */
public interface GlobalConstants {

	// MongoDb database name
	public String DATABASE = "MongoOperation";
	
	public String PDF_FILE = "application/pdf";
	public String SUCCESS = "success";
	
	public String IMAGE_DEST_PATH = "/home/naveen/Demos/UserDocs";
	
	
	/**Generic Exception */
	public static final String GENERIC_EXCEPTION = "Internal server error. Please try again later.";
	
	
	/** Error Messages */
	public static final String PDF_GENERATOR_ERROR = "Exception while generating PDF.";
	public static final String NO_USER_FOUND = "No user found.";
	public static final String USER_EXISTS ="User Details with the same email id is already Exists.";
	public static final String IS_NOT_IMAGE = "Please upload Image.";
	
	/** Success Messages */
	public static final String USER_ADDED = "User details has been Recorded";
	
}

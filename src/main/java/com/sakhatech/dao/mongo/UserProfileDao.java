package com.sakhatech.dao.mongo;

import java.util.List;

import com.sakhatech.dto.UserProfileDto;

/**
 * 
 * 
 * @author Naveen
 *
 */
public interface UserProfileDao {

	public static final String COLLECTION = "UserProfile";
	
	public static final String COL_NAME= "name";
	public static final String COL_EMAIL = "email";
	public static final String COL_MOBILE = "mobile";
	public static final String COL_DOB= "dob";
	public static final String COL_PHOTO_PATH = "imagePath";
	
	/**
	 * The service which stores the details entered by the user
	 * 
	 * @author Naveen
	 * @param user
	 * @createdDate 7-Jun-2017
	 * @modifiedDate 8-Jun-2017
	 * @return String
	 */
	public String addUSer(UserProfileDto user);
	
	/**
	 * The service which reterives the user data stored in collection
	 * 
	 * @author naveen
	 * @param email
	 * @createdDate 7-Jun-2017
	 * @modifiedDate 7-Jun-2017
	 * @return DBObject
	 */
	public UserProfileDto getUser(String email);
	
	/**
	 * 
	 * @author Naveen
	 * @createdDate 9-Jun-2017
	 * @modifiedDate 9-Jun-2017
	 * @return {@link List<{@link UserProfileDto}>
	 */
	public List<UserProfileDto> getAllUsers();
	
}

package com.sakhatech.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sakhatech.dto.UserProfileDto;
import com.sakhatech.response.ResponseData;
import com.sakhatech.response.ResponseError;
import com.sakhatech.service.impl.UserProfileServiceImpl;

/**
 * The REST Controller performs following opeeartions<br>
 * * Stores User details including user picture<br>
 * * Reterives all details stored for user<br>
 * * Generated user profile in PDF formate
 * 
 * @author Naveen
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserProfileController {

	@Autowired
	private UserProfileServiceImpl userProfileService;

	/**
	 * The service API to add user to the collection
	 * 
	 * @author Naveen
	 * @param {@link ResponseEntity}
	 * @return
	 */
	@CrossOrigin(value = "http://localhost:3000")
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> addUser(HttpServletRequest request,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "email") String email, 
			@RequestParam(value = "dob") Date dob,
			@RequestParam(value="mobile") String mobile,
			@RequestParam(value = "photo") MultipartFile photo) {
		
		UserProfileDto user = new UserProfileDto();
		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setPhoto(photo);
		user.setDob(dob);
		
		try {
			return userProfileService.addUser(user, request);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseError.getErrorResponseObject(3005, "Generic Exception");
		}
	}


	/**
	 * The Service API to get User profile in PDF formate
	 * 
	 * @param email
	 * @param response
	 * @return {@link ResponseEntity}
	 */
	@CrossOrigin(value={"http://localhost:3000"})
	@RequestMapping(value = "/generatePDF", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<UserProfileDto>> getUserDetailsInPDF(@RequestParam String email,
			HttpServletResponse response) {

		System.out.println("Requested User : " + email);

		try {
			return userProfileService.getUserProfileAsPDF(email, response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseError.getErrorResponseObject(3005, "Generic Exception");
		}
	}
	
	/**
	 * @author Naveen
	 * @createdDate 9-Jun-2017
	 * @modifiedDate 9-Jun-2017
	 * @return {@link List<{@link UserProfileDto}>
	 */
	@CrossOrigin(value={"http://localhost:3000"})
	@RequestMapping(value="getallusers", method=RequestMethod.GET)
	public ResponseEntity<ResponseData<List<UserProfileDto>>> getAllUsers(){
		
		try{
			return userProfileService.getAllUsers();
		}catch (Exception e) {
			return ResponseError.getErrorResponseObject(3006, "Generic Exception");
		}
	}
	
	
	
	
	
	
	
}

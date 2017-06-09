package com.sakhatech.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.sakhatech.GlobalConstants;
import com.sakhatech.dao.mongo.UserProfileDao;
import com.sakhatech.dto.UserProfileDto;
import com.sakhatech.response.ResponseData;
import com.sakhatech.response.ResponseError;
import com.sakhatech.util.UserPDFGenUtility;

/**
 * 
 * @author Naveen
 * @createdDate 7-Jun-2017
 * @modifiedDate 8-Jun-2017
 * 
 */
@Service("UserProfileService")
public class UserProfileServiceImpl {

	@Autowired
	private UserProfileDao userProfileDao;

	/**
	 * The service method which retrives the user profile in PDF to client
	 * 
	 * @author Naveen
	 * @param email
	 * @createdDate 7-Jun-2017
	 * @modifiedDate 7-Jun-2017
	 * @return {@link ResponseEntity}
	 * @throws Exception 
	 */
	public ResponseEntity<ResponseData<UserProfileDto>> getUserProfileAsPDF(String email,
			HttpServletResponse response) throws Exception {

		UserProfileDto user = userProfileDao.getUser(email);
     
		Document document = new Document();

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);

			document.open();

			UserPDFGenUtility file = new UserPDFGenUtility(user, document);

			file.addFileDetails();
			file.addTitlePage();
			file.addUserProfile();
			document = file.getDocument();

			System.out.println("The size of generated document : " + document.getPageNumber());

			document.close();

			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
			
			return ResponseData.getSuccessResponseObject(1004, user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseError.getErrorResponseObject(3004, "Exception while generating PDF");
		}

	}
	
	/**
	 * 
	 * @author Naveen
	 * @param user
	 * @param request
	 * @createdDate 8-Jun-2017
	 * @modifiedDate 8-Jun-2017
	 * @return {@link ResponseEntity}
	 * @throws Exception
	 */
	public ResponseEntity<ResponseData<String>> addUser(UserProfileDto user, HttpServletRequest request) throws Exception {
		
		UserProfileDto isUserExists = userProfileDao.getUser(user.getEmail());
		
		if(isUserExists != null)
			return ResponseError.getErrorResponseObject(2005, "User already exists with the same email-id");
		
		//String fileName = user.getPhoto().getOriginalFilename();
		File filePath = new File(GlobalConstants.IMAGE_DEST_PATH,user.getEmail());
		System.out.println("The file path" + filePath);
		user.getPhoto().transferTo(filePath);
		
		user.setPhotoPath(filePath);
		user.setPhoto(null);
		
		String msg = userProfileDao.addUSer(user);
		
		if(msg.equals(GlobalConstants.SUCCESS))
			return ResponseData.getSuccessResponseObject(1005, "User has been stored");
		else 				
			return ResponseError.getErrorResponseObject(2006, msg);
	}
	
	/**
	 * 
	 * @author Naveen
	 * @createdDate 9-Jun-2017
	 * @modifiedDate 9-Jun-2017
	 * @return {@link List<{@link UserProfileDto}>
	 */
	public ResponseEntity<ResponseData<List<UserProfileDto>>> getAllUsers(){
		
		List<UserProfileDto> usersFromCollection = userProfileDao.getAllUsers();
		
		if(usersFromCollection == null)
			return ResponseError.getErrorResponseObject(2007, "No Users found");
		
		return ResponseData.getSuccessResponseObject(1006, usersFromCollection);
	}
}

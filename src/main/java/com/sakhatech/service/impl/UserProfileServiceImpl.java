package com.sakhatech.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.sakhatech.GlobalConstants;
import com.sakhatech.dao.mongo.UserProfileDao;
import com.sakhatech.dto.UserProfileDto;
import com.sakhatech.enums.ResponseStatusCode;
import com.sakhatech.response.ResponseData;
import com.sakhatech.response.ResponseError;
import com.sakhatech.response.UserProfileResponse;
import com.sakhatech.util.EncodeDecodeMultipartFile;
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
	
	private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class); 

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
	public ResponseEntity<ResponseData<byte[]>> getUserProfileAsPDF(String email,
			HttpServletResponse response) throws Exception {

		UserProfileDto user = userProfileDao.getUser(email);
     
		Document document = new Document();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, baos);

		document.open();

		UserPDFGenUtility file = new UserPDFGenUtility(user, document);
		file.addFileDetails();
		file.addTitlePage();
		file.addUserProfile();
		
		document = file.getDocument();
		document.close();

/*			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
*/			
			
		byte[] userPic = baos.toByteArray();
			
		return ResponseData.getSuccessResponseObject(
				ResponseStatusCode.RESPONSE_SECCESS_1.getValue(), userPic);	

	}
	
	/**
	 * The Service method to store user data to a colletion
	 * 
	 * @WorkFlow
	 * * The data entered by user will be stored in a MongoDB <br/>
	 * * The file of type entered by user will be stored in the local folder
	 * Specified in the file path <br />
	 * * The path in which the file is stired will be stored in the mongo Collection 
	 * 
	 * @author Naveen
	 * @param user
	 * @param request
	 * @createdDate 8-Jun-2017
	 * @modifiedDate 8-Jun-2017
	 * @return {@link ResponseEntity}
	 * @throws Exception
	 */
	@Deprecated
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
	 * The service to store user data by encoding the image file using {@link Base64} encoder
	 * and store it into a MongoCollection.
	 * 
	 * @author Naveen
	 * @param user
	 * @createdDate 12-Jun-2017
	 * @modifiedDate 12-Jun-2017
	 * @return
	 * @throws IOException 
	 */
	public ResponseEntity<ResponseData<String>> addUser(UserProfileDto user) throws IOException {
		
		UserProfileDto isUserExists = userProfileDao.getUser(user.getEmail());
		
		if(isUserExists != null)
			return ResponseError.getErrorResponseObject(
					ResponseStatusCode.RESPONSE_FAILUER_4.getValue(),
					GlobalConstants.USER_EXISTS);
		
		String userImageBase64 = null;
		
		userImageBase64 = EncodeDecodeMultipartFile.encryptMultipartFile(user.getPhoto());
		user.setPhotoEncodedBase64(userImageBase64);
			
		String msg = userProfileDao.addUSer(user);
			
		if(msg.equals(GlobalConstants.SUCCESS))
			return ResponseData.getSuccessResponseObject(
					ResponseStatusCode.RESPONSE_SUCCESS_2.getValue(), 
					GlobalConstants.USER_ADDED);
		else 				
			return ResponseError.getErrorResponseObject(
					ResponseStatusCode.RESPONSE_FAILUER_2.getValue(), msg);
	
	}
	
	/**
	 * 
	 * @author Naveen
	 * @createdDate 9-Jun-2017
	 * @modifiedDate 13-Jun-2017
	 * @return {@link List<{@link UserProfileDto}>
	 * @throws IOException 
	 */
	public ResponseEntity<ResponseData<List<UserProfileResponse>>> getAllUsers() throws IOException{
			
		List<UserProfileDto> usersFromCollection = userProfileDao.getAllUsers();
		
		UserProfileResponse userProfile = null;
		List<UserProfileResponse> responseDate = new ArrayList<UserProfileResponse>();
		
		if(usersFromCollection == null)
			return ResponseError.getErrorResponseObject(
					ResponseStatusCode.RESPONSE_FAILUER_3.getValue(), 
					GlobalConstants.NO_USER_FOUND);
		
		String userImageBase64 = null;
		for(UserProfileDto eachUser : usersFromCollection){
			userProfile = new UserProfileResponse();
			
			userProfile.setName(eachUser.getName());
			userProfile.setMobile(eachUser.getMobile());
			userProfile.setEmail(eachUser.getEmail());
			userProfile.setDob(eachUser.getDob());
			
			userImageBase64 = eachUser.getPhotoEncodedBase64();
			
			if(userImageBase64 != null){
				MultipartFile userImg = EncodeDecodeMultipartFile.decryptMultiPartFile(
						userImageBase64, eachUser.getName(), "", eachUser.getEmail());
				
				userProfile.setProfilepic(userImg.getBytes());
				
				responseDate.add(userProfile);
			}
		}
		return ResponseData.getSuccessResponseObject(
			ResponseStatusCode.RESPONSE_SUCCESS_3.getValue(), responseDate);
	}
}

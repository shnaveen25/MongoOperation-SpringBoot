package com.sakhatech.controller;

import org.junit.experimental.theories.FromDataPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sakhatech.dto.FileUploaderDto;
import com.sakhatech.response.ResponseData;
import com.sakhatech.response.ResponseError;
import com.sakhatech.service.impl.PDFUploaderServiceImpl;
import com.sakhatech.util.FileManager;

/**
 * 
 * @author Naveen
 * @createdDate 6-Jun-2017
 * @modifiedDate 6-Jun-2017
 */
@RestController
@RequestMapping(value = "/pdf")
public class PDFUploadController {
	
	@Autowired
	private PDFUploaderServiceImpl uploaderService;

	/**
	 * 
	 * @author Naveen
	 * @param file
	 * @param addedBy
	 * @craetedDate 6-Jun-2017
	 * @modifiedDate 6-Jun-2017
	 * @return ResponseEntity<T>
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "content-type=multipart/*")
	public ResponseEntity<ResponseData<String>> uploadPDF(@FromDataPoints("file") MultipartFile file,
			@FromDataPoints("addedBy") String addedBy) {

		System.out.println("The file of type : "+file.getContentType());
		System.out.println("Added By : "+addedBy);
		
		try{
			if(FileManager.isPDFFile(file)){
				System.out.println("Uploaded file is a PDF");
				return uploaderService.uploadPdf(file, addedBy);
			} else{
				System.out.println("Not a PDF File");
				return ResponseError.getErrorResponseObject(2001, "Please Upload only PDF formatted file");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseError.getErrorResponseObject(3001, "Generic Exception");
		
		}
	}
	
	/**
	 * 
	 * @author Naveen
	 * @param fileName
	 * @craetedDate 6-Jun-2017
	 * @modifiedDate 6-Jun-2017
	 * @return ResponseEntity<T>
	 */
	@RequestMapping(value="/getFile", method=RequestMethod.PUT, headers="content-type=application/json")
	public ResponseEntity<ResponseData<FileUploaderDto>> getFile() {
	
		try {
			return uploaderService.getFile("MongoDB-Performance-Best-Practices.pdf");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseError.getErrorResponseObject(3002, "Generic Exception");
		}
	}
	

}

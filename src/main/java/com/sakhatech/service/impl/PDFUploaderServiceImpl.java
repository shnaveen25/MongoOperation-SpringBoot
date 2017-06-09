package com.sakhatech.service.impl;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.sakhatech.GlobalConstants;
import com.sakhatech.dao.mongo.PDFUploaderDao;
import com.sakhatech.dto.FileUploaderDto;
import com.sakhatech.response.ResponseData;
import com.sakhatech.response.ResponseError;

/**
 * The service class for uploading/reterving PDF formatted file
 * 
 * @author Naveen
 * @createdDate 6-Jun-2017
 * @modifiedDate 6-Jun-2017
 *
 */

@Service("PDFUploaderService")
public class PDFUploaderServiceImpl {

	@Autowired
	private PDFUploaderDao pdfUploader;

	
	/**
	 * 
	 * @author Naveen
	 * @param file
	 * @param addedBy
	 * @createdDate 6-Jun-2017
	 * @modifiedDate 6-Jun-2017
	 * @return ResponseEntity<T>
	 */
	public ResponseEntity<ResponseData<String>> uploadPdf(MultipartFile file, String addedBy) {

		Date currentDate = new Date();

		FileUploaderDto uploaderDto = new FileUploaderDto();
		uploaderDto.setFile(file);
		uploaderDto.setAddedBy(addedBy);
		uploaderDto.setCreatedDate(currentDate);
		uploaderDto.setModifiedDate(currentDate);

		String msg = null;
		msg = pdfUploader.uploadFile(uploaderDto);
		if (msg.equals(GlobalConstants.SUCCESS))
			return ResponseData.getSuccessResponseObject(1001, new String("File Uploaded Successifully"));
		else
			return ResponseError.getErrorResponseObject(2002, msg);
	}

	/**
	 * 
	 * @author Naveen
	 * @param fileName
	 * @createdDate 6-Jun-2017
	 * @modifiedDate 6-Jun-2017
	 * @return ResponseEntity<T>
	 */
	public ResponseEntity<ResponseData<FileUploaderDto>> getFile(String fileName){
		FileUploaderDto file = new FileUploaderDto();
		
		GridFSDBFile fileFromDb = pdfUploader.getFile(fileName);
		
		if(fileFromDb != null){
			MultipartFile multiPartFile = null;
			file.setId(fileFromDb.getId().toString());
			file.setAddedBy(fileFromDb.get("addedBy").toString());
			file.setCreatedDate(fileFromDb.getUploadDate());
			try {
				multiPartFile = new MockMultipartFile(fileFromDb.getFilename(), fileFromDb.getFilename(), "application/pdf", fileFromDb.getInputStream());
				System.out.println("The Mulipart File : "+multiPartFile.getOriginalFilename());
				//file.setFile(multiPartFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return ResponseData.getSuccessResponseObject(1002, file);
		} else{
			return ResponseError.getErrorResponseObject(2003, "No file found in the collection");
		}
	}
}

package com.sakhatech.util;

import org.springframework.web.multipart.MultipartFile;

import com.sakhatech.GlobalConstants;

/**
 * 
 * @author Naveen
 * @createdDate 6-Jun-2017
 * @modifedDate 6-Jun-2017
 *
 */
public class PDFFileManager {

	public static boolean isPDFFile(MultipartFile file){
		
		if(file == null)
			return false;
		if(!file.getContentType().equals(GlobalConstants.PDF_FILE))
			return false;
		
		return true;
	}
}

package com.sakhatech.util;

import org.springframework.web.multipart.MultipartFile;

import com.sakhatech.GlobalConstants;

/**
 * The utility class to check the content typr of a file
 * 
 * @author Naveen
 * @createdDate 6-Jun-2017
 * @modifedDate 6-Jun-2017
 *
 */
public class FileManager {

	/**
	 * The utility method to check weather the file is of type PDF
	 * 
	 * @author naveen
	 * @param file
	 * @createdDate 7-Jun-2017
	 * @modifiedDate 7-Jun-2017
	 * @return {@link Boolean}
	 */
	public static boolean isPDFFile(MultipartFile file){
		
		if(file == null)
			return false;
		if(!file.getContentType().equals(GlobalConstants.PDF_FILE))
			return false;
		
		return true;
	}
	
	/**
	 * The utility method to check weather the file is of type Image
	 * 
	 * @author Naveen
	 * @createdDate 13-Jun-2017
	 * @modifiedDate 13-Jun-2017
	 * @param file
	 * @return {@link Boolean}
	 */
	public static boolean isImageFile(MultipartFile file){
		
		if(file == null)
			return false;
		if(!file.getContentType().contains("image"))
			return false;
		
		return true;
	}
}

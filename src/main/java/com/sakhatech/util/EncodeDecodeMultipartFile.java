package com.sakhatech.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Naveen
 * @createdDate 12-Jun-2017
 * @modifidDate 12-Jun-2017
 * 
 */
public class EncodeDecodeMultipartFile {
	
	private static String fileBase64 = null;
	private static byte[] fileByteArray = null;
	private static MultipartFile file = null;
	
	/**
	 * The Utility method accpets {@link MultipartFile} as a parameter
	 * and encrypt it using {@link Base64}
	 * 
	 * @author Naveen
	 * @param file
	 * @createdDate 12-Jun-2017
	 * @modifiedDate 12-Jun-2017
	 * @return {@link String}
	 * @throws IOException
	 */
	public static String encryptMultipartFile(MultipartFile file) throws IOException{
		fileByteArray = file.getBytes();
		fileBase64 = Base64.encodeBase64String(fileByteArray);
		
		return fileBase64;
	}
	
	/**
	 * The utility method to decrypt the encrypted string 
	 * form {@link Base64} to {@link MultipartFile}
	 * 
	 * @author Naveen
	 * @param fileBase64
	 * @param fileName
	 * @param contentType
	 * @param originalName
	 * @createdDate 12-Jun-2017
	 * @modifiedDate 12-Jun-2017
	 * @return {@link MultipartFile}
	 * @throws IOException
	 */
	public static MultipartFile decryptMultiPartFile(String fileBase64, String fileName,
		 String contentType , String originalName) throws IOException{
		fileByteArray = Base64.decodeBase64(fileBase64);
		
		file = new MockMultipartFile(
				fileName, originalName , contentType ,  new ByteArrayInputStream(fileByteArray));
		
		return file;	
	}
	
	/**
	 * The utility method to decode the user stored picture 
	 * form {@link Base64} encoded String to {@link Image}  
	 * 
	 * @author Naveen
	 * @param fileBase64
	 * @param fileName
	 * @param contentType
	 * @param originalName
	 * @createdDate 13-Jun-2017
	 * @modifiedDate 13-Jun-2017
	 * @return {@link Image}
	 * @throws IOException
	 */
	public static Image decryprMultiPartFileToImage(String fileBase64, String fileName,
			 String contentType , String originalName) throws IOException{
				
		fileByteArray = Base64.decodeBase64(fileBase64);
		
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(fileByteArray));
		
		return img;
	}
}

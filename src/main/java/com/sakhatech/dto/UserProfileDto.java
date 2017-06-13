package com.sakhatech.dto;

import java.io.File;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Naveen
 * @createdDate 8-Jun-2017
 * @modifiedDate 8-Jun-2017
 * 
 */
public class UserProfileDto {
	
	private String name;
	private String email;
	private String mobile;
	private Date dob;
	private MultipartFile photo;
	private File photoPath;
	private String photoEncodedBase64;

	@Override
	public String toString() {
		return "UserProfileDto [name=" + name + ", email=" + email + ", mobile=" + mobile + ", dob=" + dob + ", photo="
				+ photo + ", photoPath=" + photoPath + ", photoEncodedBase64=" + photoEncodedBase64 + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public File getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(File filePath) {
		this.photoPath = filePath;
	}
	public String getPhotoEncodedBase64() {
		return photoEncodedBase64;
	}
	public void setPhotoEncodedBase64(String photoEncodedBase64) {
		this.photoEncodedBase64 = photoEncodedBase64;
	}
}

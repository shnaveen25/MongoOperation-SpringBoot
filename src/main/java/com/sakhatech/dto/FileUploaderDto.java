package com.sakhatech.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


/**
 * 
 * @author Naveen
 * @createdDate 6-Jun-2017
 * @modifiedDate 6-Jun-2017
 * 
 */
public class FileUploaderDto {

	//private static final long serialVersionUID = 1L;
	
	private String id;
	private MultipartFile file;
	private String addedBy;
	private Date createdDate;
	private Date modifiedDate;
	
	@Override
	public String toString() {
		return "FileUploaderDto [file=" + file + ", addedBy=" + addedBy + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}

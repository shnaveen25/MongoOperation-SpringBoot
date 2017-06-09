package com.sakhatech.dao.mongo;

import com.mongodb.gridfs.GridFSDBFile;
import com.sakhatech.dto.FileUploaderDto;

/**
 * 
 * @author Naveen
 *
 */
public interface PDFUploaderDao {

	public static final String COLLECTION = "PDF";
	
	public static final String COL_FILE = "file";
	
	public static final String COL_CREATED_BY = "addedBy";
	
	public static final String COL_CREATED_DATE = "createdDate";
	
	public static final String COL_MODIFIED_DATE = "modifiedDate";
	
	public String uploadFile(FileUploaderDto fileDto);
	
	public GridFSDBFile getFile(String fileName);
}

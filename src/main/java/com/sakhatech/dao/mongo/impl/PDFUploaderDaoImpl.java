package com.sakhatech.dao.mongo.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.sakhatech.GlobalConstants;
import com.sakhatech.dao.mongo.PDFUploaderDao;
import com.sakhatech.dto.FileUploaderDto;
import com.sakhatech.util.MongoOperationClient;
/**
 * 
 * @author Naveen
 * @createdDate 7-Jun-2017
 * @modifiedDate 7-Jun-2017
 */
@Service("PDFUploaderDao")
public class PDFUploaderDaoImpl implements PDFUploaderDao {

	@Override
	public String uploadFile(FileUploaderDto fileDto) {
			
		try {
			GridFS gfs = new GridFS(MongoOperationClient.getDatabse(), COLLECTION);
			
			GridFSDBFile isFileExist = gfs.findOne(fileDto.getFile().getOriginalFilename());
			
			if(isFileExist != null)
				return "File already exists";
			
			GridFSInputFile gfsFile = gfs.createFile(fileDto.getFile().getBytes());
			gfsFile.setFilename(fileDto.getFile().getOriginalFilename());
			gfsFile.put(COL_CREATED_BY, fileDto.getAddedBy());
			gfsFile.save();
			
			return GlobalConstants.SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return "Internal Server Error while performing operation...";
		}
	}

	@Override
	public GridFSDBFile getFile(String fileName) {
		GridFS gfs = new GridFS(MongoOperationClient.getDatabse(), COLLECTION);		
		return gfs.findOne(fileName);
	}
}

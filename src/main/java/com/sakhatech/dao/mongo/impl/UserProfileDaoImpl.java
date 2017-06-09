package com.sakhatech.dao.mongo.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sakhatech.GlobalConstants;
import com.sakhatech.dao.mongo.UserProfileDao;
import com.sakhatech.dto.UserProfileDto;
import com.sakhatech.util.MongoOperationClient;

/**
 * 
 * @author Naveen
 * @createdDate 7-Jun-2017
 * @modifiedDate 8-Jun-2017
 */
@Service("UserProfileDao")
public class UserProfileDaoImpl implements UserProfileDao {
	
	private DBCollection collection;
	
	public UserProfileDaoImpl() {
		this.collection = MongoOperationClient.getCollection(COLLECTION);
	}

	@Override
	public String addUSer(UserProfileDto user) {
		
		BasicDBObject userToBesaved = new BasicDBObject();
		userToBesaved.append(COL_NAME, user.getName());
		userToBesaved.append(COL_EMAIL, user.getEmail());
		userToBesaved.append(COL_MOBILE, user.getMobile());
		userToBesaved.append(COL_DOB, user.getDob());
		userToBesaved.append(COL_PHOTO_PATH, user.getPhotoPath().getPath());
		
		try{
			collection.insert(userToBesaved);
			return GlobalConstants.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal Server exception "+e.getMessage();
		}
	}

	@Override
	public UserProfileDto getUser(String email) {
		
		BasicDBObject query = new BasicDBObject(COL_EMAIL, email);
		DBObject userFromCollection= collection.findOne(query);
		UserProfileDto user = null;
		
		if(userFromCollection != null) {
			user = new UserProfileDto();
			user.setName(userFromCollection.get("name").toString());
			user.setEmail(userFromCollection.get("email").toString());
			user.setMobile(userFromCollection.get("mobile").toString());
			user.setDob((Date)userFromCollection.get("dob"));	
			user.setPhotoPath(new File(userFromCollection.get("imagePath").toString()));
		}
		
		return user;
			
	}
	
	@Override
	public List<UserProfileDto> getAllUsers() {
		
		DBCursor curser =  collection.find();
		
		List<UserProfileDto> responseUser = new ArrayList<UserProfileDto>();
		UserProfileDto user = null;
		
		for(DBObject eachCurser : curser){
			DBObject userFromColl = eachCurser;
			
			user = new UserProfileDto();
			
			user.setName(userFromColl.get(COL_NAME).toString());
			user.setEmail(userFromColl.get(COL_EMAIL).toString());
			user.setMobile(userFromColl.get(COL_MOBILE).toString());
			user.setDob((Date)userFromColl.get(COL_DOB));
			
			responseUser.add(user);
		}
		return responseUser;
	}

}

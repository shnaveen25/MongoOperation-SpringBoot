package com.sakhatech;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sakhatech.util.MongoOperationClient;

/**
 * 
 * @author Naveen
 * @createdDate 6-Jun-2017
 * @modifiedDate 6-Jun-2017
 */
@SpringBootApplication
public class MongoDbOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbOperationsApplication.class, args);
	}
	
	/**
	 * 
	 * @author Naveen
	 * @createdDate 6-Jun-2017
	 * @modifiedDate 6-Jun-2017
	 * 
	 */
	@PreDestroy
	public void closeMongoConnecion(){
		MongoOperationClient.closeMongoClient();
	}
}

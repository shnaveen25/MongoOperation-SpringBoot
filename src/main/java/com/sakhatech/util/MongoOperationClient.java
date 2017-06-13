package com.sakhatech.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * The utility class for instantiating the Database
 * 
 * @author Naveen
 * @createdDate 6-Jun-2017
 * @modifedDate 6-Jun-2017
 *
 */
public final class MongoOperationClient {

	private static MongoClient mongoClient;

	private static final String MONGO_HOST = "127.0.0.1";

	private static final int MONGO_PORT = 27017;

	private static final String MONGO_DB = "MongoOperation";

	private static final Logger logger = LoggerFactory.getLogger(MongoOperationClient.class);

	public MongoOperationClient() {

	}

	/**
	 * 
	 * @author Naveen
	 * @return MongoClient
	 */
	private static MongoClient getMongoClient() {
		if (mongoClient != null) {
			return mongoClient;
		}

		try {
			mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
			logger.info("The Mongo client has been instantiated.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while instantiating MongDb : ", e);
		}
		return mongoClient;
	}

	/**
	 * 
	 * @author Naveen
	 * @createdDate 6-Jun-2017
	 * @modifedDate 6-Jun-2017
	 * @return DB
	 */
	public static DB getDatabse() {
		if (mongoClient == null) {
			getMongoClient();
		}

		DB database = null;

		try {
			database = mongoClient.getDB(MONGO_DB);
			logger.info("The database has been instantiated.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while instantiated database : ", e);
		}
		return database;
	}

	/**
	 * 
	 * @author naveen
	 * @param collectionName
	 * @createdDate 6-Jun-2017
	 * @modifedDate 6-Jun-2017
	 * @return
	 */
	public static DBCollection getCollection(String collectionName) {
		DB database = getDatabse();

		if (database == null) {
			logger.error("Database not found");
		}

		DBCollection collection = null;

		try {
			collection = database.getCollection(collectionName);
			logger.info("Collection has been instantiated");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while instantiating Database", e);
		}
		return collection;
	}

	/**
	 * 
	 * @author Naveen
	 * @createdDate 6-Jun-2017
	 * @modifedDate 6-Jun-2017
	 * 
	 */
	public static void closeMongoClient() {
		if (mongoClient == null) {
			logger.warn("MongoClient has already been closed");
		}

		mongoClient.close();
	}

}

package com.umkc.dao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class CourseDAO {
MongoClient mongoClient;
	
	
	
	/**
	 * Method will return the DBCollection to "group13" object by establishing 
	 * connection with database "group13" which is running on localhost with port#27017
	 * @return DBCollection
	 */
	private DBCollection getDBCollection(){
		//Intializing client for mongodb make sure that server is running in the background
		//To run server open terminal and go to mongodb installed folder and type command in terminal "mongod"
		//replace localhost with ip address if you are using mongodb installed in some other system
		//Port number should be provided
		mongoClient= new MongoClient("localhost",27017);
		
		//Gettoing object to database created in the mongodb. Here I have created a database named as "group13"
		DB db =  mongoClient.getDB("group13");
		
		//Getting the object for collection "asegroup" created in database
		DBCollection dbcollection = db.getCollection("courses");
		
		return dbcollection;
	}

	/**
	 * Method will send the data to the mongo DB by with the collected BASIC DB object which a format document accepted by the mongoDB.
	 * @param BASICDBObject having the user details
	 * @param name(firstname+lastname).toUpperCase() of the user  
	 */
	public boolean sendDataToMongoDB(BasicDBObject basicObject) {
		
		boolean status;
		
		
		//Calling the doesContains method to check whether any records exists in database or not with that ID
		if(doesContains(basicObject)){
			status= false;
			
		}
		else{
			
		//getting dbcollection object to send the data to mongodb
		DBCollection dbCollection = getDBCollection();
	
		//inserting the data into mongodb since no records exists in mongodb
		dbCollection.insert(basicObject);
		
		//Closig the client application
		//closeClient();
		
		status = true;
		}
		
		return status;
	}
	
	//method to delete document
	public void deleteDocFromMongoDB(BasicDBObject basicObject)
	{
	DBCollection dbcollection= getDBCollection();
	dbcollection.remove(basicObject);
		
		
	}
	
	
	/**
	 * Method to close mongoClient created above.
	 */
	public void closeClient() {
		//Calling predefined api to close the connection.
		mongoClient.close();
	}

	
	public DBCursor retrieveDocument(DBObject dbObject){
		
		//Getting collection object to connect and retrieve data from mongodb
		DBCollection dbCollection = getDBCollection();
	
		//retrieving the data and collecting using the DBCursor
		DBCursor dbCurser = dbCollection.find(dbObject);
		
		//Closing the connection
		//closeClient();
		
		return dbCurser;
	}
	
	
	public JSONObject retrieveDocumentesFromMongoLab() throws ClientProtocolException, IOException{
		
		return null;
}
	
	/**
	 * @param name
	 * @return boolean specifies whether user exists in database or not true means user exists and false means user doesnot exits.
	 */
	public boolean doesContains(DBObject dbObject){
		
		//Calling retrieveDocument method to check whether any existing documents are available in the database with that name or not.
		DBCursor dbCurser = retrieveDocument(dbObject);
		
		//Checking cursor size. If size greter than 0 then there is record with that user id exists in database 
		if(dbCurser.size() > 0){
			//closeClient();
			return true;
		}
		
		//closeClient();
		
		return false;
	}
	
	public static void main(String[] args) {
		
		
		
	}
}


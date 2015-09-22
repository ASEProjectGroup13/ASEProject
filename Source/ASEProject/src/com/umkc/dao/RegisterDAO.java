package com.umkc.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class RegisterDAO {
	
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
		DBCollection dbcollection = db.getCollection("ase");
		
		return dbcollection;
	}

	/**
	 * Method will send the data to the mongo DB by with the collected BASIC DB object which a format document accepted by the mongoDB.
	 * @param BASICDBObject having the user details
	 * @param name(firstname+lastname).toUpperCase() of the user  
	 */
	public boolean sendDataToMongoDB(BasicDBObject basicObject) {
		
		boolean status;
		
		
		BasicDBObject basicDBObject = new BasicDBObject("username", basicObject.get("username"));
		//Calling the doesContains method to check whether any records exists in database or not with that ID
		if(doesContains(basicDBObject)){
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
	
	
	/**
	 * Method to close mongoClient created above.
	 */
	private void closeClient() {
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
		if(dbCurser.size() > 1){
			//closeClient();
			return true;
		}
		
		//closeClient();
		
		return false;
	}
}

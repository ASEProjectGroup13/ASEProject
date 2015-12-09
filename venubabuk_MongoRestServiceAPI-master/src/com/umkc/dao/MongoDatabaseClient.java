package com.umkc.dao;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDatabaseClient {
	
	//Creating DBCollection for ASE Collection in database group13
	private static DBCollection createASEDBCollection(){
		
		MongoClientURI mongoClientUri = new MongoClientURI("mongodb://root:admin@ds045714.mongolab.com:45714/group13");
		
		MongoClient mongoclient = new MongoClient(mongoClientUri);
		
		DB db =mongoclient.getDB(mongoClientUri.getDatabase());
		
		DBCollection dbcollection = db.getCollection("ase");
		
		return dbcollection;
	}
	
	public boolean performLoginValidation(BasicDBObject basicdbobject){
		
		DBCollection dbcollection = createASEDBCollection();
		
		String username = basicdbobject.getString("username");
		
		System.out.println("username received from front end"+ username);
		
		DBCursor dbcursor = dbcollection.find(new BasicDBObject("username", username));
		
		System.out.println("Hiii");
		
		BasicDBObject basicsDBObject = null;
		
		
		
		
		
		//System.out.println(dbcursor.toArray().toString());
		
		if(dbcursor.hasNext()){
			
			BasicDBObject basiDBObject = (BasicDBObject) dbcursor.next();
			
			System.out.println("Inside hello how are you");
			
			String usernameFt = basicdbobject.getString("username");
			String passwordFt = basicdbobject.getString("password");
			String usertypeFt = basicdbobject.getString("usertype");
			
			
			
			//return basiDBObject.toString();
			
		if(basicdbobject.getString("username").equals(basiDBObject.getString("username")) && basicdbobject.getString("password").equals(basiDBObject.getString("password")) && basicdbobject.getString("usertype").equals(basiDBObject.getString("usertype")));
			
		
		System.out.println(basicdbobject.getString("username"));
		
			return true;
		}
		
		return false;
	}
	
	public boolean registerUser(DBObject basicdbobject){
		
		System.out.println(basicdbobject.toString());
		
		DBCollection dbcollection = createASEDBCollection();
		
		if(doesASERecordExits((BasicDBObject) basicdbobject)){
			
			System.out.println("record exists");
			return false;
			
		}else{
			
			System.out.println("No record exists so trying to insert");
		
		dbcollection.insert(basicdbobject);
		
		return true;
		
		}
		
		
	}
	//Checking weather ase record exists or not
	public boolean doesASERecordExits(BasicDBObject dbobject) {
		
		DBCollection dbcollection = createASEDBCollection();
		
		DBCursor dbcursor = dbcollection.find(dbobject);
		
		if(dbcursor.hasNext()){
			return true;
		}
		
		return false;
	}
	
	public String retrieveMatchingRecord(BasicDBObject basicDBObject){
		
		DBCollection dbcollection = createASEDBCollection();
		
		DBCursor dbCursor = dbcollection.find(basicDBObject);
		return dbCursor.toArray().toString();
	}

	
	public String retrieveBasicProfile(BasicDBObject basicdbobject) {
		
		//
		DBCollection dbcollection  = createASEDBCollection();
		
		DBCursor dbcursor = dbcollection.find(basicdbobject);
		
		JSONObject jsonObject = new JSONObject();
		
		BasicDBObject basicdbobject1= null;
		
		while(dbcursor.hasNext()){
			 basicdbobject1 = (BasicDBObject) dbcursor.next();
			
//			try {
//				jsonObject.put("username", basicdbobject1.getString("username"));
//				jsonObject.put("name", basicdbobject1.getString("name"));
//				jsonObject.put("email", basicdbobject1.getString("email"));
//				jsonObject.put("phonenumber", basicdbobject1.get("phonenumber"));
//			} catch (JSONException e) {
//				System.out.println("Exception in basic profile retrieval"+e.getMessage());
//			}
			break;
		}
		
		System.out.println("JSON Object from output"+basicdbobject1.toString());
		return basicdbobject1.toString();
	}
	
	public static void main(String[] args) {
	MongoDatabaseClient mdC = new MongoDatabaseClient();
	BasicDBObject basicdbobject = new BasicDBObject("username","venu");
	mdC.retrieveBasicProfile(basicdbobject);
	}
}

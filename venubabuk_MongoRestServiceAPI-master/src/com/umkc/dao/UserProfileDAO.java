package com.umkc.dao;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class UserProfileDAO {
	
	static DBCollection dbcollection; 

	private DBCollection createASEDBCollection(){
		
		MongoClientURI mongoClientUri = new MongoClientURI("mongodb://root:admin@ds045714.mongolab.com:45714/group13");
		
		
		MongoClient mongoclient = new MongoClient(mongoClientUri);
		
		
		DB db =mongoclient.getDB(mongoClientUri.getDatabase());
		
		 dbcollection = db.getCollection("ase");
		
		return dbcollection;
	}
	
	public boolean updateBasicProfile(BasicDBObject basicdbobject){
		
		String username = basicdbobject.getString("username");
		
		BasicDBObject original = new BasicDBObject("username", username);
		
		dbcollection =createASEDBCollection();
		
		dbcollection.update(original, basicdbobject);
		
		return true;
	}
	
	public String retrieveBasicProfile(BasicDBObject basicdbobject) {
		
		 dbcollection  = createASEDBCollection();
		
		DBCursor dbcursor = dbcollection.find(basicdbobject);
		
		BasicDBObject basicdbobject1= null;
		
		while(dbcursor.hasNext()){
			 basicdbobject1 = (BasicDBObject) dbcursor.next();
			break;
		}
		
		System.out.println("JSON Object from output"+basicdbobject1.toString());
		return basicdbobject1.toString();
	}

}

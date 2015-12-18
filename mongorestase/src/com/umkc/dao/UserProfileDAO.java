package com.umkc.dao;

import javax.sound.midi.Synthesizer;

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
		
		System.out.println("original database object before insert"+original);
		System.out.println("updated database object before insert"+basicdbobject.toString());
		
		dbcollection =createASEDBCollection();
		
		dbcollection.update(original, basicdbobject);
		
		return true;
	}
	
	public String retrieveBasicProfile(BasicDBObject basicdbobject) {
		
		 dbcollection  = createASEDBCollection();
		
		DBCursor dbcursor = dbcollection.find(basicdbobject);
		
		BasicDBObject basicdbobject1= null;
		
		if(dbcursor.hasNext()){
			 basicdbobject1 = (BasicDBObject) dbcursor.next();
			
		}
		
		System.out.println("JSON Object from output"+basicdbobject1.toString());
		return basicdbobject1.toString();
	}
	
	public BasicDBObject retrieveBasicInformation(BasicDBObject basicDBObject){
		
		dbcollection  = createASEDBCollection();
		
		DBCursor dbcursor = dbcollection.find(basicDBObject);
		
		BasicDBObject basicdbobject1= null;
		
		if(dbcursor.hasNext()){
			 basicdbobject1 = (BasicDBObject) dbcursor.next();
			
		}
		
		return basicdbobject1;
	}

}

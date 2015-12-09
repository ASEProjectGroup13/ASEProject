package com.umkc.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MajorsDAO {
	
	public static MongoClient mongoClient;
	public static DBCollection dbcollection;
	
	public boolean doesMajorRecordExits(BasicDBObject dbobject) {

		dbcollection = createMajorDBCollection();

		DBCursor dbcursor = dbcollection.find(dbobject);

		if (dbcursor.hasNext()) {
			return true;
		}

		return false;
	}
	
	// Creating DBCollection for Major Collection in database group13
		private static DBCollection createMajorDBCollection() {
			
			MongoClientURI mongoClientUri = new MongoClientURI("mongodb://root:admin@ds045714.mongolab.com:45714/group13");
			
			if(mongoClient == null){
				mongoClient = new MongoClient(mongoClientUri);
			}
			
			DB db = mongoClient.getDB(mongoClientUri.getDatabase());

			DBCollection dbcollection = db.getCollection("major");

			return dbcollection;
		}
		
		public boolean insertMajorInfo(BasicDBObject basicdbobject) {

			dbcollection= createMajorDBCollection();

			if (doesMajorRecordExits(basicdbobject)) {
				System.out.println("record exists");
				return false;
			} else {
				System.out.println("Entering a new record into mongodatabase");

				dbcollection.insert(basicdbobject);

				return true;
			}
		}
		
		public String updateMajorInformation(BasicDBObject basicdbObject){
			
			
			dbcollection = createMajorDBCollection();
			
			dbcollection.update(new BasicDBObject("majorid",basicdbObject.getString("majorid")), basicdbObject);
			
			BasicDBObject basicdbobject = null;
			
			DBCursor dbcursor = dbcollection.find(basicdbObject);
			
			if(dbcursor.hasNext()){
				basicdbobject = (BasicDBObject) dbcursor.next();
			}
			
			return basicdbObject.toString();
		}
		
		public DBCursor retrieveAllMajors() {
			
			dbcollection = createMajorDBCollection();
			
			DBCursor dbcursor = dbcollection.find();
			return dbcursor;
			
		}
		
		public DBCursor retrieveSelectedMajor(BasicDBObject basicDBObject) {
			
			dbcollection = createMajorDBCollection();
			
			DBCursor dbcursor = dbcollection.find(basicDBObject);
			return dbcursor;
			
		}
		
		public void closeConnection(){
			if(mongoClient == null){
				
			}
			else{
				mongoClient.close();
			}
		}

		
		public boolean deleteMajorInformation(BasicDBObject basicdbobject) {
			
			dbcollection = createMajorDBCollection();
			
			dbcollection.remove(basicdbobject);
			
			boolean status;
			if(doesMajorRecordExits(basicdbobject)){
				status = false;
			}else{
				status = true;
			}
			return status;
		}


}

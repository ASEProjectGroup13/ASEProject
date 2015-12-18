package com.umkc.dao;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class CoursesDAO {

	// Checking weather courses record exists or not
	public boolean doesCourseRecordExits(BasicDBObject dbobject) {

		DBCollection dbcollection = createCoursesDBCollection();

		DBCursor dbcursor = dbcollection.find(dbobject);

		if (dbcursor.hasNext()) {
			return true;
		}

		return false;
	}

	// Creating DBCollection for courses Collection in database group13
	private static DBCollection createCoursesDBCollection() {
		
		MongoClientURI mongoClientUri = new MongoClientURI("mongodb://root:admin@ds045714.mongolab.com:45714/group13");
		
		MongoClient mongoclient = new MongoClient(mongoClientUri);

		//MongoClient mongoclient = new MongoClient("localhost", 27017);

		DB db = mongoclient.getDB(mongoClientUri.getDatabase());

		DBCollection dbcollection = db.getCollection("courses");

		return dbcollection;
	}

	public DBCursor retrieveAllCourses() {

		DBCollection dbcoursecollection = createCoursesDBCollection();

		return dbcoursecollection.find();

	}

	public boolean insertCourseInfo(BasicDBObject basicdbobject) {

		DBCollection coursedbcollection = createCoursesDBCollection();

		if (doesCourseRecordExits(basicdbobject)) {
			System.out.println("record exists");
			return false;
		} else {
			System.out.println("Entering a new record into mongodatabase");

			coursedbcollection.insert(basicdbobject);

			return true;
		}
	}
	
	public boolean updateCourseInfo(BasicDBObject basicdbobject) {

		DBCollection coursedbcollection = createCoursesDBCollection();

		if (doesCourseRecordExits(basicdbobject)) {
			System.out.println("record exists");
			return false;
		} else {
			System.out.println("Entering a new record into mongodatabase");
			
			System.out.println("Course ID"+basicdbobject.get("courseid"));

			BasicDBObject queryDocument = new BasicDBObject("courseid",basicdbobject.get("courseid"));
			coursedbcollection.update(queryDocument,basicdbobject);

			return true;
		}
	}
	
	public void updateExistingCourseInfo(BasicDBObject original,BasicDBObject updated) {

		DBCollection coursedbcollection = createCoursesDBCollection();

		
			System.out.println("Entering a new record into mongodatabase");
			
			//System.out.println("Course ID"+basicdbobject.get("courseid"));

			//BasicDBObject queryDocument = new BasicDBObject("courseid",basicdbobject.get("courseid"));
			coursedbcollection.update(original,updated);

			
		
	}

	public boolean deleteCourseFromDatabase(BasicDBObject basicdbobject) {

		DBCollection dbcollection = createCoursesDBCollection();

		dbcollection.remove(basicdbobject);

//		return writeresult.toString();
		
		return doesCourseRecordExits(basicdbobject);

//		return  Response.ok() //200
//				.entity(writeresult	)
//				.header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
//				.build();
	}
	public static void main(String[] args) {
		DBCollection dbcollection = createCoursesDBCollection();
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		basicDBObject.put("hello", "hi");
		
		System.out.println("why are you so dull today");
		
		dbcollection.insert(basicDBObject);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/course")
	public String insertCourse(String jsonData) {

		System.out.println(jsonData);

		Object dataJson = JSON.parse(jsonData);

		BasicDBObject dbObject = (BasicDBObject) dataJson;

		System.out.println("after conversion of datasets" + dbObject.toString());

		CoursesDAO coursedao = new CoursesDAO();

		boolean status = coursedao.insertCourseInfo(dbObject);

		JSONObject jsonObject = new JSONObject();

		if (status) {
			try {
				jsonObject.put("statusmessage", "success");
			} catch (JSONException e) {
				System.out.println("Exception" + e.getMessage());
			}
		} else {
			try {
				jsonObject.put("statusmessage", "record already exists");
			} catch (JSONException e) {
				System.out.println("Exception" + e.getMessage());
			}
		}
		return jsonObject.toString();
	}

	public BasicDBObject retrieveSelectedCourse(BasicDBObject basicDBObject) {
		
		DBCollection dbcoursecollection = createCoursesDBCollection();
		
		DBCursor dbcuror = dbcoursecollection.find(basicDBObject);
		
		BasicDBObject outputobject = new BasicDBObject();
		if(dbcuror.hasNext()){
			outputobject = (BasicDBObject) dbcuror.next();
		}
		return outputobject;
	}

	public boolean updateCourseRatings(BasicDBObject courseDBObject) {
	
			DBCollection dbcoursecollection = createCoursesDBCollection();
			
			dbcoursecollection.update(new BasicDBObject("courseid",courseDBObject.getString("courseid")), courseDBObject);
			
			return true;
			
			
	
			
		
		
	}
}

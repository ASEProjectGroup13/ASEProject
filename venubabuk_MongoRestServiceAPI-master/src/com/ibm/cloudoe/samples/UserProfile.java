																														package com.ibm.cloudoe.samples;import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.omg.CORBA.UShortSeqHolder;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;
import com.umkc.dao.CoursesDAO;
import com.umkc.dao.MongoDatabaseClient;
import com.umkc.dao.UserProfileDAO;

@Path("profile")
public class UserProfile {

	@GET
	public String returnString(){
		return "Hello Basic Profile";
	}
	
	@POST
	@Path("retrieveProfile")
	public String retrieveBasicProfile(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		
		MongoDatabaseClient mongoDatabaseClient = new MongoDatabaseClient();
		
		String responseData = mongoDatabaseClient.retrieveBasicProfile(basicdbobject);
		
		return responseData;
	}
	
	@POST
	@Path("updateProfile")
	public String updateProfile(String jsonData){
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		
		UserProfileDAO userprofileDAO = new UserProfileDAO();
		
		userprofileDAO.updateBasicProfile(basicdbobject);
		
		String output = userprofileDAO.retrieveBasicProfile(basicdbobject);
		
		return output;
	}
	
	@POST
	@Path("completedcourses")
	public String updateCompletedCourses(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		
		UserProfileDAO userprofileDAO = new UserProfileDAO();
		
		String input = userprofileDAO.retrieveBasicProfile(basicdbobject);
		
		Object profileObject = JSON.parse(input);
		
		BasicDBObject basicprofie = (BasicDBObject) profileObject;
		
		basicprofie.put("completedcourses", basicdbobject);
		
		boolean status = userprofileDAO.updateBasicProfile(basicprofie);
		
		BasicDBObject outputDBObject = new BasicDBObject();
		
		return userprofileDAO.retrieveBasicProfile(basicprofie);
		
	}
	
	@POST
	@Path("retrievecompletedcourses")
	public String retrieveCompletedCourses(BasicDBObject basicDBObject){
		
		UserProfileDAO userprofileDAO = new UserProfileDAO();
		
		String output = userprofileDAO.retrieveBasicProfile(basicDBObject);
		
		Object profileObject = JSON.parse(output);
		
		BasicDBObject basicprofie = (BasicDBObject) profileObject;
		
		BasicDBObject completedCourseObject = (BasicDBObject) basicprofie.get("completedcourses");
		return completedCourseObject.toString();
		
	}
}

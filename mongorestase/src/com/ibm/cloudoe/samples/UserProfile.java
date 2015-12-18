package com.ibm.cloudoe.samples;import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
	public String updateCompletedCourses(String jsonData) throws JSONException{
		
		System.out.println("data received from front end" + jsonData);

		String courseArray[] = jsonData.split(",");
		
		String username = courseArray[0];
		
		ArrayList<BasicDBObject> arrayList = new ArrayList<>();
		
		int i=1;
		while(i <courseArray.length){
			arrayList.add(new BasicDBObject("coursename",courseArray[i]));
			i++;
		}
		
		System.out.println(arrayList+"printing json courses");
		
		UserProfileDAO userprofileDAO = new UserProfileDAO();
		
		String input = userprofileDAO.retrieveBasicProfile(new BasicDBObject("username",username));
		
		Object profileObject = JSON.parse(input);
		
		System.out.println("output receied from database"+input);
		
		BasicDBObject basicprofie = (BasicDBObject) profileObject;
		
		System.out.println("string"+arrayList);
		
		
		basicprofie.put("completedcourses", arrayList);
		
		boolean status = userprofileDAO.updateBasicProfile(basicprofie);
		
		BasicDBObject outputDBObject = new BasicDBObject();
		
		return userprofileDAO.retrieveBasicProfile(basicprofie);
		
	}
	
	@POST
	@Path("retrievecompletedcourses")
	public String retrieveCompletedCourses(BasicDBObject basicDBObject){
		
		System.out.println("username received from front end"+ basicDBObject.getString("username"));
		UserProfileDAO userprofileDAO = new UserProfileDAO();
		
		String output = userprofileDAO.retrieveBasicProfile(basicDBObject);
		
		System.out.println("output received from database is :"+output);

		return output;
		
	}
	
	@POST
	@Path("enrolledcourses")
	public String updateEnrolledCourses(String jsonData) throws JSONException{
		
		System.out.println("data received from front end" + jsonData);
		
		System.out.println("Inside enrolled courses");

		String courseArray[] = jsonData.split(",");
		
		String username = courseArray[0];
		
		ArrayList<BasicDBObject> arrayList = new ArrayList<>();
		
		int i=1;
		while(i <courseArray.length){
			arrayList.add(new BasicDBObject("coursename",courseArray[i]));
			i++;
		}
		
		System.out.println(arrayList+"printing json courses");
		
		UserProfileDAO userprofileDAO = new UserProfileDAO();
		
		String input = userprofileDAO.retrieveBasicProfile(new BasicDBObject("username",username));
		
		Object profileObject = JSON.parse(input);
		
		System.out.println("output receied from database"+input);
		
		BasicDBObject basicprofie = (BasicDBObject) profileObject;
		
		System.out.println("string"+arrayList);
		
		
		basicprofie.put("enrolledcourses", arrayList);
		
		boolean status = userprofileDAO.updateBasicProfile(basicprofie);
		
		BasicDBObject outputDBObject = new BasicDBObject();
		
		return userprofileDAO.retrieveBasicProfile(basicprofie);
		
	}
	
	@POST
	@Path("retrieveenrolledcourses")
	public String retrieveEnrolledCourses(BasicDBObject basicDBObject){
		
		System.out.println("username received from front end"+ basicDBObject.getString("username"));
		UserProfileDAO userprofileDAO = new UserProfileDAO();
		
		String output = userprofileDAO.retrieveBasicProfile(basicDBObject);
		
		System.out.println("output received from database is :"+output);

		return output;
		
	}
	
	@POST
	@Path("retrieveprofessor")
	public String retrieveProfessorInformation(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		
		UserProfileDAO userProfileDao =  new UserProfileDAO();
		
		BasicDBObject userDBObject = userProfileDao.retrieveBasicInformation(basicdbobject);
		
		String name = userDBObject.getString("name");
		
		System.out.println("printing namess");
		
		CoursesDAO courseDao =  new CoursesDAO();
		DBCursor courseCursor = courseDao.retrieveAllCourses();
		
		ArrayList<String> coursenames = new ArrayList<>();
		while(courseCursor.hasNext()){
			BasicDBObject tempobject = (BasicDBObject) courseCursor.next();
			
			if(tempobject.getString("professor").equals(name)){
				coursenames.add(tempobject.getString("coursename"));
			}
		}
		
		userDBObject.put("coursesteach", coursenames);
		
		return userDBObject.toString();
	}
	
	public static void main(String[] args) throws JSONException {
		UserProfile userProfile = new UserProfile();		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "Dr. Lee");
		userProfile.retrieveProfessorInformation(jsonObject.toString());
	}
}

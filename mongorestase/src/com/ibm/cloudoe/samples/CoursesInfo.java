package com.ibm.cloudoe.samples;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;
import com.umkc.dao.CoursesDAO;
import com.umkc.dao.UserProfileDAO;

@Path("courseinfo")
public class CoursesInfo {
	@Path("test")
	@GET
	public String testCourse() {
		return "hello";
	}

	@Path("deletecourse")
	@POST
	public String deleteCourse(String jsondata) {
		System.out.println("data received from front end" + jsondata);

		Object jsonObject = JSON.parse(jsondata);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		CoursesDAO coursesdao = new CoursesDAO();

		boolean status = coursesdao.deleteCourseFromDatabase(basicdbobject);

		JSONObject statusObject = new JSONObject();
		try {
		if(status){
			statusObject.put("status", "success");
		}
		else{
			
				statusObject.put("status", "fail");
			} 
		}catch (JSONException e) {
			System.out.println("exception in message"+e.getMessage());
			
		}
		
		Response.status(200).header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        .header("Access-Control-Allow-Credentials", "true")
        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        .header("Access-Control-Max-Age", "1209600").entity(statusObject.toString()).build();
		return statusObject.toString();

	}
	
	
	
	@Path("insertcourse")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String createCourse(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		CoursesDAO coursesdao = new CoursesDAO();
		
		JSONObject jsonobject = new JSONObject();
		try {
		if(coursesdao.insertCourseInfo(basicdbobject)){
			jsonobject.put("status", "success");
			
		}else{
		
				jsonobject.put("status", "fail");
			
		}
		} catch (JSONException e) {
			System.out.println("Exception in json conversion"+e.getMessage());
		}
		
		return jsonobject.toString();
		
	}
	
	@Path("updatecourse")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCourse(String jsonData){
		
		System.out.println("data received from front end" + jsonData);
		System.out.println("data received from front end" + jsonData);
		System.out.println("data received from front end" + jsonData);
		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		CoursesDAO coursesdao = new CoursesDAO();
		String courseid= basicdbobject.getString("courseid");
		JSONObject jsonobject = new JSONObject();
		
		coursesdao.updateExistingCourseInfo(new BasicDBObject("courseid",courseid), basicdbobject);
			
		
		
		return basicdbobject.toString();
		
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/courses")
	public String retrieveCourses(){
		
		CoursesDAO courseDAO = new CoursesDAO();
		
		DBCursor dbcoursecursor = courseDAO.retrieveAllCourses();
		
		return dbcoursecursor.toArray().toString();
				
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coursestatus")
	public String retrieveCourseStatus(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);
		
		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		
		String username = basicdbobject.getString("username");
		
		String coursename = basicdbobject.getString("coursename");
		
		System.out.println("username"+username);
		System.out.println("password"+coursename);
		
		
		UserProfileDAO userProfileDAO = new UserProfileDAO();
		
		BasicDBObject outputObject = userProfileDAO.retrieveBasicInformation(new BasicDBObject("username",username));
		
		ArrayList<BasicDBObject> completedCourseList = (ArrayList<BasicDBObject>) outputObject.get("completedcourses");
		
		ArrayList<BasicDBObject> enrolledCourseList = (ArrayList<BasicDBObject>) outputObject.get("enrolledcourses");
		
		ArrayList<BasicDBObject> ratingsProvidedCourses = new ArrayList<>();
		if(outputObject.containsKey("ratingsprovided")){
		
			ratingsProvidedCourses = (ArrayList<BasicDBObject>) outputObject.get("ratingsprovided");
		}
		
		 
		
		ArrayList<String> completedCourseArrayList = new ArrayList<>();
		
		ArrayList<String> enrolledCourseArrayList = new ArrayList<>();
		
		ArrayList<String> ratingsCourseArrayList = new ArrayList<>();
		
		for (BasicDBObject basicDBObject2 : completedCourseList) {
			completedCourseArrayList.add(basicDBObject2.getString("coursename"));
		}
		
		for(BasicDBObject basicDBObject3: enrolledCourseList){
			enrolledCourseArrayList.add(basicDBObject3.getString("coursename"));
		}
		if(ratingsProvidedCourses.size() > 0){
		for(BasicDBObject basicDBObject3: ratingsProvidedCourses){
			ratingsCourseArrayList.add(basicDBObject3.getString("coursename"));
			
		}
		
		}
		
		
		
		System.out.println("enrolled courses"+enrolledCourseArrayList);
		System.out.println("completed courses"+completedCourseArrayList);
		JSONObject outputJSON = new JSONObject();
		try{
			
			if(ratingsCourseArrayList.contains(coursename)){
				outputJSON.put("ratingstatus", "provided");
			}else{
				outputJSON.put("ratingstatus", "notprovided");
			}
			
			
		
		if(completedCourseArrayList.contains(coursename)){
			outputJSON.put("status", "completed");
			
			System.out.println("Inside completed courses");
		}
		else if(enrolledCourseArrayList.contains(coursename)){
			outputJSON.put("status", "enrolled");
		}
		else{
			outputJSON.put("status", "not enrolled");
			System.out.println("Inside not enrolled method");
		}
		
		}catch(Exception e){
			System.out.println("Exception occured"+e.getMessage());
		}
		
		return outputJSON.toString();
	}
	
	@POST
	@Path("/updateratings")
	public String updateRatings(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);
		
		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;
		
		String username = basicdbobject.getString("username");
		
		String courseid = basicdbobject.getString("courseid");
		
		String ratings =  basicdbobject.getString("rating");
		
		CoursesDAO courseDao = new CoursesDAO();
		
		BasicDBObject courseDBObject = courseDao.retrieveSelectedCourse(new BasicDBObject("courseid",courseid));
		
		double ratingsProvided = 0;
		
		
		
		if(courseDBObject.containsKey("ratings")){
			
			ratingsProvided = Double.parseDouble(courseDBObject.getString("ratings"));
			
			System.out.println("ratings disabled"+ratingsProvided);
			
			double finalRatings = ratingsProvided + Double.parseDouble(ratings) ;
			System.out.println("final ratings after:"+finalRatings/2);
			
			courseDBObject.put("ratings", finalRatings/2);
			
		}
		else{
			courseDBObject.put("ratings", ratings);
		}
		
		
		
		boolean status = courseDao.updateCourseRatings(courseDBObject);
		
		UserProfileDAO userProfileDao = new UserProfileDAO();
		BasicDBObject userDBObject = userProfileDao.retrieveBasicInformation(new BasicDBObject("username",username));
		
		ArrayList<BasicDBObject> ratingsProvidedCourses = new ArrayList<>();
		
		if(userDBObject.containsKey("ratingsprovided")){
			ratingsProvidedCourses = (ArrayList<BasicDBObject>) userDBObject.get("ratingsprovided");
			ratingsProvidedCourses.add(new BasicDBObject("coursename",courseDBObject.getString("coursename")));
		}else{
			ratingsProvidedCourses.add(new BasicDBObject("coursename",courseDBObject.getString("coursename")));
		}
		
		userDBObject.put("ratingsprovided", ratingsProvidedCourses);
		
		userProfileDao.updateBasicProfile(userDBObject);
		
		System.out.println("hello how are you");
		
		System.out.println(courseDBObject);
		
		System.out.println(userDBObject);
		
		return courseDBObject.toString();
		
	}
	
	public static void main(String[] args) throws JSONException {
		CoursesInfo coursesInfor = new CoursesInfo();
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("username", "manideep");
		jsonObject.put("courseid", "COMP-SCI 5540");
		jsonObject.put("rating", "4");
		coursesInfor.updateRatings(jsonObject.toString());
		
		System.out.println("Testinfgg");
	}
	

}

package com.ibm.cloudoe.samples;

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
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/courses")
	public String retrieveCourses(){
		
		CoursesDAO courseDAO = new CoursesDAO();
		
		DBCursor dbcoursecursor = courseDAO.retrieveAllCourses();
		
		return dbcoursecursor.toArray().toString();
				
	}

}

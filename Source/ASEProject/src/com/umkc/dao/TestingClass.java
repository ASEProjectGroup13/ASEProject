package com.umkc.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.umkc.pojo.CoursePOJO;

public class TestingClass {
	
	public static void main(String[] args) {
		
	
	
	CoursePOJO couserPOJO = new CoursePOJO();
	
	String course1 = "CS";
	String spec = "DS";
	
//	couserPOJO.setDepartment(req.getParameter("Department"));
//	couserPOJO.setSpecialization(req.getParameter("Specialization"));
	
	BasicDBObject basicDBObject = new BasicDBObject();
	
	basicDBObject.put("Department", course1);
	basicDBObject.put("Specialization", spec);
	
	CourseDAO courseDao = new CourseDAO();
	
	DBCursor dbCursor = courseDao.retrieveDocument(basicDBObject);
	
	List<CoursePOJO> coursesList = new ArrayList<CoursePOJO>();
	
	
	while (dbCursor.hasNext()) {
		
		DBObject dbObject = (DBObject) dbCursor.next();
		
		CoursePOJO courses = new CoursePOJO();
		courses.setCourseid( dbObject.get("courseid").toString());
		courses.setCoursename( dbObject.get("coursename").toString());
		courses.setCoursedescription( dbObject.get("coursedescription").toString());
		courses.setProfessorname(dbObject.get("professorname").toString());
		courses.setDepartment(dbObject.get("Department").toString());
		courses.setSpecialization(dbObject.get("Specialization").toString());
		
		
		System.out.println("courseid"+ dbObject.get("courseid"));
		System.out.println("Coursename"+ dbObject.get("coursename"));
		System.out.println("Course description"+dbObject.get("coursedescription"));
		
	}
	}


}

package com.umkc.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.umkc.dao.CourseDAO;
import com.umkc.pojo.CoursePOJO;

public class CoursesInfo extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CoursePOJO couserPOJO = new CoursePOJO();
		
		couserPOJO.setDepartment(req.getParameter("Department"));
		couserPOJO.setSpecialization(req.getParameter("Specialization"));
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		basicDBObject.put("Department", couserPOJO.getDepartment());
		basicDBObject.put("Specialization", couserPOJO.getSpecialization());
		
		CourseDAO courseDao = new CourseDAO();
		
		DBCursor dbCursor = courseDao.retrieveDocument(basicDBObject);
		
		List<CoursePOJO> coursesList = new ArrayList<CoursePOJO>();
		
		//ArrayList<String> listofcourses= new ArrayList<>();
		while (dbCursor.hasNext()) {
			
			DBObject dbObject = (DBObject) dbCursor.next();
			
			CoursePOJO courses = new CoursePOJO();
			courses.setCourseid( dbObject.get("courseid").toString());
			courses.setCoursename( dbObject.get("coursename").toString());
			courses.setCoursedescription( dbObject.get("coursedescription").toString());
			courses.setProfessorname(dbObject.get("professorname").toString());
			courses.setDepartment(dbObject.get("Department").toString());
			courses.setSpecialization(dbObject.get("Specialization").toString());

			//String appendcoursedetails= dbObject.get("courseid").toString()+":"+dbObject.get("coursename").toString()+":"+dbObject.get("professorname");
			
			//System.out.println(appendcoursedetails);
			//listofcourses.add(appendcoursedetails);
			coursesList.add(courses);
			
			
			System.out.println("courseid:"+ dbObject.get("courseid"));
			System.out.println("Coursename:"+ dbObject.get("coursename"));
			System.out.println("Course description:"+dbObject.get("coursedescription"));
			
		}
		
		req.setAttribute("courses", coursesList);
		
		req.setAttribute("Department", req.getParameter("Department"));
		req.setAttribute("Specialization", req.getParameter("Specialization"));
		
		courseDao.closeClient();
		
		
		
		req.getRequestDispatcher("courses.jsp").forward(req, resp);
		
	
	}

}

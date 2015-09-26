package com.umkc.users;

import java.io.IOException;
import java.util.concurrent.CancellationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.umkc.dao.CourseDAO;
import com.umkc.pojo.CoursePOJO;

public class AddCourse extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CoursePOJO addcoursepojo = new CoursePOJO();
		addcoursepojo.setCourseid(req.getParameter("courseid"));
		addcoursepojo.setCoursename(req.getParameter("coursename"));
		addcoursepojo.setCoursedescription(req.getParameter("coursedescription"));
		addcoursepojo.setProfessorname(req.getParameter("professorname"));
		addcoursepojo.setDepartment(req.getParameter("Department"));
		addcoursepojo.setSpecialization(req.getParameter("Specialization"));
		
		BasicDBObject basicdbobject = new BasicDBObject();
		basicdbobject.put("courseid", addcoursepojo.getCourseid());
		basicdbobject.put("coursename", addcoursepojo.getCoursename());
		basicdbobject.put("coursedescription", addcoursepojo.getCoursedescription());
		basicdbobject.put("professorname", addcoursepojo.getProfessorname());
		basicdbobject.put("Department", addcoursepojo.getDepartment());
		basicdbobject.put("Specialization", addcoursepojo.getSpecialization());
  CourseDAO coursedao=new CourseDAO();
  boolean status=coursedao.sendDataToMongoDB(basicdbobject);
  
  if (status){
		//Navigating the page to login.jsp if success
		req.setAttribute("status", "Registration successful");
	req.getRequestDispatcher("AdminHome.jsp").forward(req, resp);	
	}
	else{
		//Navigating the page to Registration.jsp if failed
		req.setAttribute("status", "Registration Failed");
		req.getRequestDispatcher("createCourse.jsp").forward(req, resp);
	}
	}
	
}

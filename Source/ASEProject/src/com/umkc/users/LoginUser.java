package com.umkc.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.umkc.dao.RegisterDAO;
import com.umkc.pojo.LoginPojo;

public class LoginUser extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getParameter("userName") != null && !req.getParameter("userName").isEmpty() && req.getParameter("password") != null && !req.getParameter("password").isEmpty() &&
				req.getParameter("userType") != null && !req.getParameter("userType").isEmpty()){
		
		LoginPojo loginPojo = new LoginPojo();
		
		loginPojo.setUsername(req.getParameter("userName"));
		
		loginPojo.setPassword(req.getParameter("password"));
		
		loginPojo.setUserType(req.getParameter("userType"));
		
		BasicDBObject loginDBObject = new BasicDBObject("username",loginPojo.getUsername());
		
		RegisterDAO loginDAO = new RegisterDAO();
		DBCursor loginDBCursor = loginDAO.retrieveDocument(loginDBObject);
		
		String password= "";
		String userType = "";
		
		while(loginDBCursor.hasNext()){
			BasicDBObject outputObject = (BasicDBObject) loginDBCursor.next();
			
			password = outputObject.getString("Password");
			
			userType = outputObject.getString("usertype");
			System.out.println("password"+password);
			System.out.println("usertype"+ userType);
		}

		
		if(loginPojo.getPassword().equals(password) && loginPojo.getUserType().equals(userType)){
			
			if(userType.equals("admin"))
				req.getRequestDispatcher("AdminHome.jsp").forward(req, resp);
			else if (userType.equals("professor")) 
				req.getRequestDispatcher("professorhome.jsp").forward(req, resp);
			else			
				req.getRequestDispatcher("homepage.jsp").forward(req, resp);
		}else {
			req.setAttribute("message", "login failed becuase of invalid entries.");
			req.getRequestDispatcher("Login.jsp").forward(req, resp);
		}
	}
		else {
			req.setAttribute("message", "Entries should not be empty");
			req.getRequestDispatcher("Login.jsp").forward(req, resp);
		}
			
		}

}

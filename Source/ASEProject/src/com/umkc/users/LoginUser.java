package com.umkc.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
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
		
		LoginPojo loginPojo = new LoginPojo();
		
		loginPojo.setUsername(req.getParameter("username"));
		loginPojo.setPassword(req.getParameter("password"));
		
		BasicDBObject loginDBObject = new BasicDBObject("username",loginPojo.getUsername());
		
		
		RegisterDAO loginDAO = new RegisterDAO();
		DBCursor loginDBCursor = loginDAO.retrieveDocument(loginDBObject);
		
//		DBObject result = loginDBCursor.getQuery();
//		
//		System.out.println(result.get("username"));
//		System.out.println(result.get("password"));
		
		String password= "";
		
		while(loginDBCursor.hasNext()){
			BasicDBObject outputObject = (BasicDBObject) loginDBCursor.next();
			
			password = outputObject.getString("Password");
			
			System.out.println("password"+password);
		}

		
		if(loginPojo.getPassword().equals(password)){
			req.getRequestDispatcher("homepage.jsp").forward(req, resp);
		}else {
			req.setAttribute("message", "login failed.");
			req.getRequestDispatcher("Login.jsp").forward(req, resp);
		}
	}

}

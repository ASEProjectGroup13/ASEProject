package com.ibm.cloudoe.samples;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.umkc.dao.CoursesDAO;
import com.umkc.dao.MongoDatabaseClient;


@Path("/mongo")
public class UserDetails {

	@GET
	public String getInformation() {
		return "Hi Hello How are you......?????";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response doLogin(String data) throws JSONException {
		
		System.out.println("hello");
		System.out.println("data received from front end"+data);
		
		Object jsonObject = JSON.parse(data);
		
		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;

		MongoDatabaseClient mongods = new MongoDatabaseClient();
		boolean status = mongods.performLoginValidation(basicdbobject);
		
		JSONObject statusJson = new JSONObject();
		
		if(status){
		try {
			statusJson.put("statusmessage", "success");
			System.out.println("setting up success message");
		} catch (JSONException e) {
			System.out.println("Exception"+e.getMessage());
		}
		}else{
			try {
				statusJson.put("statusmessage", "record already exists");
				System.out.println("Setting uo the failure message");
			} catch (JSONException e) {
				System.out.println("Exception"+e.getMessage());
			}
		}
		
		return Response.ok(statusJson.toString())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Max-Age", "1209600")
				.build();
		//return statusObject.toString();
		//return statusJson.toString();
	}
	
	
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertIntoMongo(String data) throws JSONException, IOException {
		System.out.println(data);

		Object dataJson = JSON.parse(data);

		DBObject dbObject = (DBObject) dataJson;

		System.out.println("after conversion" + dbObject.toString());

		MongoDatabaseClient mongoCLient = new MongoDatabaseClient();

		boolean status = mongoCLient.registerUser(dbObject);

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

		Response r = null;
		
		
		
		return jsonObject.toString();
	}
	
	
}
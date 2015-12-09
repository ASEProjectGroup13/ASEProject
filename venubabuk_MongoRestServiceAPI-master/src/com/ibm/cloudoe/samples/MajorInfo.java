package com.ibm.cloudoe.samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;
import com.umkc.dao.MajorsDAO;

@Path("majorinfo")
public class MajorInfo {
	
	@GET
	public String testMethod(){
		return "Hello Majors";
	}

	@GET
	@Path("majors")
	public String retrieveAllMajors() {

		MajorsDAO majorDAO = new MajorsDAO();
		DBCursor dbCursor = majorDAO.retrieveAllMajors();

		return dbCursor.toArray().toString();

	}

	@POST
	@Path("createmajor")
	public String createMajor(String jsonData) {

		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;

		MajorsDAO majorDAO = new MajorsDAO();

		boolean status = majorDAO.insertMajorInfo(basicdbobject);

		JSONObject statusJSON = new JSONObject();

		try {
			if (status) {

				statusJSON.put("statusmessage", "success");

			} else {
				statusJSON.put("statusmessage", "failure");
			}
		} catch (JSONException e) {
			System.out.println("Exception while converting into JSON" + e.getMessage());
		}
		
		return statusJSON.toString();

	}
	
	@POST
	@Path("updatemajor")
	public String updateMajor(String jsonData) {

		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;

		MajorsDAO majorDAO = new MajorsDAO();

		System.out.println(majorDAO.updateMajorInformation(basicdbobject));
		return majorDAO.updateMajorInformation(basicdbobject);

	}
	
	@POST
	@Path("deletemajor")
	public String deleteMajor(String jsonData) {

		System.out.println("data received from front end" + jsonData);

		Object jsonObject = JSON.parse(jsonData);

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject;

		MajorsDAO majorDAO = new MajorsDAO();

		JSONObject statusJson = new JSONObject();
		
		try {
		if(majorDAO.deleteMajorInformation(basicdbobject)){
			
				statusJson.put("statusmessage", "success");
			
		}
		else{
			statusJson.put("statusmessage", "failure");
		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return statusJson.toString();

	}

	public static void main(String[] args) {

		MajorsDAO majorsDAO = new MajorsDAO();

		DBCursor dbcursor = majorsDAO.retrieveAllMajors();

		ArrayList<String> arrayList = new ArrayList<String>();

		arrayList.add("Cloud Computing");
		arrayList.add("Principles of Big Data Management");
		arrayList.add("Real-time Big Data Analytics");

		TreeMap<String, BasicDBObject> majormap = new TreeMap<>();

		while (dbcursor.hasNext()) {
			BasicDBObject basicdbObject = (BasicDBObject) dbcursor.next();

			// String majorid = basicdbObject.getString("majorid");
			String majorname = basicdbObject.getString("majorname");
			BasicDBObject basicdbobject = (BasicDBObject) basicdbObject.get("courses");

			majormap.put(majorname, basicdbobject);

		}

		majorsDAO.closeConnection();

	}

}

package com.ibm.cloudoe.samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.ws.rs.Path;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.umkc.dao.MajorsDAO;

@Path("major")
public class MajorInfo {
	
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
			
			//String majorid = basicdbObject.getString("majorid");
			String majorname = basicdbObject.getString("majorname");
			BasicDBObject basicdbobject = (BasicDBObject) basicdbObject.get("courses");
			
			majormap.put(majorname, basicdbobject);
			
		}
		
		majorsDAO.closeConnection();
		
	}

}

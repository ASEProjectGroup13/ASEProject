package com.umkc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class CourseRecommendationDAO {

	static MajorsDAO majorDAO = new MajorsDAO();
	public int checkWhetherDataScienceMatched(ArrayList<String> arrayList){
		
		BasicDBObject basicdbobject = new BasicDBObject();
		basicdbobject.put("majorid","DS0001");
		basicdbobject.put("majorname", "Data Science");
		DBCursor dbCursor = majorDAO.retrieveSelectedMajor(basicdbobject);
		
		BasicDBObject courseDBObject= null;
		
		HashMap<String, String> hashMap = null;
		
		if (dbCursor.hasNext()) {
			BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
			
			courseDBObject = (BasicDBObject) dbObject.get("courses");
			
			hashMap = (HashMap<String, String>) courseDBObject.toMap();
		}
		
		ArrayList<String> majorCoursesList = new ArrayList<>(hashMap.values());
		
		ArrayList<String> commonList = new ArrayList<>(majorCoursesList);
		
		commonList.retainAll(arrayList);
		
		int size = commonList.size();
		
		System.out.println("no of elements match is equal to "+size);
		
		return size;
	}
	
public int checkWhetherBioInformaticsMatched(ArrayList<String> arrayList){
		
		BasicDBObject basicdbobject = new BasicDBObject();
		basicdbobject.put("majorid","DS0001");
		basicdbobject.put("majorname", "Data Science");
		DBCursor dbCursor = majorDAO.retrieveSelectedMajor(basicdbobject);
		
		BasicDBObject courseDBObject= null;
		
		HashMap<String, String> hashMap = null;
		
		if (dbCursor.hasNext()) {
			BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
			
			courseDBObject = (BasicDBObject) dbObject.get("courses");
			
			hashMap = (HashMap<String, String>) courseDBObject.toMap();
		}
		
		ArrayList<String> majorCoursesList = new ArrayList<>(hashMap.values());
		
		ArrayList<String> commonList = new ArrayList<>(majorCoursesList);
		
		commonList.retainAll(arrayList);
		
		int size = commonList.size();
		
		System.out.println("no of elements match is equal to "+size);
		
		return size;
	}
	
	public static void main(String[] args) {
		CourseRecommendationDAO cdao = new CourseRecommendationDAO();
		ArrayList<String> arrayList = new ArrayList<String>();
		
		arrayList.add("Cloud Computing");
		arrayList.add("Principles of Big Data Management");
		arrayList.add("Real-time Big Data Analytics");
		cdao.allMajorsMap();
	}
	
	
	public HashMap<String, HashMap<String, String>> allMajorsMap(){
		
		DBCursor dbCursor = majorDAO.retrieveAllMajors();
		
		HashMap<String, HashMap<String, String>> majorsMap = new HashMap<>();
		while(dbCursor.hasNext()){
			BasicDBObject basicdbobject = (BasicDBObject) dbCursor.next();
			
			String majorname = basicdbobject.getString("majorname");
			BasicDBObject basicDBObject = (BasicDBObject) basicdbobject.get("courses");
			
			HashMap<String, String> coursesMap = (HashMap<String, String>) basicDBObject.toMap();
			
			majorsMap.put(majorname, coursesMap);
		}
		
		System.out.println("majors map"+majorsMap);
		
		return majorsMap;
	}
}

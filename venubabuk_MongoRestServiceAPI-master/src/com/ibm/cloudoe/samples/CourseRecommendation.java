package com.ibm.cloudoe.samples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.umkc.dao.CourseRecommendationDAO;
import com.umkc.dao.UserProfileDAO;

@Path("recommend")
public class CourseRecommendation {
	
	@POST
	@Path("recommendcourse")
	public String recommendCourses(String jsonData){
		
		System.out.println("data received from front end" + jsonData);

		String courseArray[] = jsonData.split(",");
		
		String username = courseArray[0];
		
		ArrayList<String> treeSet = new ArrayList<>();
		
		int i=1;
		while(i <courseArray.length){
			
			treeSet.add(courseArray[i]);
			i++;
		}

		BasicDBObject basicdbobject = new BasicDBObject("username",username);
		
		UserProfileDAO userprofileDAO = new UserProfileDAO();
		
		BasicDBObject basicdbObject = userprofileDAO.retrieveBasicInformation(basicdbobject);
		
		
		
		ArrayList<BasicDBObject> completedCourseList = (ArrayList<BasicDBObject>) basicdbObject.get("completedcourses");
		
		ArrayList<BasicDBObject> enrolledCourseList = (ArrayList<BasicDBObject>) basicdbObject.get("enrolledcourses");
		
		for (BasicDBObject basicDBObject2 : completedCourseList) {
			treeSet.add(basicDBObject2.getString("coursename"));
		}
		
		for(BasicDBObject basicDBObject3: enrolledCourseList){
			treeSet.add(basicDBObject3.getString("coursename"));
		}
		
		HashMap<String, Integer> tempMap = retrieveMatchedScores(treeSet);

		System.out.println("results out are:" + tempMap);

		int maxsize = Collections.max(tempMap.values());
		String majorname = null;
		for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
		if (entry.getValue() == maxsize) {
		majorname = entry.getKey();
		}
		}

		System.out.println("recommended major name" + majorname);
		
		JSONObject outputJSON = new JSONObject();
		try {
			outputJSON.put("majorname", majorname);
			
			HashMap<String, ArrayList<String>> recommendedCourses = differentCourses(treeSet, majorname);
			
			ArrayList<JSONObject> arrayList = new ArrayList<>();
			
			JSONObject jsonObjects = new JSONObject();
			for(String s: recommendedCourses.get(majorname)){
				jsonObjects.put("coursename", s);
				
				arrayList.add(jsonObjects);
			}
			outputJSON.put("courses", arrayList);
			System.out.println("recommended major name along with list of courses::\t" + recommendedCourses.get(majorname));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("final json"+outputJSON);
		return outputJSON.toString();
		
		
		
	}
	

	public static void main(String[] args) throws JSONException {
//		ArrayList<String> arrayList = new ArrayList<String>();
//		HashMap<String, Integer> scoresMap = new HashMap<>();
//		arrayList.add("Cloud Computing");
//		arrayList.add("Principles of Big Data Management");
//		arrayList.add("Real-time Big Data Analytics");
//
//		HashMap<String, Integer> tempMap = retrieveMatchedScores(arrayList);
//
//		System.out.println("results out are:" + tempMap);
//
//		int maxsize = Collections.max(tempMap.values());
//		String majorname = null;
//		for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
//			if (entry.getValue() == maxsize) {
//				majorname = entry.getKey();
//			}
//		}
//
//		System.out.println("recommended major name" + majorname);
//		HashMap<String, ArrayList<String>> recommendedCourses = differentCourses(arrayList, majorname);
//		System.out.println("recommended major name along with list of courses::\t" + recommendedCourses);
		
		CourseRecommendation courseRecommendation = new CourseRecommendation();
		
		JSONObject jsonObject = new JSONObject();
		
	
		jsonObject.put("username", "manideep");
		
		Object jsonObject1 = JSON.parse(jsonObject.toString());

		BasicDBObject basicdbobject = (BasicDBObject) jsonObject1;
		
		
		String test = courseRecommendation.recommendCourses(jsonObject.toString());
		
		System.out.println(test+"Testing for which");

	}

	public static HashMap<String, ArrayList<String>> differentCourses(ArrayList<String> arrayList, String majorname) {
		// TODO Auto-generated method stub
		CourseRecommendationDAO courseDAO = new CourseRecommendationDAO();
		HashMap<String, HashMap<String, String>> majorsCourseMap = courseDAO.allMajorsMap();
		HashMap<String, String> recommendedCourses = majorsCourseMap.get(majorname);
		ArrayList<String> listofCourses = new ArrayList<>(recommendedCourses.values());
		listofCourses.removeAll(arrayList);
		HashMap<String, ArrayList<String>> majorAndCourses = new HashMap<>();
		majorAndCourses.put(majorname, listofCourses);
		return majorAndCourses;
	}

	public static HashMap<String, Integer> retrieveMatchedScores(ArrayList<String> arrayList) {
		CourseRecommendationDAO courseDAO = new CourseRecommendationDAO();

		HashMap<String, HashMap<String, String>> majorsCourseMap = courseDAO.allMajorsMap();

		HashMap<String, Integer> matchedScoresMap = new HashMap<>();

		Iterator<Entry<String, HashMap<String, String>>> iterator = majorsCourseMap.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, HashMap<String, String>> entry = (Map.Entry<String, HashMap<String, String>>) iterator
					.next();

			String majorname = entry.getKey();

			HashMap<String, String> hashMap = entry.getValue();

			ArrayList<String> coursesList = new ArrayList<>(hashMap.values());

			ArrayList<String> commonCourseList = new ArrayList<>(coursesList);

			commonCourseList.retainAll(arrayList);

			int size = commonCourseList.size();

			System.out.println("courses matched in" + majorname + "no of courses matched:" + size
					+ "Matched courses are:" + commonCourseList);

			matchedScoresMap.put(majorname, size);
		}

		return matchedScoresMap;
	}
}

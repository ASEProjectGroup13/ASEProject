package com.ibm.cloudoe.samples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.umkc.dao.CourseRecommendationDAO;

public class CourseRecommendation {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();
		HashMap<String, Integer> scoresMap = new HashMap<>();
		arrayList.add("Cloud Computing");
		arrayList.add("Principles of Big Data Management");
		arrayList.add("Real-time Big Data Analytics");
		
		HashMap<String, Integer> tempMap = retrieveMatchedScores(arrayList);
		
		System.out.println("results out are:"+tempMap);
		
		int maxsize = Collections.max(tempMap.values());
		String majorname = null;
		for(Map.Entry<String, Integer> entry:tempMap.entrySet()){
			if(entry.getValue() == maxsize){
				majorname = entry.getKey();
			}
		}
		
		System.out.println("recommended major name"+majorname);
		HashMap<String, ArrayList<String>> recommendedCourses= differentCourses(arrayList,majorname);
		System.out.println("recommended major name along with list of courses::\t"+recommendedCourses);
	
		
	}
	
	public static HashMap<String, ArrayList<String>> differentCourses(ArrayList<String> arrayList, String majorname) {
		// TODO Auto-generated method stub
		CourseRecommendationDAO courseDAO = new CourseRecommendationDAO();
		HashMap<String, HashMap<String, String>> majorsCourseMap = courseDAO.allMajorsMap();
		HashMap<String, String> recommendedCourses=majorsCourseMap.get(majorname);
		ArrayList<String> listofCourses= new ArrayList<>(recommendedCourses.values());
		listofCourses.removeAll(arrayList);
		HashMap<String, ArrayList<String>> majorAndCourses=new HashMap<>();
		majorAndCourses.put(majorname, listofCourses);
		return majorAndCourses;
	}

	public static HashMap<String, Integer> retrieveMatchedScores(ArrayList<String> arrayList){
CourseRecommendationDAO courseDAO = new CourseRecommendationDAO();
		
		HashMap<String, HashMap<String, String>> majorsCourseMap = courseDAO.allMajorsMap();
		
		HashMap<String, Integer> matchedScoresMap = new HashMap<>();
		
		Iterator<Entry<String, HashMap<String, String>>> iterator = majorsCourseMap.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<String, HashMap<String,String>> entry = (Map.Entry<String, HashMap<String, String>>) iterator
					.next();
			
			String majorname = entry.getKey();
			
			HashMap<String, String> hashMap = entry.getValue();
			
			ArrayList<String> coursesList = new ArrayList<>(hashMap.values());
			
			ArrayList<String> commonCourseList = new ArrayList<>(coursesList);
			
			commonCourseList.retainAll(arrayList);
			
			int size = commonCourseList.size();
			
			System.out.println("courses matched in"+majorname+"no of courses matched:"+ size+"Matched courses are:"+commonCourseList);
			
			
			
			matchedScoresMap.put(majorname, size);
		}
		
		return matchedScoresMap;
	}
}

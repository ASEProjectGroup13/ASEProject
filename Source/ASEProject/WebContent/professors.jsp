<%@page import="com.mongodb.DBObject"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="com.umkc.pojo.CoursePOJO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Courses</title>

<style type="text/css">
#header {
	background-color: black;
	color: white;
	text-align: center;
	padding: 5px;
}

#nav {
	line-height: 30px;
	background-color: #eeeeee;
	height: 300px;
	width: 200px;
	float: left;
	padding: 5px;
}

#section {
	width: 350px;
	float: left;
	padding: 10px;
}

#footer {
	background-color: black;
	color: white;
	clear: both;
	text-align: center;
	padding: 5px;
}
</style>
</head>
<body background="images/professor.jpg">
	<div id="header">
		<h1>Course Recommendations</h1>
		<h2 align="right"><label>welcome Admin</label></right></h2>
	</div>
	<div id="nav">
		<a href="courses.jsp">Courses</a><br> <a href="professors.jsp">Professors</a><br>
		
	

	</div>
	<div id="section">
		<h1>Professors List</h1>
		<form action="CoursesInfo" method="get">
			<table>
			<tr>
			<th style="width:100px; background-color:#CCCCCC">Professor Name</th>
            <th style="width:100px; background-color:#CCCCCC">Designation</th>
            <th style="width:100px; background-color:#CCCCCC">Specialization</th>
          
				<tr>
					<td align = "center">Dr.Yugyung Lee</td>
					<td align="center">Associate Professor</td>
					<td align="center">Data Science</td>
				</tr>

				<tr>
					<td align = "center">Dr.Praveen Rao</td>
					<td align="center">Associate Professor</td>
					<td align="center">Data Science</td>
				</tr>
				<tr>
					<td align = "center">Dr. Ghulam Chaudhary</td>
					<td align="center">Dean of SCE</td>
					<td align="center">VLSI</td>
				</tr>
			</table>
		</form>
			<%
		if(	request.getAttribute("courses")!=null)
		{
		
			%>
		
		<table id="tableHeader">
        <tr>
            <th style="width:100px; background-color:#CCCCCC">courseid</th>
            <th style="width:100px; background-color:#CCCCCC">coursename</th>
            <th style="width:100px; background-color:#CCCCCC">professorname</th>
            <th style="width:100px; background-color:#CCCCCC">coursedescription</th><br>
        </tr>
   
<c:forEach items="${courses}" var="element"> 
  <tr>
    <td>${element.courseid}</td>
     <td>${element.coursename}</td>
      <td>${element.professorname}</td>
      <td>${element.coursedescription}</td>
   </tr>
</c:forEach>
	 </table>
			
		<% }
		%>
	</div>
	<div id="footer">Copyrights @Group13</div>
</body>
</html>
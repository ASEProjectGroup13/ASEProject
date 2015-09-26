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
<body>
	<div id="header">
		<h1>Course Recommendation</h1>
		<h2 align="right"><label>welcome Admin</label></right></h2>
	</div>
	<div id="nav">
		<!-- <a href="courses.jsp">Courses</a><br> <a href="professors.jsp">Professors</a><br> -->
		
		<a href = "courses.jsp">Courses</a><br>
<a href = "createCourse.jsp">Add Course</a><br>
<a href = "modifyCourse.jsp">Modify Course</a><br>
<a href = "deleteCourse.jsp">Delete Course</a><br>
<a href = "professors.jsp">Professors List</a><br>
		

	</div>
	<div id="section">
		<h1>Courses List</h1>
		<form action="CoursesInfo" method="get">
			<table>
				<tr>
					<td><label>Choose:</label>
					<td align="center"><select name="Department">
							<option value="CS">Computer Science</option>
							<option value="EE">Electrical Engineering</option>

					</select></td>
				</tr>

				<tr>
					<td><label>Choose:</label>
					<td align="center"><select name="Specialization">
							<option value="DS">Data Science</option>
							<option value="SE">Software Engineering</option>
							<option value="DBMS">DataBase Management Systems</option>
							<option value="BI">Bio-informatics</option>
							<option value="NW">Networking</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Display"></td>
					<td></td>
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
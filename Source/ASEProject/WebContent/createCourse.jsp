<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Course</title>
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

#notifications {
	line-height: 30px;
	background-color: #eeeeee;
	height: 300px;
	width: 600px;
	float: right;
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
<body background="images/umkc.jpg">
	<div id="header">
		<h1>Course Recommendation</h1>
		<h2 align="right"><label>welcome Admin</label></right></h2>
	</div>
	<div id="nav">
	
		<a href="createCourse.jsp">Add Course</a><br> <a
			href="modifyCourse.jsp">Modify Course</a><br> <a
			href="deleteCourse.jsp">Delete Course</a><br><a
			href="courses.jsp">Courses List</a><br><br>
			<a
			href="professors.jsp">Professors List</a><br><br>


	</div>
	<div id="section">
	
	<h1>CreateCourse</h1>
		<form action="AddCourse" method="get">
			<table>
				<tr>
					<td align="center"><label>Enter course id:</label></td>
					<td align="center"><input type="text" name="courseid"></td>
				</tr>
				<tr>
					<td align="center"><label>Course Name:</label></td>
					<td align="center"><input type="text" name="coursename"></td>
				</tr>
				<tr>
					<td align="center"><label>Course Description:</label></td>
					<td align="center"><textarea name="coursedescription" cols="18" rows="5"></textarea></td>
				</tr>
				<tr>
					<td align="center"><label>Professor Name:</label></td>
					<td align="center"><input type="text" name="professorname"></td>
				</tr>
				<tr>
			<td><label>Choose:</label><td align="center">
			<select name="Department">
				<option value="CS">Computer Science</option>
				<option value="EE">Electrical Engineering</option>
				
			</select>
			</td>
			</tr>
			
			<tr>
			<td><label>Choose:</label><td align="center">
			<select name="Specialization">
				<option value="DS">Data Science</option>
				<option value="SE">Software Engineering</option>
				<option value="DBMS">DataBase Management Systems</option>
				<option value="BI">Bio-informatics</option>
				<option value="NW">Networking</option>
			</select>
			</td>
			</tr>
				<tr>
				<td align="center"><input type="submit" value="Create" ></td>
				</tr>
				
			</table>
			
		</form>


	</div>
	<div id="footer">Copyrights @ ASE Group13</div>

</body>
</html>
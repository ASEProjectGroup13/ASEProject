<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify course</title>
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
		<h1>Course Recommendations</h1>
		<h2 align="right"><label>welcome Admin</label></right></h2>
	</div>
	<div id="nav">
		<a href="createCourse.jsp">Add Course</a><br> <a
			href="modifyCourse.jsp">Modify Course</a><br> <a
			href="courses.jsp">Courses List</a><br> <br>
			<a
			href="professors.jsp">Professors List</a><br><br>

	</div>
	<div id="section">
<h1>Modify Course</h1>
		<form action="ListCourses" method="get">
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
	</div>


	<div id="section">

		<form action="DeleteCourse" method="get">
			<table>
				<tr>
					<td><label>choose:</label>
					<td align="center"><select name="coursename">
					<option value="select coursename">select coursename</option>
							<c:forEach items="${courses}" var="element">

								<option value="${element.courseid}">${element.coursename}</option>

							</c:forEach>

					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Modify"></td>
				</tr>
			</table>
		</form>

	</div>
	<div id="footer">Copyrights @ ASE Group13</div>

</body>
</html>
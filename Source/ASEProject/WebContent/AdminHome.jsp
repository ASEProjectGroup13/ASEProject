<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Home Page</title>
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
<body background="images/admin.jpeg">
<div id = "header"><h1>Course Recommendation</h1></div>
<div id = "nav">
<a href = "courses.jsp">Courses</a><br>
<a href = "createCourse.jsp">Add Course</a><br>
<a href = "modifyCourse.jsp">Modify Course</a><br>
<a href = "deleteCourse.jsp">Delete Course</a><br>

</div>
<div id = "section">

<h2>Welcome Admin</h2>

</div>
<div id = "footer">Copyrights @ ASE Group13</div>

</body>
</html>
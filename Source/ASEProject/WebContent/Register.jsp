<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register Page</title>
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
	</div>

	<div id="section">
	<center><font color="red">${status};</font></center>

		<h2>Register Page</h2>
		<form action="RegisterUser" method="get">
			<table align="center">
			
			<tr>
			<td><label>Choose:</label><td align="center">
			<select name="userType">
				<option value="student">Student</option>
				<option value="admin">Admin</option>
				<option value="professor">Professor</option>
			</select>
			</td>
			</tr>
				<tr>
					<td align="center"><label>User Name</label></td>
					<td align="center"><input name="username" type="text"></td>
				</tr>
				<tr>
					<td align="center"><label>First Name</label></td>
					<td align="center"><input name="firstName" type="text"></td>
				</tr>
				<tr>
					<td align="center"><label>Last Name</label></td>
					<td align="center"><input name="lastName" type="text"></td>
				</tr>
				<tr>
					<td align="center"><label>Phone Number</label></td>
					<td align="center"><input name="phoneNumber" type="text"></td>
				</tr>
				<tr>
					<td align="center"><label>Email-ID </label></td>
					<td align="center"><input name="emailId" type="text"></td>
				</tr>
				<tr>
					<td align="center"><label>Password </label></td>
					<td align="center"><input name="password" type="password"></td>
				</tr>
				<tr>
					<td align="center"><label>Confirm Password</label></td>
					<td align="center"><input name="confirmPassword"
						type="password"></td>
				</tr>
				
			</table>
			<input type="submit" name = "register" value="Register" >

	</form>

	</div>
	<div id="notifications">
		<h1>Notifications</h1>
	</div>
	<div id="footer">Copyrights @ASE Group13</div>
</body>
</html>
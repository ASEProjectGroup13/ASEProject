<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
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
<body>

<div id="header">
		<h1>Student Recommendations</h1>
	</div>
	<div id="section">

		<h2>Login Page</h2>
		<form action="LoginUser" method="get">
			<table align="center">
				<tr>
					<td align="center"><label>User Name:</label></td>
					<td align="center"><input name="userName" type="text"></td>
				</tr>
				<tr>
					<td align="center"><label>Password:</label></td>
					<td align="center"><input name="password" type="text"></td>
				</tr>
			</table>
			<input type="submit" name = "login" value="Login" >

	</form>

	</div>
	<div id="notifications">
		<h1>Notifications</h1>
	</div>
	<div id="footer">Copyrights @ASE Group13</div>
<h1>Hello</h1>
</body>
</html>
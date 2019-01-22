
<%@ page contentType="text/html"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Home</title>
	</head>
	
	<body>
		<h1 align='center'>Wellcome to Home</h1>
		<a align="center">User Name : <%= session.getAttribute("JSESSIONUSERNAME") %></a><br/>
		<a align="center" href="../account/logout" > Logout </a> 
	</body>
</html>
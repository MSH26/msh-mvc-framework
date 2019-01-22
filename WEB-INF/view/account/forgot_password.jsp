
<%@ page contentType="text/html"  pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<title>Forgot Password</title>
	</head>
	
	<body>
		<fieldset>
			<legend><h3 align="center">Insert Your User Name</h3></legend>
			
			<form method="post">
				<b>User Name</b>: <input type='text' name='username'> <%= request.getAttribute("err_username")==null?"":request.getAttribute("err_username") %><hr/>
				<input type='submit' value='Submit' />
			</form>
		</fieldset>
		<a align="center" href="../registration/basicregistration" >Go Registration </a> | <a align="center" href="login" >Login </a>
	</body>
</html>
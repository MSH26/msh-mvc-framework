
<%@ page contentType="text/html"  pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
	</head>
	
	<body>
		<div align="center">
			<%= request.getAttribute("err_account")==null?"":request.getAttribute("err_account") %>
			<fieldset>
				<legend><h3 align="center">LOGIN PORTAL</h3></legend>
					<form method="post">
						<div align="center">
							<b>User Name / Email</b>: 
							<input type='text' name='user_name' placeholder='User Name' value=''><abbr title='Email address'><b>i</b></abbr> <%= request.getAttribute("err_user_name")==null?"":request.getAttribute("err_user_name") %><hr/>
							<b>Password</b>: 
							<input type='password' name='password' placeholder='Password' value=''><br/> <%= request.getAttribute("err_password")==null?"":request.getAttribute("err_password") %><hr/>
							<input type='submit' value='LOGIN' /><br/><hr/>
						</div>
					</form>
				<a align="center" href="../registration/basicregistration" >Go Registration </a> | <a align="center" href="forgotpassword" >Forgot Password </a>
			</fieldset>
		</div>
	</body>
</html>
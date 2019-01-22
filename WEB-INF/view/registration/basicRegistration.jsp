
<%@ page contentType="text/html"  pageEncoding="UTF-8"%>

<!DOCTYPE html  public "-//w3c//dtd html 4.0 transitional//en">
<html>

	<head>
		<title>REGISTRATION</title>
	</head>
	
	<body>
		<fieldset>
			<legend><h3 align="center">BASIC REGISTRATION </h3></legend>
			<form method="post">
				<b>First Name</b>: <input type='text' name='first_name' placeholder='First name' value='<%= request.getParameter("first_name")==null?"":request.getParameter("first_name") %>'> <%= request.getAttribute("err_first_name")==null?"":request.getAttribute("err_first_name") %><hr/>
				<b>Last Name</b>: <input type='text' name='last_name' placeholder='Last name' value='<%= request.getParameter("last_name")==null?"":request.getParameter("last_name") %>'> <%= request.getAttribute("err_last_name")==null?"":request.getAttribute("err_last_name") %><hr/>
				<b>Date Of Birth</b>: <input type='text' name='date_of_birth' placeholder='MM/DD/YYYY' value='<%= request.getParameter("date_of_birth")==null?"":request.getParameter("date_of_birth") %>'> <abbr title='MM/DD/YYYY'><b>i</b></abbr> <%= request.getAttribute("err_date_of_birth")==null?"":request.getAttribute("err_date_of_birth") %><hr/>
				<b>Gender</b>:
				<%
					if(request.getParameter("gender") != null){
						if(request.getParameter("gender").equals("male")){
				%>
							<input type='radio' name='gender' value='male' checked> Male 
							<input type='radio' name='gender' value='female'> Female 
							<input type='radio' name='gender' value='other'> Other 
				<%
						}
						else if(request.getParameter("gender").equals("female")){
				%>
							<input type='radio' name='gender' value='male'> Male 
							<input type='radio' name='gender' value='female' checked> Female 
							<input type='radio' name='gender' value='other'> Other 
				<%
						}
						else if(request.getParameter("gender").equals("other")){
				%>
							<input type='radio' name='gender' value='male'> Male 
							<input type='radio' name='gender' value='female'> Female 
							<input type='radio' name='gender' value='other' checked> Other 
				<%
						}
					}
					else{
				%>
						<input type='radio' name='gender' value='male'> Male 
						<input type='radio' name='gender' value='female'> Female 
						<input type='radio' name='gender' value='other'> Other 
						<%= request.getAttribute("err_gender")==null?"":request.getAttribute("err_gender") %>
				<% } %>
				<hr/>
				<b>Email</b>: <input type='text' name='email' value='<%= request.getParameter("email")==null?"":request.getParameter("email") %>'> <abbr title='hint: sample@example.com'><b>i</b></abbr> <%= request.getAttribute("err_email")==null?"":request.getAttribute("err_email") %><hr/>
				<b>Phone</b>: <input type='text' name='phone' value='<%= request.getParameter("phone")==null?"":request.getParameter("phone") %>'> <%= request.getAttribute("err_phone")==null?"":request.getAttribute("err_phone") %><hr/>
				<b>Password</b>: <input type='password' name='password' value='<%= request.getParameter("password")==null?"":request.getParameter("password") %>'><abbr title='must contain one lowercase(a-z),one uppercase(A-Z), one special(@,#,$,&) character. Length at least 8'><b>i</b></abbr> <%= request.getAttribute("err_password")==null?"":request.getAttribute("err_password") %><hr/>
				<b>Confirm Password</b>: <input type='password' name='confirm_password' value='<%= request.getParameter("confirm_password")==null?"":request.getParameter("confirm_password") %>'> <%= request.getAttribute("err_confirm_password")==null?"":request.getAttribute("err_confirm_password") %><hr/>
				<input type='submit' value='Submit' />
				<input type='reset' />
			</form>
			<hr/>
			<a align="center" href="../account/login" >Go Login</a>
		</fieldset>
	</body>
</html>
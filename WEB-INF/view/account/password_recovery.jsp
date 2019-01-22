
<%@ page contentType="text/html"  pageEncoding="UTF-8"%>


<jsp:useBean id = "user" class = "mvc.model.UserModel" scope = "session" />


<!DOCTYPE html>
<html>
	<head>
		<title>Forgot Password</title>
	</head>
	
	<body>
		 <%= request.getAttribute("err_operation")==null?"":request.getAttribute("err_operation") %>
		<fieldset>
			<legend><h3 align="center">Password Recovery</h3></legend>
			
			<form method="post">
				<b>Current Password</b>: <input type='password' name='current_password' value=''> <%= request.getAttribute("err_current_password")==null?"":request.getAttribute("err_current_password") %><hr/>
				<b>Password</b>: <input type='password' name='password' value=''><abbr title='must contain one lowercase(a-z),one uppercase(A-Z), one special(@,#,$,&) character. Length at least 8'><b>i</b></abbr> <%= request.getAttribute("err_password")==null?"":request.getAttribute("err_password") %><hr/>
				<b>Confirm Password</b>: <input type='password' name='confirm_password' value=''> <%= request.getAttribute("err_confirm_password")==null?"":request.getAttribute("err_confirm_password") %><hr/>
				<b>Security Question One </b>:
				<%= user.getQuestionOne() == null ?  "" : user.getQuestionOne() %> : <input type='text' name='answer_one' value=''> <%= request.getAttribute("err_answer_one")==null?"":request.getAttribute("err_answer_one") %><hr/>
				<b>Security Question Two</b>: 
				<%= user.getQuestionTwo()  == null ?  "" : user.getQuestionTwo() %> : <input type='text' name='answer_two' value=''> <%= request.getAttribute("err_answer_two")==null?"":request.getAttribute("err_answer_two") %><hr/>
				<b>Security Question Three</b>: 
				<%= user.getQuestionThree() == null ?  "" : user.getQuestionThree() %> : <input type='text' name='answer_three' value=''> <%= request.getAttribute("err_answer_three")==null?"":request.getAttribute("err_answer_three") %><hr/>
				<input type='submit' value='Submit' />
			</form>
		</fieldset>
		<a align="center" href="../registration/basicregistration" >Go Registration </a> | <a align="center" href="login" >Login </a>
	</body>
</html>
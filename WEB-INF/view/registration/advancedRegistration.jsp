
<%@ page contentType="text/html"  pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<title>REGISTRATION</title>
	</head>
	
	<body>
		<%= request.getAttribute("err_db_exception")==null?"":request.getAttribute("err_question_one") %>
		<fieldset>
			<legend><h3 align="center">ADVANCED REGISTRATION </h3></legend>
			<form method="post">
				<h4 align="center">Write three question(not more than 50 character per question) and it's answer(not more than 10 character per answer). This question will be asked for security reason latter</h4>
				<b>Question One</b>: 
				<input type='text' name='question_one' value='<%= request.getParameter("question_one")==null?"":request.getParameter("question_one") %>'> <%= request.getAttribute("err_question_one")==null?"":request.getAttribute("err_question_one") %><hr/>
				<b>Answer One</b>:
				<input type='text' name='answer_one' value='<%= request.getParameter("answer_one")==null?"":request.getParameter("answer_one") %>'> <%= request.getAttribute("err_answer_one")==null?"":request.getAttribute("err_answer_one") %><hr/>
				<b>Question Two</b>: 
				<input type='text' name='question_two' value='<%= request.getParameter("question_two")==null?"":request.getParameter("question_two") %>'> <%= request.getAttribute("err_question_two")==null?"":request.getAttribute("err_question_two") %><hr/>
				<b>Answer Two</b>:
				<input type='text' name='answer_two' value='<%= request.getParameter("answer_two")==null?"":request.getParameter("answer_two") %>'> <%= request.getAttribute("err_answer_two")==null?"":request.getAttribute("err_answer_two") %><hr/>
				<b>Question Three</b>: 
				<input type='text' name='question_three' value='<%= request.getParameter("question_three")==null?"":request.getParameter("question_three") %>'> <%= request.getAttribute("err_question_three")==null?"":request.getAttribute("err_question_three") %><hr/>
				<b>Answer Three</b>:
				<input type='text' name='answer_three' value='<%= request.getParameter("answer_three")==null?"":request.getParameter("answer_three") %>'> <%= request.getAttribute("err_answer_three")==null?"":request.getAttribute("err_answer_three") %><hr/>
				<input type='submit' value='Submit' />
				<input type='reset' />
			</form>
		</fieldset>
	</body>
</html>
package mvc.controller;

import mvc.systemfiles.systemclasses.models.Model;
import mvc.controller.interfaces.IController;
import mvc.model.UserModel;
import repository.UserDataAccess;
import mvc.systemfiles.systemclasses.controllers.BaseController;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;

import javax.servlet.http.*;
import java.util.regex.*;


public class AdvancedRegistrationController extends BaseController implements IController{
	
	public ActionResult get(HttpServletRequest request,HttpServletResponse response, Model model){
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1*60*60);
		
		if(session.getAttribute("JSESSIONLOGSTATUS") != null){
			if(session.getAttribute("JSESSIONLOGSTATUS").equals("LOOGGED_IN")){
				return redirectToAction("Home", "Home", request, response);
			}
		}
		
		if(session.getAttribute("registrationStatus") != null){
			/** 
			*	'0' means do basic registration
			*	'1' means advanced registration incomplete
			*/
			if(session.getAttribute("registrationStatus").equals("1")){ 
				return view(request, response);
			}
			else{
				return redirectToAction("BasicRegistration", request, response);
			}
		}
		else{
			session.setAttribute("registrationStatus", "0");
			return view("/index.html", request, response);
		}
	}
	
	public ActionResult post(HttpServletRequest request,HttpServletResponse response, Model model){
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1*60*60);
		
		if(session.getAttribute("JSESSIONLOGSTATUS") != null){
			if(session.getAttribute("JSESSIONLOGSTATUS").equals("LOOGGED_IN")){
				return redirectToAction("Home", "Home", request, response);
			}
		}
		
		if(session.getAttribute("registrationStatus") == "1"){
			Boolean validityStatusFlag = true;
			
			String errorMessages = "<fieldset> These field cross the word length";
			if(!isValidLength(request.getParameter("question_one"), 50)){
				validityStatusFlag = false;
				request.setAttribute("err_question_one", "Not more than 50 character");
			}
			if(!isValidLength(request.getParameter("answer_one"), 10)){
				validityStatusFlag = false;
				request.setAttribute("err_answer_one", "Not more than 10 character");
			}
			if(!isValidLength(request.getParameter("question_two"), 50)){
				validityStatusFlag = false;
				request.setAttribute("err_question_two", "Not more than 50 character");
			}
			if(!isValidLength(request.getParameter("answer_two"), 10)){
				validityStatusFlag = false;
				request.setAttribute("err_answer_two", "Not more than 10 character");
			}
			if(!isValidLength(request.getParameter("question_three"), 50)){
				validityStatusFlag = false;
				request.setAttribute("err_question_three", "Not more than 50 character");
			}
			if(!isValidLength(request.getParameter("answer_three"), 10)){
				validityStatusFlag = false;
				request.setAttribute("err_answer_three", "Not more than 10 character");
			}
			if(validityStatusFlag == true){
				if(session.getAttribute("basicRegistrationInfo") != null){
					UserModel user = (UserModel)session.getAttribute("basicRegistrationInfo");
					user.setQuestionOne(request.getParameter("question_one"));
					user.setQuestionTwo(request.getParameter("question_two"));
					user.setQuestionThree(request.getParameter("question_three"));
					user.setAnswerOne(request.getParameter("answer_one"));
					user.setAnswerTwo(request.getParameter("answer_two"));
					user.setAnswerThree(request.getParameter("answer_three"));
					
					UserDataAccess userDataAccess = new UserDataAccess();
					if(userDataAccess.insert(user)){
						session.setAttribute("registrationStatus", "0");
						session.removeAttribute("basicRegistrationInfo");
						return redirectToAction("Login", "Account", request, response);
					}
					request.setAttribute("err_db_exception", "<a align='center'><h4>Database exception occurs! Please try again.</h4></a>");
				}
				else{
					session.setAttribute("registrationStatus", "0");
					return view("/index.html", request, response);
				}
			}
			return view(request, response);
		}
		else{
			session.setAttribute("registrationStatus", "0");
			return view("/index.html", request, response);
		}
	}
	
	
	/* Validation Code Segment*/
	
	private Boolean isValidLength(String str, int length){
		try{
			if(str != null && !str.equals("")){
				char[] characters = str.toCharArray();
				if(characters.length > length){
					return false;
				}
				return true;
			}
			return false;
		}
		catch(PatternSyntaxException e){
			return false;
		}
		catch(Exception e){
			return false;
		}
	}
}
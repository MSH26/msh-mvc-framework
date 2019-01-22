package mvc.controller;

import mvc.systemfiles.systemclasses.models.Model;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;
import mvc.systemfiles.systemclasses.controllers.BaseController;
import mvc.controller.interfaces.IController;

import javax.servlet.http.*;
import java.util.regex.*;

import mvc.model.UserModel;
import repository.UserDataAccess;


public class PasswordRecoveryController extends BaseController implements IController{
	
	public ActionResult get(HttpServletRequest request,HttpServletResponse response, Model model){
		UserModel user = (UserModel)model;
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1*60*60);
		
		if(session.getAttribute("JSESSIONLOGSTATUS") != null){
			if(session.getAttribute("JSESSIONLOGSTATUS").equals("LOOGGED_IN")){
				return redirectToAction("Home", "Home", request, response);
			}
		}
		else{
			if(((String)session.getAttribute("FORGOTPASSWORDUSERNAME")) != null && ((String)session.getAttribute("FORGOTPASSWORDUSERNAME")).equals("")){
				return redirectToAction("Login", "Account", request, response);
			}
		}
		
		UserDataAccess userDataAccess = new UserDataAccess();
		if((String)session.getAttribute("FORGOTPASSWORDUSERNAME") != null){
			user = userDataAccess.getByUserName((String)session.getAttribute("FORGOTPASSWORDUSERNAME"));
			if(user != null){
				session.setAttribute("user", user);
				return view(request, response);
			}
			else{
				return view("/index.html", request, response);
			}
		}
		else{
			return view("/index.html", request, response);
		}
	}
	
	public ActionResult post(HttpServletRequest request,HttpServletResponse response, Model model){
		UserModel user = (UserModel)model;
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1*60*60);
		
		if(session.getAttribute("JSESSIONLOGSTATUS") != null){
			if(session.getAttribute("JSESSIONLOGSTATUS").equals("LOOGGED_IN")){
				return redirectToAction("Home", "Home", request, response);
			}
		}
		else{
			if(((String)session.getAttribute("FORGOTPASSWORDUSERNAME")) != null && ((String)session.getAttribute("FORGOTPASSWORDUSERNAME")).equals("")){
				return redirectToAction("Login", "Account", request, response);
			}
		}
		
		Boolean validityStatusFlag = true;
		Boolean passwordStatus = true;
		Boolean status = true; 		// For dispatcher null pointer exception safety
		
		if(!isPasswordValid(request.getParameter("current_password"))){
			validityStatusFlag = false;
			request.setAttribute("err_current_password", "Fill the current password field correctly");
		}
		if(!isPasswordValid(request.getParameter("password"))){
			validityStatusFlag = false;
			request.setAttribute("err_password", "Fill the password field correctly");
		}
		if(passwordStatus){
			if(isPasswordValid(request.getParameter("confirm_password"))){
				if(!request.getParameter("password").trim().equals(request.getParameter("confirm_password").trim())){
					validityStatusFlag = false;
					request.setAttribute("err_confirm_password", "Password mismatched");
				}
			} 
			else{
				validityStatusFlag = false;
				request.setAttribute("err_confirm_password", "Invalid password");
			}
		}

		if(request.getParameter("answer_one") != null && request.getParameter("answer_one").equals("")){
			validityStatusFlag = false;
			request.setAttribute("err_answer_one", "Fill the field");
		}

		if(request.getParameter("answer_two") != null && request.getParameter("answer_two").equals("")){
			validityStatusFlag = false;
			request.setAttribute("err_answer_two", "Fill the field");
		}

		if(request.getParameter("answer_three") != null && request.getParameter("answer_three").equals("")){
			validityStatusFlag = false;
			request.setAttribute("err_answer_three", "Fill the field");
		}
		if(validityStatusFlag == true){
			UserDataAccess userDataAccess = new UserDataAccess();
			user = userDataAccess.getByUserName((String)session.getAttribute("FORGOTPASSWORDUSERNAME"));
			if(user != null){
				if(user.getUserName().equals((String)session.getAttribute("FORGOTPASSWORDUSERNAME"))){
					if(user.getPassword().equals(request.getParameter("current_password"))){
						if(user.getAnswerOne().equals(request.getParameter("answer_one")) && user.getAnswerTwo().equals(request.getParameter("answer_two")) && user.getAnswerThree().equals(request.getParameter("answer_three"))){
							if(userDataAccess.update(user, (String)session.getAttribute("FORGOTPASSWORDUSERNAME"))){
								session.removeAttribute("FORGOTPASSWORDUSERNAME");
								return redirectToAction("Login", "Account", request, response);
							}
							status = false;
							request.setAttribute("err_operation", "Failled to change password!");
						}
						else{
							status = false;
							request.setAttribute("err_operation", "Anyone of the answer is  wrong!");
						}
					}
					else{
						status = false;
						request.setAttribute("err_operation", "Password don't matched");
					}
					
				}
				else{
					status = false;
					request.setAttribute("err_operation", "User name not exist");
				}
			}
			else{
				status = false;
				request.setAttribute("err_operation", "User name not exist");
			}
		}
		else{
			status = false;
		}
		// if(!status){
			// return view(request, response);
		// }
		
		return view(request, response);
	}
	
	
	
	private Boolean isPasswordValid(String password)
	{
		try{
			if(Pattern.matches("((.+)?)[A-Z]((.+)?)", password) && Pattern.matches("((.+)?)[a-z]((.+)?)", password) && Pattern.matches("((.+)?)[@|#|$|&]((.+)?)", password) && Pattern.matches("((.+)?)[0-9]((.+)?)", password)){
				if(password.length() >=8){
					return true;
				}
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
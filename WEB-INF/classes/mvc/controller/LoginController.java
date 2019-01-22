package mvc.controller;

import mvc.systemfiles.systemclasses.models.Model;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;
import mvc.systemfiles.systemclasses.controllers.BaseController;
import mvc.controller.interfaces.IController;

import javax.servlet.http.*;
import java.util.regex.*;

import mvc.model.UserModel;
import repository.UserDataAccess;

public class LoginController extends BaseController implements IController{
	
	private int loginFailledFlag;
	
	public void getloginFailledFlag(HttpSession session){
		if(session.getAttribute("loginFailledFlag") != null){
			String tmp = (session.getAttribute("loginFailledFlag")).toString();
			loginFailledFlag = Integer.parseInt(tmp);
		}
		else if(session.getAttribute("loginFailledFlag") == null){
			loginFailledFlag = 0;
			session.setAttribute("loginFailledFlag", loginFailledFlag);
		}
	}
	
	
	public ActionResult get(HttpServletRequest request,HttpServletResponse response, Model model){
		HttpSession session = request.getSession();
		getloginFailledFlag(session);
		session.setMaxInactiveInterval(1*60*60);
		
		if(session.getAttribute("JSESSIONLOGSTATUS") != null){
			if(session.getAttribute("JSESSIONLOGSTATUS").equals("LOOGGED_IN")){
				return redirectToAction("Home", "Home", request, response);
			}
		}
		
		return view(request, response);
	}
	
	public ActionResult post(HttpServletRequest request,HttpServletResponse response, Model model){
		UserModel user = (UserModel)model;
		HttpSession session = request.getSession();
		getloginFailledFlag(session);
		
		if(session.getAttribute("JSESSIONLOGSTATUS") != null){
			if(session.getAttribute("JSESSIONLOGSTATUS").equals("LOOGGED_IN")){
				return redirectToAction("Home", "Home", request, response);
			}
		}
		
		loginFailledFlag++;
		Boolean validityStatusFlag = true;
		Boolean Status = true;	//For Dispatcher's null pointer exception safety
		
		if(!isEmailValid(request.getParameter("user_name"))){
			validityStatusFlag = false;
			request.setAttribute("err_user_name", "Fill the username field correctly");
		}
		if(request.getParameter("password") == null){
			validityStatusFlag = false;
			request.setAttribute("err_password", "Fill the password field");
		}
		else{
			if(request.getParameter("password").trim().equals("")){
				validityStatusFlag = false;
				request.setAttribute("err_password", "Fill the password field");
			}
		}
		
		
		if(validityStatusFlag == true){
			if(request.getParameter("user_name") != null && request.getParameter("password") != null){
				UserDataAccess userDataAccess = new UserDataAccess();
				user = userDataAccess.getByUserName(request.getParameter("user_name"));
				
				if(user != null){
					if(user.getUserName().equals(request.getParameter("user_name"))){
						if(user.getPassword().equals(request.getParameter("password"))){
							session.setAttribute("JSESSIONLOGSTATUS", "LOOGGED_IN");
							session.setAttribute("JSESSIONUSERNAME", user.getUserName());
							Status = false;
							return redirectToAction("Home", "Home", request, response);
						}
						else{
							request.setAttribute("err_password", "Password missmatched!");
						}
					}
					else{
						request.setAttribute("err_account", "User name not exist");
					}
					
				}
				else{
					request.setAttribute("err_account", "User not exist");
				}
			}
			
		}
		if(loginFailledFlag >= 3){
			loginFailledFlag = 0;
			session.setAttribute("loginFailledFlag", loginFailledFlag);
			request.setAttribute("err_account", "You may not registered.Please <a href='registration' >Go Registration </a>");
		}
		// if(Status){
			// return view(request, response);
		// }
		return view(request, response);
	}
	
	
	
	/* Validation Code Segment */
	
	private Boolean isEmailValid(String email)
	{
		try{
			Pattern p = Pattern.compile("(.+)@(.+)(.+)\\.(.+)(.+)");
			Matcher m = p.matcher(email);
			if(m.matches()){
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
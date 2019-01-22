package mvc.controller;

import mvc.systemfiles.systemclasses.models.Model;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;
import mvc.systemfiles.systemclasses.controllers.BaseController;
import mvc.controller.interfaces.IController;

import javax.servlet.http.*;
import java.util.regex.*;

import mvc.model.UserModel;
import repository.UserDataAccess;


public class ForgotPasswordController extends BaseController implements IController{
	
	public ActionResult get(HttpServletRequest request,HttpServletResponse response, Model model){
		HttpSession session = request.getSession();
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
		session.setMaxInactiveInterval(1*60*60);
		
		if(session.getAttribute("JSESSIONLOGSTATUS") != null){
			if(session.getAttribute("JSESSIONLOGSTATUS").equals("LOOGGED_IN")){
				return redirectToAction("Home", "Home", request, response);
			}
		}
		
		if(request.getParameter("username") != null){
			UserDataAccess userDataAccess = new UserDataAccess();
			String userName = request.getParameter("username");
			user = userDataAccess.getByUserName(userName.trim());
			if(user != null){
				session.setAttribute("FORGOTPASSWORDUSERNAME", userName.trim());
				return redirectToAction("PasswordRecovery", request, response);
			}
			else{
				request.setAttribute("err_username", "Wrong user name");
				return view(request, response);
			}
		}
		else{
			request.setAttribute("err_username", "insert user name");
			return view(request, response);
		}
	}
	
}
package mvc.controller;

import mvc.model.UserModel;
import mvc.systemfiles.systemclasses.models.Model;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;
import mvc.systemfiles.systemclasses.controllers.BaseController;
import mvc.controller.interfaces.IController;

import javax.servlet.http.*;
import java.util.regex.*;
import java.time.Year; 


public class BasicRegistrationController extends BaseController implements IController{
	
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
				return redirectToAction("AdvancedRegistration", request, response);
			}
			else{
				return view(request, response);
			}
		}
		else{
			session.setAttribute("registrationStatus", "0");
			return view(request, response);
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
		
		if(!session.getAttribute("registrationStatus").equals("1")){
			Boolean validityStatusFlag = true;
			Boolean passwordStatus = true;
			
			if(!isNameValid(request.getParameter("first_name"))){
				validityStatusFlag = false;
				request.setAttribute("err_first_name", "Invalid first name or may be length smaller than 3");
			}
			if(!isNameValid(request.getParameter("last_name"))){
				validityStatusFlag = false;
				request.setAttribute("err_last_name", "Invalid last name or may be length smaller than 3");
			}
			if(!isDateValid(request.getParameter("date_of_birth"))){
				validityStatusFlag = false;
				request.setAttribute("err_date_of_birth", "Invalid date of birth");
			}
			if(!isPhoneValid(request.getParameter("phone"))){
				validityStatusFlag = false;
				request.setAttribute("err_phone", "Invalid phone number");
			}
			if(!isEmailValid(request.getParameter("email"))){
				validityStatusFlag = false;
				request.setAttribute("err_email", "Invalid email address");
			}
			if(!isPasswordValid(request.getParameter("password"))){
				validityStatusFlag = false;
				passwordStatus = false;
				request.setAttribute("err_password", "Invalid password");
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
			
			if(request.getParameter("gender") == null){
				validityStatusFlag = false;
				request.setAttribute("err_gender", "Please choose gender");
			}
			
			if(validityStatusFlag == true){
				UserModel user = (UserModel)model;
				user.setUserName(request.getParameter("email"));
				user.setFirstName(request.getParameter("first_name"));
				user.setLastName(request.getParameter("last_name"));
				user.setDateOfBirth(request.getParameter("date_of_birth"));
				user.setGender(request.getParameter("gender"));
				user.setEmail(request.getParameter("email"));
				user.setPhone(request.getParameter("phone"));
				user.setPassword(request.getParameter("password"));	
				session.setAttribute("basicRegistrationInfo", user);
				
				session.setAttribute("registrationStatus", "1");
				
				return redirectToAction("AdvancedRegistration", request, response);
			}
			else{
				return view(model, request, response);
			}
		}
		else{
			return redirectToAction("AdvancedRegistration", request, response);
		}
	}
	
	
	
	/* Validation Code Segment*/
	
	private Boolean isNameValid(String name)
	{
		try{
			if(Pattern.matches("([a-z]|[A-Z])([a-z]|[A-Z])([a-z]|[A-Z])((([a-z]|[A-Z])+)?)", name)){
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
	
	private Boolean isDateValid(String formDate)
	{
		try{
			/** regular expression for date validation except** leap year
			((0?(1|3|5|7|8))|(1(0|2)))/(((0?(\\d[^0]))|([1-2]\\d))|(3[0-1]))/\\d\\d\\d\\d  // for 31 days			
			((0?(2|4|6|9))|(11))/(((0?(\\d[^0]))|([1-2]\\d))|(30))/\\d\\d\\d\\d  // for 28/29/30 days
			((((0?(1|3|5|7|8))|(1(0|2)))/(((0?(\\d[^0]))|([1-2]\\d))|(3[0-1])))|(((0?(2|4|6|9))|(11))/(((0?(\\d[^0]))|([1-2]\\d))|(30))))/\\d\\d\\d\\d  // for any days
			*/
			Pattern p = Pattern.compile("((((0?(1|3|5|7|8))|(1(0|2)))/(((0?(\\d[^0]))|([1-2]\\d))|(3[0-1])))|(((0?(2|4|6|9))|(11))/(((0?(\\d[^0]))|([1-2]\\d))|(30))))/\\d\\d\\d\\d"); // date format "mm/dd/yyyy"
			Matcher m = p.matcher(formDate);
			if(m.matches()){
				String[] formDateParts = formDate.split("/");
				
				if ((Year.of(Integer.parseInt(formDateParts[2]) + 18)).isAfter(Year.now()) != true) //Integer.parseInt(dateParts[2]))
                {
					if( Integer.parseInt(formDateParts[0]) == 2)
                    {
						if((Year.of(Integer.parseInt(formDateParts[2]))).isLeap()){
							if(Integer.parseInt(formDateParts[1]) <= 29){
								return true;
							}
							else{
								return false;
							}
						}
						else{
							if(Integer.parseInt(formDateParts[1]) <= 28){
								return true;
							}
							else{
								return false;
							}
						}
					}
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
		/** Another way
		int atpos = email.indexOf("@");
		int dotpos = email.lastIndexOf(".");
		if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length()) {
			return false;
		}
		return true; */
	}
	
	private Boolean isPhoneValid(String phone)
	{
		try{
			Pattern p = Pattern.compile("[\\d]+");
			Matcher m = p.matcher(phone);
			if(m.matches()){
				if(phone.length() == 11){
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
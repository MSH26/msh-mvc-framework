package mvc.controller;

import mvc.systemfiles.systemclasses.models.Model;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;
import mvc.systemfiles.systemclasses.controllers.BaseController;
import mvc.controller.interfaces.IController;

import javax.servlet.http.*;


public class HomeController extends BaseController implements IController{
	
	public ActionResult get(HttpServletRequest request,HttpServletResponse response, Model model){
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1*60*60);
		
		if(session.getAttribute("JSESSIONLOGSTATUS") != null){
			if(!session.getAttribute("JSESSIONLOGSTATUS").equals("LOOGGED_IN")){
				return view("/index.html", request, response);
			}
		}
		else{
			return view("/index.html", request, response);
		}
		
		return view(request, response);
	}
	
	public ActionResult post(HttpServletRequest request,HttpServletResponse response, Model model){
		return get(request, response, model);
	}
	
}
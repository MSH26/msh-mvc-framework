package mvc.controller;

import mvc.systemfiles.systemclasses.models.Model;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;
import mvc.systemfiles.systemclasses.controllers.BaseController;
import mvc.controller.interfaces.IController;

import javax.servlet.http.*;


public class LogoutController extends BaseController implements IController{
	
	public ActionResult get(HttpServletRequest request,HttpServletResponse response, Model model){
		HttpSession session = request.getSession();
		
		session.setAttribute("JSESSIONLOGSTATUS", "LOOGGED_OUT");
		if(session.getAttribute("JSESSIONUSERNAME") != null){
			session.removeAttribute("JSESSIONUSERNAME");
		}
		session.setMaxInactiveInterval(0);
		return redirectToAction("Home", "Home", request, response);
	}
	
	public ActionResult post(HttpServletRequest request,HttpServletResponse response, Model model){
		return null;
	}
	
}
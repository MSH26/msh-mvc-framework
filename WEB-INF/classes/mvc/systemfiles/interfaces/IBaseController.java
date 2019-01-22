package mvc.systemfiles.interfaces;

import mvc.systemfiles.systemclasses.models.Model;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;

import javax.servlet.http.*;

public interface IBaseController{

	void setModel(Model model);
	Model getModel();
	
	ActionResult view(HttpServletRequest request, HttpServletResponse response);
	ActionResult view(Model model, HttpServletRequest request, HttpServletResponse response);
	ActionResult view(String url, HttpServletRequest request, HttpServletResponse response);
	ActionResult redirectToAction(String redirectControllerURL, HttpServletRequest request, HttpServletResponse response);
	ActionResult redirectToAction(String redirectControllerURL, String moduleName, HttpServletRequest request, HttpServletResponse response);
	ActionResult dispatchToAction(String dispatchControllerURL, HttpServletRequest request, HttpServletResponse response);
	ActionResult dispatchToAction(String dispatchControllerURL, String moduleName, HttpServletRequest request, HttpServletResponse response);
}
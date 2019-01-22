package mvc.systemfiles.systemclasses.controllers;

import mvc.systemfiles.interfaces.IBaseController;
import mvc.systemfiles.systemclasses.models.Model;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;


import javax.servlet.http.*;

public abstract class BaseController implements IBaseController{

	private Model model = null;
	
	
	public void setModel(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return model;
	}
	
	public ActionResult view(HttpServletRequest request, HttpServletResponse response){
		ActionResult actionResult = new ActionResult();
		actionResult.setChosenMethod("view");
		actionResult.setRequest(request);
		actionResult.setResponse(response);
		return actionResult;
	}
	
	public ActionResult view(Model model, HttpServletRequest request, HttpServletResponse response){
		ActionResult actionResult = new ActionResult();
		actionResult.setChosenMethod("view");
		actionResult.setModel(model);
		actionResult.setRequest(request);
		actionResult.setResponse(response);
		return actionResult;
	}
	
	public ActionResult view(String url, HttpServletRequest request, HttpServletResponse response){
		ActionResult actionResult = new ActionResult();
		actionResult.setChosenMethod("view");
		actionResult.setUrl(url.toLowerCase());
		actionResult.setRequest(request);
		actionResult.setResponse(response);
		return actionResult;
	}
	
	public ActionResult redirectToAction(String redirectControllerURL, HttpServletRequest request, HttpServletResponse response){
		ActionResult actionResult = new ActionResult();
		actionResult.setChosenMethod("redirectToAction");
		actionResult.setUrl(redirectControllerURL.toLowerCase());
		actionResult.setRequest(request);
		actionResult.setResponse(response);
		return actionResult;
	}
	
	public ActionResult redirectToAction(String redirectControllerURL, String moduleName, HttpServletRequest request, HttpServletResponse response){
		ActionResult actionResult = new ActionResult();
		actionResult.setChosenMethod("redirectToAction");
		actionResult.setUrl(redirectControllerURL.toLowerCase());
		actionResult.setModuleName(moduleName.toLowerCase());
		actionResult.setRequest(request);
		actionResult.setResponse(response);
		return actionResult;
	}
	
	public ActionResult dispatchToAction(String dispatchControllerURL, HttpServletRequest request, HttpServletResponse response){
		ActionResult actionResult = new ActionResult();
		actionResult.setChosenMethod("dispatchToAction");
		actionResult.setUrl(dispatchControllerURL);
		actionResult.setRequest(request);
		actionResult.setResponse(response);
		return actionResult;
	}
	
	public ActionResult dispatchToAction(String dispatchControllerURL, String moduleName, HttpServletRequest request, HttpServletResponse response){
		ActionResult actionResult = new ActionResult();
		actionResult.setChosenMethod("dispatchToAction");
		actionResult.setUrl(dispatchControllerURL);
		actionResult.setModuleName(moduleName);
		actionResult.setRequest(request);
		actionResult.setResponse(response);
		return actionResult;
	}
	
}
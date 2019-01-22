package mvc.systemfiles.interfaces;

import mvc.systemfiles.systemclasses.models.Model;

import javax.servlet.http.*;

public interface IActionResult{
	
	void setRequest(HttpServletRequest request);
	
	HttpServletRequest getRequest();
	
	void setResponse(HttpServletResponse response);
	
	HttpServletResponse getResponse();
	
	void setModel(Model model);
	
	Model getModel();
	
	void setChosenMethod(String chosenMethod);
	
	String getChosenMethod();
	
	void setModuleName(String moduleName);
	
	String getModuleName();
	
	void setUrl(String url);
	
	String GetUrl();
}
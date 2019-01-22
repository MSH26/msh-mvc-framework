package mvc.systemfiles.systemclasses.utilityclasses;

import mvc.systemfiles.interfaces.IActionResult;
import mvc.systemfiles.systemclasses.models.Model;

import javax.servlet.http.*;

public class ActionResult implements IActionResult{
	
	private Model model = null;
	private String chosenMethod = "";
	private String moduleName = "";
	private String url = "";
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	
	public void setRequest(HttpServletRequest request){
		this.request = request;
	}
	
	public HttpServletRequest getRequest(){
		return this.request;
	}
	
	public void setResponse(HttpServletResponse response){
		this.response = response;
	}
	
	public HttpServletResponse getResponse(){
		return this.response;
	}
	
	public void setModel(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return this.model;
	}
	
	public void setChosenMethod(String chosenMethod){
		this.chosenMethod = chosenMethod;
	}
	
	public String getChosenMethod(){
		return this.chosenMethod;
	}
	
	public void setModuleName(String moduleName){
		this.moduleName = moduleName;
	}
	
	public String getModuleName(){
		return this.moduleName;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String GetUrl(){
		return this.url;
	}
}
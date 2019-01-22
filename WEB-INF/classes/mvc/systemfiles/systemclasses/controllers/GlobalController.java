package mvc.systemfiles.systemclasses.controllers;

import mvc.controller.interfaces.IController;
import mvc.systemfiles.systemclasses.models.Model;
import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GlobalController extends HttpServlet{

	private String moduleName = "";
	private String controllerName = "";
	private String controllerAbsoluteName = "";
	private String modelName = "";
	private String modelAbsoluteName = "";
	private String viewUrl = "";
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		if(isUrlValid((String)request.getPathInfo())){
			IController controllerObject = createControllerInstance();
			Model model = createModelInstance();
			ActionResult actionResult = controllerObject.get(request, response, model);
			performOperationBasedOnActionResult(actionResult);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		if(isUrlValid((String)request.getPathInfo())){
			IController controllerObject = createControllerInstance();
			Model model = createModelInstance();
			ActionResult actionResult = controllerObject.post(request, response, model);
			performOperationBasedOnActionResult(actionResult);
		}
	}
	
	private void performOperationBasedOnActionResult(ActionResult actionResult)
	throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpServletRequest request = actionResult.getRequest();
		HttpServletResponse response = actionResult.getResponse();
		
		if(actionResult.getChosenMethod().equals("view")){
			if(actionResult.getModel() != null){
				request.setAttribute("model", actionResult.getModel());
			}
			if(!actionResult.GetUrl().equals("")){
				dispatcher = request.getRequestDispatcher(actionResult.GetUrl());
			}
			else{
				dispatcher = request.getRequestDispatcher(viewUrl);
			}
			dispatcher.forward(request, response);
		}
		else if(actionResult.getChosenMethod().equals("redirectToAction")){
			if(actionResult.getModuleName() != null && !actionResult.getModuleName().equals("")){
				response.sendRedirect("../" + actionResult.getModuleName() + "/" + actionResult.GetUrl());
			}
			else{
				response.sendRedirect("../" + moduleName + "/" + actionResult.GetUrl());
			}
		}
		else if(actionResult.getChosenMethod().equals("dispatchToAction")){
			if(actionResult.getModuleName() != null && !actionResult.getModuleName().equals("")){
				dispatcher = request.getRequestDispatcher(actionResult.getModuleName() + "/" + actionResult.GetUrl());
			}
			else{
				dispatcher = request.getRequestDispatcher(moduleName + "/" + actionResult.GetUrl());
			}
			dispatcher.forward(request, response);
		}
	}
	
	private IController createControllerInstance(){
		try{
			return (IController)Class.forName(controllerAbsoluteName).getConstructor().newInstance();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private Model createModelInstance(){
		try{
			return (Model)Class.forName(modelAbsoluteName).getConstructor().newInstance();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	*	Checking the validity of the requested URL
	*/
	private boolean isUrlValid(String url){
		boolean urlValidityStatusFlag = false;
		
		if(url == null || url.trim().equals("/") || url.trim().equals("")){
			return false;	// when the url is like "msh-framework/mvc" or "msh-framework/mvc/"
		}
		String[] requestedUrl  = url.split("/");
		//System.out.println(requestedUrl[1]);
		// for(String requestedUrlParts:requestedUrl){
			// System.out.println(requestedUrlParts);
		// }
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(getClass().getResourceAsStream("../../../mvc.xml"));
			doc.getDocumentElement().normalize();

			NodeList mvcList = doc.getElementsByTagName("mvc");

			for(int i = 0; i<mvcList.getLength(); i++){
				Node mvc = mvcList.item(i);
				
				if (mvc.getNodeType() == Node.ELEMENT_NODE) {
					Element eMvc = (Element)mvc;
					NodeList mvcNodeList = eMvc.getChildNodes();
					
					Node mvcNode = mvcNodeList.item(0);		//	get the first node 
					while (!(mvcNode instanceof Element) && mvcNode != null) {		//	Used to tackle the white space problem
						mvcNode = mvcNode.getNextSibling();
					}
					if(mvcNode.getNodeType() == Node.ELEMENT_NODE){
						Element eMvcNode = (Element)mvcNode;
						if(eMvcNode.getTagName().equals("module-name")){
							moduleName = eMvcNode.getTextContent().trim();		// Storing the module name
							if(moduleName.toLowerCase().equals(requestedUrl[1].trim())){	//Matching the module name
								mvcNode = mvcNode.getNextSibling();
								while (!(mvcNode instanceof Element) && mvcNode != null) {		//	Used to tackle the white space problem
									mvcNode = mvcNode.getNextSibling();
								}
								if(mvcNode.getNodeType() == Node.ELEMENT_NODE){
									eMvcNode = (Element)mvcNode;
									if(eMvcNode.getTagName().equals("controller-name")){
										controllerName = eMvcNode.getTextContent().trim();		// Storing the controller name
										if(controllerName.toLowerCase().equals(requestedUrl[2].trim())){	//Matching the controller name	
											urlValidityStatusFlag = true;
											mvcNode = mvcNode.getNextSibling();
											while (!(mvcNode instanceof Element) && mvcNode != null) {		//	Used to tackle the white space problem
												mvcNode = mvcNode.getNextSibling();
											}
											if(mvcNode.getNodeType() == Node.ELEMENT_NODE){
												eMvcNode = (Element)mvcNode;
												controllerAbsoluteName = eMvcNode.getTextContent().trim();
											}
											mvcNode = mvcNode.getNextSibling();
											while (!(mvcNode instanceof Element) && mvcNode != null) {		//	Used to tackle the white space problem
												mvcNode = mvcNode.getNextSibling();
											}
											if(mvcNode.getNodeType() == Node.ELEMENT_NODE){
												eMvcNode = (Element)mvcNode;
												modelName = eMvcNode.getTextContent().trim();
											}
											mvcNode = mvcNode.getNextSibling();
											while (!(mvcNode instanceof Element) && mvcNode != null) {		//	Used to tackle the white space problem
												mvcNode = mvcNode.getNextSibling();
											}
											if(mvcNode.getNodeType() == Node.ELEMENT_NODE){
												eMvcNode = (Element)mvcNode;
												modelAbsoluteName = eMvcNode.getTextContent().trim();
											}
											mvcNode = mvcNode.getNextSibling();
											while (!(mvcNode instanceof Element) && mvcNode != null) {		//	Used to tackle the white space problem
												mvcNode = mvcNode.getNextSibling();
											}
											if(mvcNode.getNodeType() == Node.ELEMENT_NODE){
												eMvcNode = (Element)mvcNode;
												viewUrl = eMvcNode.getTextContent().trim();
											}
											if(controllerAbsoluteName.trim().equals("") || modelName.trim().equals("") || viewUrl.trim().equals("")){
												urlValidityStatusFlag = false;
											}
										}
									}
								}
							}
						}
					}
				}
				if(urlValidityStatusFlag){
					return true;
				}
			}
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
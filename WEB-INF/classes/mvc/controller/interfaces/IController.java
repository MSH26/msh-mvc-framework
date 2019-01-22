package mvc.controller.interfaces;

import mvc.systemfiles.systemclasses.utilityclasses.ActionResult;
import mvc.systemfiles.systemclasses.models.Model;

import javax.servlet.http.*;

public interface IController{

	ActionResult get(HttpServletRequest request,HttpServletResponse response, Model model);
	ActionResult post(HttpServletRequest request,HttpServletResponse response, Model model);
}
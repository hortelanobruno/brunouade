package struts.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.model.BusinessDelegate;

public class CargarDatosAction extends Action {

	BusinessDelegate businessDelegate;

	public CargarDatosAction() {
		businessDelegate = new BusinessDelegate();
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		//Hay que cambiar todo

		try {
				return (mapping.findForward("success"));
		} catch (Exception e) {
			return (mapping.findForward("failure"));
		}
	}

}

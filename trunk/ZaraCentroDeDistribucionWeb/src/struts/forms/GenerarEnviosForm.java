package struts.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;


public class GenerarEnviosForm extends ActionForm {

	private static final long serialVersionUID = -940262830497003845L;

	private List codigosSolDist;

	public void setCodigosSolDist(List codigosSolDist) {
		this.codigosSolDist = codigosSolDist;
	}

	public List getCodigosSolDist() {
		return codigosSolDist;
	}

}

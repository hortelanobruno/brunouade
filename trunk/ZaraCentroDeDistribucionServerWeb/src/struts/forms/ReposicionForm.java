package struts.forms;

import org.apache.struts.action.ActionForm;

public class ReposicionForm extends ActionForm {

	private String[] _codigo;
	private String[] _cantidad;
	
	public String[] getCantidad() {
		return _cantidad;
	}
	public void setCantidad(String[] _cantidad) {
		this._cantidad = _cantidad;
	}
	public String[] getCodigo() {
		return _codigo;
	}
	public void setCodigo(String[] _codigo) {
		this._codigo = _codigo;
	}
	
	
	
}

package struts.forms;

import org.apache.struts.action.ActionForm;


public class GenerarSolFabForm extends ActionForm {

	private Integer[] _codigo;
	
	private Integer[] _cantidad;

	public Integer[] getCantidad() {
		return _cantidad;
	}

	public void setCantidad(Integer[] _cantidad) {
		this._cantidad = _cantidad;
	}

	public Integer[] getCodigo() {
		return _codigo;
	}

	public void setCodigo(Integer[] _codigo) {
		this._codigo = _codigo;
	}
	
	
}

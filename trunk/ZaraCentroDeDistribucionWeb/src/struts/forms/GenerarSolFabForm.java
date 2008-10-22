package struts.forms;

import org.apache.struts.action.ActionForm;


public class GenerarSolFabForm extends ActionForm {

	private Long[] _codigo;
	
	private Integer[] _cantidadAFab;
	private Integer[] _cantidadRec;
	private Integer[] _cantidadPed;

	public Integer[] getCantidad() {
		return _cantidadAFab;
	}

	public void setCantidadAFab(Integer[] _cantidad) {
		this._cantidadAFab = _cantidad;
	}

	public Long[] getCodigo() {
		return _codigo;
	}

	public void setCodigo(Long[] _codigo) {
		this._codigo = _codigo;
	}

	public Integer[] getCantidadPed() {
		return _cantidadPed;
	}

	public void setCantidadPed(Integer[] ped) {
		_cantidadPed = ped;
	}

	public Integer[] getCantidadRec() {
		return _cantidadRec;
	}

	public void setCantidadRec(Integer[] rec) {
		_cantidadRec = rec;
	}
	
	
}

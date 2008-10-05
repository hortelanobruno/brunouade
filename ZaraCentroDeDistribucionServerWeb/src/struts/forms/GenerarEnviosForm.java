package struts.forms;

import org.apache.struts.action.ActionForm;

public class GenerarEnviosForm extends ActionForm {

	private static final long serialVersionUID = -940262830497003845L;

	/** campo codigo solicitudDistribucion */
	private String _codigoSolicitudDistribucion = null;
	
	/** vector codigo articulo */
	private String[] _codigoArticulo = null;
	
	/** vector cantEnviar */
	private String[] _cantEnviar = null;

	public String[] getCantEnviar() {
		return _cantEnviar;
	}

	public void setCantEnviar(String[] enviar) {
		_cantEnviar = enviar;
	}

	public String[] getCodigoArticulo() {
		return _codigoArticulo;
	}

	public void setCodigoArticulo(String[] articulo) {
		_codigoArticulo = articulo;
	}

	public String getCodigoSolicitudDistribucion() {
		return _codigoSolicitudDistribucion;
	}

	public void setCodigoSolicitudDistribucion(String solicitudDistribucion) {
		_codigoSolicitudDistribucion = solicitudDistribucion;
	}
	
	
}

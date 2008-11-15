package struts.forms;

import org.apache.struts.action.ActionForm;

public class GenerarSolFabForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8850938378261278671L;
	private String[] codigo;
	private String[] descripcion;
	private String[] stock;
	private String[] cantPedida;
	private String[] cantMinAPedir;
	private String[] cantAFab;
	
	public void setCantAFab(String[] cantAFab) {
		this.cantAFab = cantAFab;
	}
	public String[] getCantAFab() {
		return cantAFab;
	}
	public void setCantMinAPedir(String[] cantMinAPedir) {
		this.cantMinAPedir = cantMinAPedir;
	}
	public String[] getCantMinAPedir() {
		return cantMinAPedir;
	}
	public void setCantPedida(String[] cantPedida) {
		this.cantPedida = cantPedida;
	}
	public String[] getCantPedida() {
		return cantPedida;
	}
	public void setStock(String[] stock) {
		this.stock = stock;
	}
	public String[] getStock() {
		return stock;
	}
	public void setDescripcion(String[] descripcion) {
		this.descripcion = descripcion;
	}
	public String[] getDescripcion() {
		return descripcion;
	}
	public void setCodigo(String[] codigo) {
		this.codigo = codigo;
	}
	public String[] getCodigo() {
		return codigo;
	}

}

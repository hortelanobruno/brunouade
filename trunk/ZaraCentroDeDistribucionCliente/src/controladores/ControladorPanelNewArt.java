package controladores;

import BusinessLogic.BusinessDelegate;
import Exceptions.ExistingProductException;
import MVCFramework.Controlador;
import MVCFramework.ProxyModelo;
import MVCFramework.Vista;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import Vistas.VistaNewArt;

public class ControladorPanelNewArt extends Controlador {

	public ControladorPanelNewArt(ProxyModelo mod, Vista vis) {
		super(mod, vis);
		// TODO Auto-generated constructor stub
	}
	
	public void doCargarXML(boolean b) {
		((VistaNewArt)vista).cargarTabla(b);
		vista.actualizar();
	}
	
	/*public void doGuardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException{
		((BusinessDelegate)(this.getModelo())).guardarArticuloHogar(a);
	}
	
	public void doGuardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException{
		((BusinessDelegate)(this.getModelo())).guardarArticuloRopa(a);
	}

	public boolean doExisteArticulo(long codigo) {
		return ((BusinessDelegate)(this.getModelo())).existeArticulo(codigo);
	}
*/
}

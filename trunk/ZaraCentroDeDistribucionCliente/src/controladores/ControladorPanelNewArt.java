package controladores;

import BusinessLogic.BusinessDelegate;
import RemoteMVCFramework.Controlador;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;
import VO.ArticuloHogarVO;
import VO.ArticuloRopaVO;
import Vistas.VistaNewArt;
import Vistas.VistaSolDis;

public class ControladorPanelNewArt extends Controlador {

	public ControladorPanelNewArt(ProxyModelo mod, Vista vis) {
		super(mod, vis);
		// TODO Auto-generated constructor stub
	}
	
	public void doCargarXML(boolean b) {
		((VistaNewArt)vista).cargarTabla(b);
		vista.actualizar();
	}
	
	public void doGuardarArticuloHogar(ArticuloHogarVO a){
		((BusinessDelegate)(this.getModelo())).guardarAritucloHogar(a);
	}
	
	public void doGuardarArticuloRopa(ArticuloRopaVO a){
		((BusinessDelegate)(this.getModelo())).guardarAritucloRopa(a);
	}

}

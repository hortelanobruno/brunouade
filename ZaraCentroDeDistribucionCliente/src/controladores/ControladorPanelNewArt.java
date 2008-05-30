package controladores;

import RemoteMVCFramework.Controlador;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;
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

}

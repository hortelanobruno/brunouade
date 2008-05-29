package controladores;

import RemoteMVCFramework.Controlador;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;
import Vistas.VistaRepArt;

public class ControladorPanelRepArt extends Controlador{

	public ControladorPanelRepArt(ProxyModelo mod, Vista vis) {
		super(mod, vis);
		// TODO Auto-generated constructor stub
	}

	public void doCargarXML(boolean b) {
		((VistaRepArt)vista).cargarTabla(b);
		vista.actualizar();
		
	}

}

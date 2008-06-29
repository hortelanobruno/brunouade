package controladores;

import MVCFramework.Controlador;
import MVCFramework.ProxyModelo;
import MVCFramework.Vista;
import Vistas.VistaEnvios;

public class ControladorPanelEnvios extends Controlador {

	public ControladorPanelEnvios(ProxyModelo mod, Vista vis) {
		super(mod, vis);
		// TODO Auto-generated constructor stub
	}



	public void doCargarSolicitud(boolean b,boolean c) {
		((VistaEnvios)vista).cargarTabla(b);
		((VistaEnvios)vista).cargarTree(c);
		vista.actualizar();
	}



	public void doTiendaSeleccionada(boolean b) {
		((VistaEnvios)vista).cargarTree(b);
		vista.actualizar();
	}

}

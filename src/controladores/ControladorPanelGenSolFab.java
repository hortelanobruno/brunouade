package controladores;

import MVCFramework.Controlador;
import MVCFramework.ProxyModelo;
import MVCFramework.Vista;
import Vistas.VistaGenSolFab;

public class ControladorPanelGenSolFab extends Controlador {

	public ControladorPanelGenSolFab(ProxyModelo mod, Vista vis) {
		super(mod, vis);
		// TODO Auto-generated constructor stub
	}

	public void doCargarArticulosPendientes(boolean b) {
		((VistaGenSolFab)vista).cargarTabla(b);
		vista.actualizar();
	}

}

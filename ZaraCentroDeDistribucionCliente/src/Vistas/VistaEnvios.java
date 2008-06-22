package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import MVCFramework.Vista;

public class VistaEnvios extends Vista {

	private MenuPrincipal ref;
	
	public VistaEnvios(BusinessDelegate mod) {
		super(mod);
	}

	public void actualizar() {
		ref.getPanelEnvios().update();
	}

	public MenuPrincipal getRef() {
		return ref;
	}

	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

	public void cargarTabla(boolean b) {
		ref.getPanelEnvios().setCargarTable(b);
	}

	public void cargarTree(boolean b) {
		ref.getPanelEnvios().setCargarTree(b);
	}

}

package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaRepArt extends Vista {

	private MenuPrincipal ref;
	
	public VistaRepArt(BusinessDelegate mod) {
		super(mod);
	}


	public void actualizar() {
		ref.getPanelRepArt().update();
	}


	public MenuPrincipal getRef() {
		return ref;
	}


	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}


	public void cargarTabla(boolean b) {
		ref.getPanelRepArt().setCargarTable(b);
	}

}

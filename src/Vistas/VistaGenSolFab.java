package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import MVCFramework.Vista;

public class VistaGenSolFab extends Vista {

	private MenuPrincipal ref;
	
	public VistaGenSolFab(BusinessDelegate mod) {
		super(mod);
	}

	public void actualizar() {
		ref.getPanelGSF().update();
	}

	public MenuPrincipal getRef() {
		return ref;
	}

	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

	public void cargarTabla(boolean b) {
		ref.getPanelGSF().setCargarTabla(b);
	}

}

package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import MVCFramework.Vista;

public class VistaNewArt extends Vista {

	private MenuPrincipal ref;
	
	public VistaNewArt(BusinessDelegate mod) {
		super(mod);
	}


	public void cargarTabla(boolean flag){
		ref.getPanelNewArt().setCargarTable(flag);
	}
	
	

	public void actualizar() {
		ref.getPanelNewArt().update();
	}

	public MenuPrincipal getRef() {
		return ref;
	}


	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

}

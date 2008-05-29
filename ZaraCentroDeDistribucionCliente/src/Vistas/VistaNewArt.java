package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaNewArt extends Vista {

	private MenuPrincipal ref;
	
	public VistaNewArt(BusinessDelegate mod) {
		super(mod);
	}


	public void actualizar() {
		
	}


	public MenuPrincipal getRef() {
		return ref;
	}


	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

}

package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaNewArt extends Vista {

	private MenuPrincipal ref;
	
	public VistaNewArt(BusinessDelegate mod, MenuPrincipal mn) {
		super(mod);
		this.ref = mn;
	}


	public void actualizar() {
		
	}

}

package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaGenSolFab extends Vista {

	private MenuPrincipal ref;
	
	public VistaGenSolFab(BusinessDelegate mod, MenuPrincipal mn) {
		super(mod);
		this.ref = mn;
	}

	public void actualizar() {

		
	}

}

package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaEnvios extends Vista {

	private MenuPrincipal ref;
	
	public VistaEnvios(BusinessDelegate mod, MenuPrincipal mn) {
		super(mod);
		this.ref = mn;
		
	}

	public void actualizar() {
		
		
	}

}

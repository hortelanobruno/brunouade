package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaEnvios extends Vista {

	private MenuPrincipal ref;
	
	public VistaEnvios(BusinessDelegate mod) {
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

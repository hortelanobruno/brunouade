package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import MVCFramework.Vista;

public class VistaConfig extends Vista {

	private MenuPrincipal ref;
	
	public VistaConfig(BusinessDelegate mod) {
		super(mod);
	}


	public void actualizar() {
		// TODO Auto-generated method stub
		
	}


	public MenuPrincipal getRef() {
		return ref;
	}


	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}

}

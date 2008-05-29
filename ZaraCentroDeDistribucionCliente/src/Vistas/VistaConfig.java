package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaConfig extends Vista {

	private MenuPrincipal ref;
	
	public VistaConfig(BusinessDelegate mod, MenuPrincipal mn) {
		super(mod);
		this.ref = mn;
	}


	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

}

package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaSolDis extends Vista{

	private MenuPrincipal ref;
	
	public VistaSolDis(BusinessDelegate businessDelegate, MenuPrincipal mn) {
		super(businessDelegate);
		this.ref = mn;
		
	}

	public void cargarTabla(boolean flag){
		ref.getPanelSD().setCargarTable(flag);
	}

	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
}

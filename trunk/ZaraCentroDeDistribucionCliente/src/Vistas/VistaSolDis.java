package Vistas;

import BusinessLogic.BusinessDelegate;
import GUI.MenuPrincipal;
import RemoteMVCFramework.Vista;

public class VistaSolDis extends Vista{

	private MenuPrincipal ref;
	
	public VistaSolDis(BusinessDelegate businessDelegate) {
		super(businessDelegate);
	}

	public void cargarTabla(boolean flag){
		ref.getPanelSD().setCargarTable(flag);
	}

	public void actualizar() {
		ref.getPanelSD().update();
	}

	public MenuPrincipal getRef() {
		return ref;
	}

	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}
}

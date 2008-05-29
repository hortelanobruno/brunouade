package Paneles;

import GUI.MenuPrincipal;

public class PanelDB extends javax.swing.JPanel{


	private static final long serialVersionUID = 1L;
	private MenuPrincipal ref;
	
	public PanelDB(MenuPrincipal m) {
		this.ref = m;
	}
	public void update(){
		
	}
	public MenuPrincipal getRef() {
		return ref;
	}
	public void setRef(MenuPrincipal ref) {
		this.ref = ref;
	}
}

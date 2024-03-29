package controladores;

import MVCFramework.Controlador;
import MVCFramework.ProxyModelo;
import MVCFramework.Vista;
import Vistas.VistaRepArt;

public class ControladorPanelRepArt extends Controlador{

	public ControladorPanelRepArt(ProxyModelo mod, Vista vis) {
		super(mod, vis);
		// TODO Auto-generated constructor stub
	}

	public void doCargarXML(boolean b) {
		((VistaRepArt)vista).cargarTabla(b);
		vista.actualizar();
	}

	/*public void doActualizarSolicitudFabricacion(SolicitudFabricaVO solFab) {
		((BusinessDelegate)(this.getModelo())).actualizarSolicitudFabricacion(solFab);
	}

	public void doGuardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO) {
		((BusinessDelegate)(this.getModelo())).guardarSolicitudReposicion(solRepVO);
	}

	public void doCargarStocks(ArrayList<ArticuloAReponerVO> articulo) {
		((BusinessDelegate)(this.getModelo())).actualizarStock(articulo);
	}

	/*public boolean doExisteSolicitudDeReposicion(int numero) {
		return ((BusinessDelegate)(this.getModelo())).existeSolRep(numero);
	}*/

}

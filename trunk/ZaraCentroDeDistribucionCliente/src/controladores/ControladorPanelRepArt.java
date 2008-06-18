package controladores;

import java.util.ArrayList;

import BusinessLogic.BusinessDelegate;
import MVCFramework.Controlador;
import MVCFramework.ProxyModelo;
import MVCFramework.Vista;
import VO.ArticuloAReponerVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudFabricaVO;
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

	public void doGuardarSolicitudFabricacion(SolicitudFabricaVO solFab) {
		((BusinessDelegate)(this.getModelo())).guardarSolicitudFabricacion(solFab);
	}

	public void doGuardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO) {
		((BusinessDelegate)(this.getModelo())).guardarSolicitudReposicion(solRepVO);
	}

	public void doCargarStocks(ArrayList<ArticuloAReponerVO> articulo) {
		((BusinessDelegate)(this.getModelo())).actualizarStock(articulo);
	}

	public boolean doExisteSolicitudDeReposicion(int numero) {
		return ((BusinessDelegate)(this.getModelo())).existeSolRep(numero);
	}

}

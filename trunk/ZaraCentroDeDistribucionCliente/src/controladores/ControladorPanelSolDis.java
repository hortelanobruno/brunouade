package controladores;

import java.util.Collection;

import BusinessLogic.BusinessDelegate;
import MVCFramework.Controlador;
import MVCFramework.ProxyModelo;
import MVCFramework.Vista;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloHeaderVO;
import VO.FabricaVO;
import VO.SolicitudDistribucionVO;
import Vistas.VistaSolDis;

public class ControladorPanelSolDis extends Controlador
{
    public ControladorPanelSolDis(ProxyModelo mod, Vista vis) 
    {
        super(mod, vis);
    }
    
    public int doGetTestNumber()
    {
    	return ((BusinessDelegate)this.getModelo()).getTestNumber();
    }

    public void doGuardarSolicitud(SolicitudDistribucionVO soldis)
    {
        ((BusinessDelegate)(this.getModelo())).guardarSolicitud(soldis);
    }

	public void doCargarXML(boolean b) {
		((VistaSolDis)vista).cargarTabla(b);
		vista.actualizar();
	}

	public Integer doGetNumeroSolEnv() {
		return ((BusinessDelegate)(this.getModelo())).getNumeroSolEnv();
	}

	public ArticuloHeaderVO doGetArticulo(long cod) {
		return ((BusinessDelegate)(this.getModelo())).getArticulo(cod);
	}

	public int doGetNumeroSolFab() {
		return ((BusinessDelegate)(this.getModelo())).getNumeroSolFab();
	}

	public FabricaVO doGetFabrica() {
		return ((BusinessDelegate)(this.getModelo())).getFabrica();
	}

	public void doGuardarArticulosPendientes(Collection<ArticuloAFabricarVO> artic) {
		((BusinessDelegate)(this.getModelo())).guardarArticulosPendientes();
	}

	public void doGuardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2) {
		// TODO Auto-generated method stub
		
	}


	
	
	
    
}

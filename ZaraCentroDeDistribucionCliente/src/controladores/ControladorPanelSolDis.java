package controladores;

import BusinessLogic.BusinessDelegate;
import RemoteMVCFramework.Controlador;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;
import VO.SolicitudVO;
import VO.TiendaVO;
import Varios.FileReaderWrapper;
import Varios.XMLWrapper;
import Vistas.VistaMain;
import Vistas.VistaSolDis;

import com.thoughtworks.xstream.XStream;
import java.util.Vector;

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
    
    public void doGuardarSolicitudEnvios(SolicitudEnvioVO solenv)
    {
        ((BusinessDelegate)(this.getModelo())).guardarSolicitudEnvios(solenv);
    }
    
    public void doGuardarSolicitudFabricacion(SolicitudFabricaVO solFab){
    	((BusinessDelegate)(this.getModelo())).guardarSolicitudFabrica(solFab);
    }

	public void doCargarXML(boolean b) {
		((VistaSolDis)vista).cargarTabla(b);
		vista.actualizar();
	}

	
	
	
    
}

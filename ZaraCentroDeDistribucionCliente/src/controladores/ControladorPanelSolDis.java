package controladores;

import BusinessLogic.BusinessDelegate;
import RemoteMVCFramework.Controlador;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;
import VO.SolicitudDistribucionVO;
import VO.SolicitudVO;
import VO.TiendaVO;
import Varios.FileReaderWrapper;
import Varios.XMLWrapper;
import Vistas.VistaMain;

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

	public SolicitudDistribucionVO doCargarXML(String url) {
		XMLWrapper xml = new XMLWrapper();
		SolicitudDistribucionVO solDisVO = (SolicitudDistribucionVO) xml.parseXMLSD(url);
		return solDisVO;
		
	}
	
	
	public Vector<String> doGetDescripciones(Vector<Long> codigos){
		Vector<String> desc = ((BusinessDelegate)(this.getModelo())).getDescripciones(codigos);
		return desc;
	}
	
	public Vector<Integer> doGetStocks(Vector<Long> codigos){
		Vector<Integer> stocks = ((BusinessDelegate)(this.getModelo())).getStocks(codigos);
		return stocks;
	}
	
	
	
    
}

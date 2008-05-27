package controladores;

import BusinessLogic.BusinessDelegate;
import BusinessLogic.SolicitudDistribucionVO;
import BusinessLogic.SolicitudVO;
import BusinessLogic.TiendaVO;
import RemoteMVCFramework.Controlador;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;
import Varios.FileReaderWrapper;
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

    public void doMostrarSolicitud(String url)
    {
        ((VistaMain)this.getVista()).fillSDTable(this.parseXMLSD(url));
        ((VistaMain)this.getVista()).actualizar();
    }

    public void doGuardarSolicitud(SolicitudDistribucionVO soldis)
    {
        ((BusinessDelegate)(this.getModelo())).guardarSolicitud(soldis);
    }

	public SolicitudDistribucionVO doCargarXML(String url) {
		SolicitudDistribucionVO solDisVO = (SolicitudDistribucionVO) parseXMLSD(url);
		return solDisVO;
		
	}
	
	
	public Vector<String> doGetDescripcion(Vector<Integer> codigos){
		((BusinessDelegate)(this.getModelo())).getDescripciones(codigos);
	}
	
	
	
	
	
	
    private SolicitudDistribucionVO parseXMLSD(String url)
    {
        FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();


        xstream.alias("solicituddistribucion", SolicitudDistribucionVO.class);
        //xstream.alias("articuloropa", ArticuloRopa.class);
        //xstream.alias("articulohogar", ArticuloHogar.class);
        xstream.alias("tienda", TiendaVO.class);

        SolicitudDistribucionVO sol = (SolicitudDistribucionVO) xstream.fromXML(XML);

        return sol;
    }
    
}

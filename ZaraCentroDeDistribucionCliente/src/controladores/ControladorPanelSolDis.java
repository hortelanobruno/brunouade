package controladores;

import BusinessLogic.BusinessDelegate;
import BusinessLogic.SolicitudDistribucionVO;
import BusinessLogic.SolicitudVO;
import BusinessLogic.TiendaVO;
import BusinessLogic.VistaMain;
import RemoteMVCFramework.Controlador;
import RemoteMVCFramework.ProxyModelo;
import RemoteMVCFramework.Vista;
import Varios.FileReaderWrapper;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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

    private SolicitudDistribucionVO parseXMLSD(String url)
    {
        FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();


        xstream.alias("solicituddistribucion", SolicitudDistribucionVO.class);
        xstream.alias("articuloropa", ArticuloRopa.class);
        xstream.alias("articulohogar", ArticuloHogar.class);
        xstream.alias("tienda", TiendaVO.class);

        SolicitudDistribucionVO sol = (SolicitudDistribucionVO) xstream.fromXML(XML);

        return sol;
    }

    public Vector<SolicitudVO> doGetSolicitudes(String tienda)
    {
    	return null;
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
}

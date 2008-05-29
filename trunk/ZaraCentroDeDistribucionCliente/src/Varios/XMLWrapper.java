package Varios;

import VO.SolicitudDistribucionVO;
import VO.SolicitudFabricaVO;
import VO.TiendaVO;

import com.thoughtworks.xstream.XStream;

public class XMLWrapper {

	
	public XMLWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	
    public SolicitudDistribucionVO parseXMLSD(String url)
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
    
    public SolicitudFabricaVO parseXMLSF(String url)
    {
    	
    }
}

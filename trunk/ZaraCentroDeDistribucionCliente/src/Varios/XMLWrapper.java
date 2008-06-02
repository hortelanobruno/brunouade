package Varios;

import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
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
    	
		return null;
    	
    }
    
    public XMLArticulo parseXMLArticulo(String url)
    {
    	FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();

        xstream.alias("XMLArticulo", XMLArticulo.class);

        XMLArticulo art = (XMLArticulo) xstream.fromXML(XML);

        return art;
    }


	public SolicitudDeReposicionVO parseXMLSR(String urlXML) {
		FileReaderWrapper fileReader = new FileReaderWrapper(urlXML);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();


        xstream.alias("solicitudreposicion", SolicitudDeReposicionVO.class);
        //xstream.alias("articuloropa", ArticuloRopa.class);
        //xstream.alias("articulohogar", ArticuloHogar.class);
        xstream.alias("fabrica", FabricaVO.class);

        SolicitudDeReposicionVO sol = (SolicitudDeReposicionVO) xstream.fromXML(XML);

        return sol;
	}
}

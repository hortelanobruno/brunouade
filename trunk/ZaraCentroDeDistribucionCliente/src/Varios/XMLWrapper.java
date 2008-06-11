package Varios;

import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.TiendaVO;

import com.thoughtworks.xstream.XStream;

public class XMLWrapper 
{
	public XMLWrapper() 
	{

	}
	
    public SolicitudDistribucionVO parseXMLSD(String url)
    {
        FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();
        XMLAdapter xmlAdapter = new XMLAdapter();
        xstream.alias("solicituddistribucion", XMLSolDis.class);
        xstream.alias("articulopedido", XMLArticuloPedido.class);
        xstream.alias("tienda", TiendaVO.class);

        return xmlAdapter.getSolDisVOFromXML((XMLSolDis) xstream.fromXML(XML));
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

	public SolicitudDeReposicionVO parseXMLSR(String urlXML) 
	{
		FileReaderWrapper fileReader = new FileReaderWrapper(urlXML);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();
        XMLAdapter xmlAdapter = new XMLAdapter();
        xstream.alias("solrep", XMLSolRep.class);
        xstream.alias("fabrica", FabricaVO.class);
        xstream.alias("articulorecibido", XMLArticuloRecibido.class);
        XMLSolRep srep = (XMLSolRep) xstream.fromXML(XML);
        
        System.out.println("hola");
        return xmlAdapter.getSolRepVOFromXML(srep);
	}
}

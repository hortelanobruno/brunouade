package Varios;

import VO.SolicitudFabricaVO;
import VO.TiendaVO;

import com.thoughtworks.xstream.XStream;

public class XMLWrapper 
{
	public XMLWrapper() 
	{

	}
	
    public XMLSolDis parseXMLSD(String url)
    {
        FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();

        xstream.alias("solicituddistribucion", XMLSolDis.class);
        xstream.alias("articulopedido", XMLArticuloPedido.class);
        xstream.alias("tienda", TiendaVO.class);

        return (XMLSolDis) xstream.fromXML(XML);
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

	public XMLSolRep parseXMLSR(String urlXML) 
	{
		FileReaderWrapper fileReader = new FileReaderWrapper(urlXML);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();

        xstream.alias("solrep", XMLSolRep.class);
        xstream.alias("articulorecibido", XMLArticuloRecibido.class);

        return (XMLSolRep) xstream.fromXML(XML);
	}
}

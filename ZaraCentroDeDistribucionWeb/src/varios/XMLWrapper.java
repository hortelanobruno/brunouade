package varios;

import vo.FabricaVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudEnvioVO;
import vo.SolicitudFabricaVO;
import vo.TiendaVO;

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
        
        SolicitudDistribucionVO sol = null; 
        try {
        	XMLSolDis xmlsoldis = (XMLSolDis) xstream.fromXML(XML);
        	sol = xmlAdapter.getSolDisVOFromXML(xmlsoldis);
        } catch (Exception e) {

        }
        return sol;
    }
    
    public XMLArticulo parseXMLArticulo(String url)
    {
    	XMLArticulo art = null;
    	FileReaderWrapper fileReader = new FileReaderWrapper(url);
        String XML = fileReader.obtenerContenido();
        XStream xstream = new XStream();
        xstream.alias("XMLArticulo", XMLArticulo.class);
        try {
			art = (XMLArticulo) xstream.fromXML(XML);
		} catch (RuntimeException e) {
		}
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
        SolicitudDeReposicionVO sol = null;
        try {
        	XMLSolRep srep = (XMLSolRep) xstream.fromXML(XML);
			sol = xmlAdapter.getSolRepVOFromXML(srep);
		} catch (Exception e) {
			
		}
        return sol;
	}

	public void parseXMLSolFab(SolicitudFabricaVO solFab) {
		XStream xstream = new XStream();
		XMLAdapter adapter = new XMLAdapter();
		
		xstream.alias("articuloFabrica", XMLArticuloFabrica.class);
		xstream.alias("centro", XMLCentro.class);
		xstream.alias("fabrica", XMLFabrica.class);
		xstream.alias("solFab", XMLSolFab.class);

		String solFabXML = xstream.toXML(adapter.getXMLSolFabFromSolFabVO(solFab));
		int id = solFab.getIdFab();
		//Escribo la salida en un archivo
		String file = "xml/solfab"+id+".xml";
		FileWriterWrapper fileWriter = new FileWriterWrapper(file);
		fileWriter.write(solFabXML);
	}

	public void parseXMLSolEnvio(SolicitudEnvioVO solEnvio) {
		XStream xstream = new XStream();
		XMLAdapter adapter = new XMLAdapter();
		
		xstream.alias("articuloaenviar",XMLArticuloAEnviar.class);
		xstream.alias("centro", XMLCentro.class);
		xstream.alias("tienda", XMLTienda.class);
		xstream.alias("solEnv", XMLSolEnv.class);
		//Parsear el objeto saco a XML

		String solEnvXML = xstream.toXML(adapter.getXMLSolEnvFromSolEnvVO(solEnvio));
		int id = solEnvio.getIdEnv();
		//Escribo la salida en un archivo
		String file = "xml/solenvio"+id+".xml";
		FileWriterWrapper fileWriter = new FileWriterWrapper(file);
		fileWriter.write(solEnvXML);
	}
}

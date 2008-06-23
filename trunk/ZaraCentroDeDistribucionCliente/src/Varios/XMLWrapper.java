package Varios;

import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloPedidoVO;
import VO.CategoriaHogarVO;
import VO.CentroDistribucionVO;
import VO.FabricaVO;
import VO.LineaRopaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;
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

	public void parseXMLSolFab(SolicitudFabricaVO solFab) {
		XStream xstream = new XStream();
		xstream.alias("centrodistribucion", CentroDistribucionVO.class);
		xstream.alias("fabrica", FabricaVO.class);
		xstream.alias("categoriahogar", CategoriaHogarVO.class);
		xstream.alias("linearopa", LineaRopaVO.class);
		xstream.alias("articuloafabricar", ArticuloAFabricarVO.class);
		xstream.alias("solicituddistribucion", SolicitudDistribucionVO.class);
		xstream.alias("articuloheader", ArticuloHeaderVO.class);
		//Parsear el objeto saco a XML
		String solFabXML = xstream.toXML(solFab);
		int id = solFab.getNumero();
		//Escribo la salida en un archivo
		String file = "xml/solfab"+id+".xml";
		FileWriterWrapper fileWriter = new FileWriterWrapper(file);
		fileWriter.write(solFabXML);
		
		
	}

	public void parseXMLSolEnvio(SolicitudEnvioVO solEnvio) {
		XStream xstream = new XStream();
		xstream.alias("tienda", TiendaVO.class);
		xstream.alias("articuloaenviar", ArticuloAEnviarVO.class);
		xstream.alias("articuloheader", ArticuloHeaderVO.class);
		xstream.alias("solicituddistribucion", SolicitudDistribucionVO.class);
		xstream.alias("articulopedido", ArticuloPedidoVO.class);
		//Parsear el objeto saco a XML
		String solEnvXML = xstream.toXML(solEnvio);
		int id = solEnvio.getNumero();
		//Escribo la salida en un archivo
		String file = "xml/solenvio"+id+".xml";
		FileWriterWrapper fileWriter = new FileWriterWrapper(file);
		fileWriter.write(solEnvXML);
	}
}

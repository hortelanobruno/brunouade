package Varios;

import java.util.Iterator;
import java.util.Vector;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.FabricaVO;
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
		/*xstream.alias("centrodistribucion", CentroDistribucionVO.class);
		xstream.alias("fabrica", FabricaVO.class);
		xstream.alias("categoriahogar", CategoriaHogarVO.class);
		xstream.alias("linearopa", LineaRopaVO.class);
		xstream.alias("articuloafabricar", ArticuloAFabricarVO.class);
		xstream.alias("solicituddistribucion", SolicitudDistribucionVO.class);
		xstream.alias("articuloheader", ArticuloHeaderVO.class);
		//Parsear el objeto saco a XML
		String solFabXML = xstream.toXML(solFab);
		int id = solFab.getNumero();*/
		xstream.alias("articuloFabrica", XMLArticuloFabrica.class);
		xstream.alias("centro", XMLCentro.class);
		xstream.alias("fabrica", XMLFabrica.class);
		xstream.alias("solFab", XMLSolFab.class);

		XMLFabrica xmlFabrica = new XMLFabrica();
		xmlFabrica.setCodigoFabrica(solFab.getFabrica().getCodigoFabrica());
		xmlFabrica.setNombreFabrica(solFab.getFabrica().getNombreFabrica());
		
		XMLCentro xmlCentro = new XMLCentro();
		xmlCentro.setCodCentro(solFab.getCdVO().getCodCentro());
		xmlCentro.setNombreCentro(solFab.getCdVO().getNombreCentro());
		xmlCentro.setLatitud(solFab.getCdVO().getLatitud());
		xmlCentro.setLongitud(solFab.getCdVO().getLongitud());
		
		Vector<XMLArticuloFabrica> xmlArticulos = new Vector<XMLArticuloFabrica>();
		
		for(Iterator i = solFab.getArticulosAFabricar().iterator(); i.hasNext();)
		{
			ArticuloAFabricarVO arVO = (ArticuloAFabricarVO)i.next();
			XMLArticuloFabrica xmlArtFab = new XMLArticuloFabrica();
			xmlArtFab.setCod(arVO.getArt().getCodigo());
			xmlArtFab.setCant(arVO.getCantidadAFabricar());
			xmlArticulos.add(xmlArtFab);
		}
		
		XMLSolFab xmlSolFab = new XMLSolFab();
		xmlSolFab.setNumero(solFab.getIdFab());
		xmlSolFab.setFecha(solFab.getFechaEmision());
		xmlSolFab.setCentro(xmlCentro);
		xmlSolFab.setFabrica(xmlFabrica);
		xmlSolFab.setArticulosAFabricar(xmlArticulos);
		String solFabXML = xstream.toXML(xmlSolFab);
		int id = solFab.getIdFab();
		//Escribo la salida en un archivo
		String file = "xml/solfab"+id+".xml";
		FileWriterWrapper fileWriter = new FileWriterWrapper(file);
		fileWriter.write(solFabXML);
	}

	public void parseXMLSolEnvio(SolicitudEnvioVO solEnvio) {
		XStream xstream = new XStream();
		/*xstream.alias("tienda", TiendaVO.class);
		xstream.alias("articuloaenviar", ArticuloAEnviarVO.class);
		xstream.alias("articuloheader", ArticuloHeaderVO.class);
		xstream.alias("solicituddistribucion", SolicitudDistribucionVO.class);
		xstream.alias("articulopedido", ArticuloPedidoVO.class);*/
		
		xstream.alias("articuloaenviar",XMLArticuloAEnviar.class);
		xstream.alias("centro", XMLCentro.class);
		xstream.alias("tienda", XMLTienda.class);
		xstream.alias("solEnv", XMLSolEnv.class);
		//Parsear el objeto saco a XML
		
		XMLTienda xmlTienda = new XMLTienda();
		xmlTienda.setCodigoTienda(solEnvio.getTienda().getCodigoTienda());
		xmlTienda.setNombreTienda(solEnvio.getTienda().getNombreTienda());
		
		XMLCentro xmlCentro = new XMLCentro();
		xmlCentro.setCodCentro(solEnvio.getCdVO().getCodCentro());
		xmlCentro.setNombreCentro(solEnvio.getCdVO().getNombreCentro());
		xmlCentro.setLatitud(solEnvio.getCdVO().getLatitud());
		xmlCentro.setLongitud(solEnvio.getCdVO().getLongitud());
		
		Vector<XMLArticuloAEnviar> arts = new Vector<XMLArticuloAEnviar>();
		Iterator i = solEnvio.getArticulosAEnviar().iterator();
		ArticuloAEnviarVO artAEnv = (ArticuloAEnviarVO)i.next();
		int idSolDis = artAEnv.getSolDis().getIdDis();
		
		for(Iterator it = solEnvio.getArticulosAEnviar().iterator(); it.hasNext();)
		{
			ArticuloAEnviarVO artVO = new ArticuloAEnviarVO();
			artVO = (ArticuloAEnviarVO) it.next();
			XMLArticuloAEnviar xmlArt = new XMLArticuloAEnviar();
			xmlArt.setCod(artVO.getArt().getCodigo());
			xmlArt.setCant(artVO.getCantidadAEnviar());
			arts.add(xmlArt);
		}
		
		XMLSolEnv xmlSolEnv = new XMLSolEnv();

		xmlSolEnv.setNumero(solEnvio.getIdEnv());
		xmlSolEnv.setSolDis(idSolDis);
		xmlSolEnv.setCentro(xmlCentro);
		xmlSolEnv.setTienda(xmlTienda);
		xmlSolEnv.setArticulosAEnviar(arts);	
		
		String solEnvXML = xstream.toXML(xmlSolEnv);
		int id = solEnvio.getIdEnv();
		//Escribo la salida en un archivo
		String file = "xml/solenvio"+id+".xml";
		FileWriterWrapper fileWriter = new FileWriterWrapper(file);
		fileWriter.write(solEnvXML);
	}
}

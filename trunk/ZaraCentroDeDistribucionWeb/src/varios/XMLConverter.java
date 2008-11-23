package varios;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import vo.ArticuloAEnviarVO;
import vo.ArticuloAFabricarVO;
import vo.ArticuloAReponerVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloPedidoVO;
import vo.ArticuloRopaVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudEnvioVO;
import vo.SolicitudFabricaVO;
import vo.TiendaVO;

import com.thoughtworks.xstream.XStream;

public class XMLConverter 
{
	public static ArticuloHeaderVO getArticuloFromString(String msg)
	{
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(msg);
		Document doc;
		try {
			doc = builder.build(in);
			Element root = doc.getRootElement();
			if(root.getChild("talle") == null){
				//Articulo Hogar
				ArticuloHogarVO artHogarVO = new ArticuloHogarVO();
				artHogarVO.setCantidad(0);
				artHogarVO.setCategoria(root.getChild("categoria").getText());
				artHogarVO.setCodigo(Long.parseLong(root.getChild("referencia").getText()));
				artHogarVO.setColor(root.getChild("color").getText());
				artHogarVO.setComposicion(root.getChild("compocicion").getText());
				artHogarVO.setDescripcion(root.getChild("descripcion").getText());
				artHogarVO.setDetalles(root.getChild("nombre").getText());
				artHogarVO.setLinea(root.getChild("linea").getText());
				artHogarVO.setMedidas(root.getChild("medidas").getText());
				artHogarVO.setPrecio(Float.parseFloat(root.getChild("precioVentaUnitario").getText()));
				artHogarVO.setSeccion(root.getChild("seccion").getText());
				return artHogarVO;
			}
			else{
				//Articulo Personal
				ArticuloRopaVO artRopaVO = new ArticuloRopaVO();
				artRopaVO.setCantidad(0);
				artRopaVO.setCodigo(Long.parseLong(root.getChild("referencia").getText()));
				artRopaVO.setColor(root.getChild("color").getText());
				artRopaVO.setDescripcion(root.getChild("descripcion").getText());
				artRopaVO.setLinea(root.getChild("linea").getText());
				artRopaVO.setOrigen(root.getChild("origen").getText());
				artRopaVO.setPrecio(Float.parseFloat(root.getChild("precioVentaUnitario").getText()));
				artRopaVO.setSeccion(root.getChild("seccion").getText());
				artRopaVO.setTalle(root.getChild("talle").getText());
				return artRopaVO;
			}
		}
		catch(JDOMException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static SolicitudDistribucionVO getSolDisVOFromString(String in0)
	{
		Logger logger = Logger.getLogger("zara.centro");
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(in0);
		Document doc;
		try {
			doc = builder.build(in);
			Element root = doc.getRootElement();
			SolicitudDistribucionVO soldis = new SolicitudDistribucionVO();
			soldis.setFechaEmision(getFechaHoraFromString((root.getChild("fechaSolicitud").getText())));
			Element items = root.getChild("items");
			if(items != null){
				List hijos = items.getChildren();
				List<ArticuloPedidoVO> articulos = new ArrayList<ArticuloPedidoVO>();
				String cod,nombre,referencia,cantidad;
				for(Object hijo : hijos){
					soldis.setIdPedido(Integer.parseInt(((Element)hijo).getChild("idPedido").getText()));
					ArticuloPedidoVO articulo = new ArticuloPedidoVO();
					ArticuloHeaderVO art = new ArticuloHeaderVO();
					TiendaVO tienda = new TiendaVO();
					cod = ((Element)hijo).getChild("tienda").getChild("codigo").getText();
					if(cod.equals("")){
						logger.debug("Error al leer la solicitud de distribucion");
						return null;
					}
					tienda.setCodigoTienda(Integer.parseInt(cod));
					nombre = ((Element)hijo).getChild("tienda").getChild("nombre").getText();
					if(nombre.equals("")){
						logger.debug("Error al leer la solicitud de distribucion");
						return null;
					}
					tienda.setNombreTienda(nombre);
					articulo.setTienda(tienda);
					referencia = ((Element)hijo).getChild("articulo").getChild("referencia").getText();
					if(referencia.equals("")){
						logger.debug("Error al leer la solicitud de distribucion");
						return null;
					}
					art.setCodigo(Integer.parseInt(referencia));
					cantidad = ((Element)hijo).getChild("catidad").getText();
					if(cantidad.equals("")){
						logger.debug("Error al leer la solicitud de distribucion");
						return null;
					}
					articulo.setCantidadPedida(Integer.parseInt(cantidad));
					articulo.setArt(art);
					articulos.add(articulo);
				}
				soldis.setArticulosPedidos(articulos);
				return soldis;
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("Error al leer la solicitud de distribucion");
		return null;
	}
	
	public static SolicitudDeReposicionVO getSolRepVOFromString(String in0, int nextID)
	{
		
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(in0);
		Document doc;
		Logger logger = Logger.getLogger("zara.centro");
		int aux = 0;
		try {
			doc = builder.build(in);
			Element root = doc.getRootElement();
			SolicitudDeReposicionVO solrep = new SolicitudDeReposicionVO();
			solrep.setFechaEmision(new Date());
			Collection<ArticuloAReponerVO> arts = new ArrayList<ArticuloAReponerVO>();
			ArticuloAReponerVO art = new ArticuloAReponerVO();
			ArticuloHeaderVO artH = new ArticuloHeaderVO();
			artH.setCodigo(Long.parseLong(root.getChild("productoList").getChild("int").getText()));
			art.setArt(artH);
			aux++;
			art.setCantidadRecibida((int)Float.parseFloat(root.getChild("cantidad").getText()));
			art.setIdAAR(nextID);
			arts.add(art);
			solrep.setArticulosAReponer(arts);
			return solrep;
		} catch (JDOMException e) {
			if(aux==0){
				logger.debug("Error al parsear la solicitud de reposicion. Error en el codigo del articulo");
			}else{
				logger.debug("Error al parsear la solicitud de reposicion. Error en la cantidad enviada del articulo");
			}
			return null;
		} catch (IOException e) {
			if(aux==0){
				logger.debug("Error al parsear la solicitud de reposicion. Error en el codigo del articulo");
			}else{
				logger.debug("Error al parsear la solicitud de reposicion. Error en la cantidad enviada del articulo");
			}
			return null;
		}
	}
	
	public static String getStringFromSolFab(SolicitudFabricaVO solFab)
	{
		XStream xstream = new XStream();	
		
		xstream.alias("articuloFabrica", XMLArticuloFabrica.class);
		xstream.alias("centro", XMLCentro.class);
		xstream.alias("fabrica", XMLFabrica.class);
		xstream.alias("solFab", XMLSolFab.class);

		return xstream.toXML(getXMLSolFabFromSolFabVO(solFab));
	}
	
	private static XMLSolFab getXMLSolFabFromSolFabVO(SolicitudFabricaVO solFab)
	{
		XMLFabrica xmlFabrica = new XMLFabrica();
		xmlFabrica.setCodigoFabrica(solFab.getFabrica().getCodigoFabrica());
		xmlFabrica.setNombreFabrica(solFab.getFabrica().getNombreFabrica());
		
		XMLCentro xmlCentro = new XMLCentro();
		xmlCentro.setCodCentro(solFab.getCdVO().getCodCentro());
		xmlCentro.setNombreCentro(solFab.getCdVO().getNombreCentro());
		xmlCentro.setLatitud(solFab.getCdVO().getLatitud());
		xmlCentro.setLongitud(solFab.getCdVO().getLongitud());
		
		Vector<XMLArticuloFabrica> xmlArticulos = new Vector<XMLArticuloFabrica>();
		Map<Long,Integer> mapa = new HashMap<Long,Integer>();
		
		for(Iterator i = solFab.getArticulosAFabricar().iterator(); i.hasNext();)
		{
			ArticuloAFabricarVO arVO = (ArticuloAFabricarVO)i.next();
			long cod = arVO.getArt().getCodigo();
			int cant = arVO.getCantidadAFabricar();
			if(!mapa.containsKey(cod)){
				mapa.put(cod,cant);
			}else{
				mapa.put(cod, cant + mapa.get(cod));
			}
		}
		
		XMLArticuloFabrica xmlArtFab;
		Object[] keys = mapa.keySet().toArray();
		Object[] values = mapa.values().toArray();
		for(int i=0 ; i < mapa.size() ; i++){
			xmlArtFab = new XMLArticuloFabrica();
			xmlArtFab.setCod((Long)keys[i]);
			xmlArtFab.setCant((Integer)values[i]);
			xmlArticulos.add(xmlArtFab);
		}
		
		XMLSolFab xmlSolFab = new XMLSolFab();
		xmlSolFab.setNumero(solFab.getIdFab());
		xmlSolFab.setFecha(solFab.getFechaEmision());
		xmlSolFab.setCentro(xmlCentro);
		xmlSolFab.setFabrica(xmlFabrica);
		xmlSolFab.setArticulosAFabricar(xmlArticulos);
		
		return xmlSolFab;
	}
	
	public static String getStringFromSolEnv(SolicitudEnvioVO solEnvio)
	{
		XStream xstream = new XStream();
		
		xstream.alias("articuloaenviar",XMLArticuloAEnviar.class);
		xstream.alias("centro", XMLCentro.class);
		xstream.alias("tienda", XMLTienda.class);
		xstream.alias("solEnv", XMLSolEnv.class);
		//Parsear el objeto saco a XML

		return xstream.toXML(getXMLSolEnvFromSolEnvVO(solEnvio));
	}
	
	private static XMLSolEnv getXMLSolEnvFromSolEnvVO(SolicitudEnvioVO solEnvio)
	{
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
		
		int idSolDis = solEnvio.getSolDis().getIdDis();
		
		for(Iterator it = solEnvio.getArticulosAEnviar().iterator(); it.hasNext();)
		{
			ArticuloAEnviarVO artVO = new ArticuloAEnviarVO();
			artVO = (ArticuloAEnviarVO) it.next();
			XMLArticuloAEnviar xmlArt = new XMLArticuloAEnviar();
			xmlArt.setCod(artVO.getArt().getCodigo());
			xmlArt.setIdPedido(artVO.getIdPedido());
			xmlArt.setCant(artVO.getCantidadAEnviar());
			arts.add(xmlArt);
		}
		
		XMLSolEnv xmlSolEnv = new XMLSolEnv();

		xmlSolEnv.setNumero(solEnvio.getIdEnv());
		xmlSolEnv.setSolDis(idSolDis);
		xmlSolEnv.setCentro(xmlCentro);
		xmlSolEnv.setTienda(xmlTienda);
		xmlSolEnv.setArticulosAEnviar(arts);	
		xmlSolEnv.setFechaEmision(solEnvio.getFechaEmision());
		return xmlSolEnv;
	}
	
	private static String getFecha(String f)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<f.indexOf(" ");i++)
			sb.append(f.charAt(i));
		
		return sb.toString();
	}
	
	private static String getHora(String f)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = f.indexOf(" "); i<f.length();i++)
			sb.append(f.charAt(i));
		
		return sb.toString();
	}
	
	@SuppressWarnings("deprecation")
	private static Date getFechaHoraFromString(String f)
	{
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
		DateFormat df1 = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());
		f = f.replace("-", "/");
		Date fn;
		Date fn2;
		try 
		{
			fn = df.parse(getFecha(f));
			fn.setHours(Integer.parseInt(getHora(f).split(":")[0].trim()));
			fn.setMinutes(Integer.parseInt(getHora(f).split(":")[1].trim()));
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
		return fn;
	}	
}

package Varios;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloAReponerVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloPedidoVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;

public class XMLAdapter 
{
	public SolicitudDistribucionVO getSolDisVOFromXML(XMLSolDis sd) throws Exception 
	{
		SolicitudDistribucionVO disVO = new SolicitudDistribucionVO();
		disVO.setIdDis(sd.getNumero());
		disVO.setTienda(sd.getTienda());
		Collection<ArticuloPedidoVO> arts = new ArrayList<ArticuloPedidoVO>();
		
		for (int i =0 ; i < sd.getArticulosPedidos().size() ; i++)
		{
			XMLArticuloPedido arped = sd.getArticulosPedidos().elementAt(i);
			ArticuloPedidoVO ar = new ArticuloPedidoVO();
			ArticuloHeaderVO arhead = new ArticuloHeaderVO();
			ar.setCantidad(arped.getCant());
			arhead.setCodigo(arped.getCod());
			ar.setArt(arhead);
			arts.add(ar);
		}
		disVO.setArticulosPedidos(arts);
		disVO.setFechaEmision(this.getFechaHoraFromString(sd.getFecha()));
		disVO.setFechaEmision(new Date());
		return disVO;
	}
	
	public SolicitudDeReposicionVO getSolRepVOFromXML(XMLSolRep sr) throws Exception
	{
		SolicitudDeReposicionVO ret = new SolicitudDeReposicionVO();
		SolicitudFabricaVO sf = new SolicitudFabricaVO();
		sf.setFabrica(sr.getFabrica());
		sf.setIdFab(sr.getCodSolFab());
		ret.setIdRep(sr.getCodigo());
		ret.setFabrica(sr.getFabrica());
		Collection<ArticuloAReponerVO> arts = new ArrayList<ArticuloAReponerVO>();
		
		for(int i = 0; i< sr.getArticulosRecibidos().size();i++)
		{
			XMLArticuloRecibido ar= sr.getArticulosRecibidos().elementAt(i);
			ArticuloAReponerVO aar = new ArticuloAReponerVO();
			ArticuloHeaderVO arhead = new ArticuloHeaderVO();
			aar.setCantidad(ar.getCant());
			arhead.setCodigo(ar.getCodArt());
			aar.setArt(arhead);
			arts.add(aar);
		}
		ret.setArticulosAReponer(arts);
		ret.setSolFab(sf);
		ret.setFechaEmision(this.getFechaHoraFromString(sr.getFechaEmision()));
		return ret;
	}

	public XMLSolEnv getXMLSolEnvFromSolEnvVO(SolicitudEnvioVO solEnvio)
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
		xmlSolEnv.setFechaEmision(solEnvio.getFechaEmision());
		return xmlSolEnv;
	}

	public XMLSolFab getXMLSolFabFromSolFabVO(SolicitudFabricaVO solFab)
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
		
		return xmlSolFab;
	}
	
	private String getFecha(String f)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<f.indexOf(" ");i++)
			sb.append(f.charAt(i));
		
		return sb.toString();
	}
	
	private String getHora(String f)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = f.indexOf(" "); i<f.length();i++)
			sb.append(f.charAt(i));
		
		return sb.toString();
	}
	
	@SuppressWarnings("deprecation")
	private Date getFechaHoraFromString(String f)
	{
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
		DateFormat df1 = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());
		
		Date fn;
		Date fn2;
		try 
		{
			fn = df.parse(this.getFecha(f));
			//fn2 = df1.parse(this.getHora(f));	
			fn.setHours(Integer.parseInt(this.getHora(f).split(":")[0].trim()));
			fn.setMinutes(Integer.parseInt(this.getHora(f).split(":")[1].trim()));
			//fn.setSeconds(fn2.getSeconds());
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			return null;
		}
		return fn;
	}
}

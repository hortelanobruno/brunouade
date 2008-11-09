package xml;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import vo.ArticuloAReponerVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloPedidoVO;
import vo.ArticuloRopaVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudFabricaVO;
import vo.TiendaVO;
import xmlVOSolDisLaCoruna.itemSolDis;

public class XMLAdapter 
{
	public ArticuloHeaderVO getArticuloFromXMLArticuloLaCoruna(XMLArticuloLaCoruna art)
	{
		ArticuloHeaderVO ahvo = new ArticuloHeaderVO();

		ahvo.setCodigo(art.getReferencia());
		ahvo.setCantidad(0);
		ahvo.setColor(art.getColor());
		ahvo.setDescripcion(art.getDescripcion());
		ahvo.setPrecio(art.getPrecioventaunitario());
		ahvo.setSeccion(art.getSeccion());
		
		String auxTalle = art.getTalle();
		
		if(auxTalle == null) //Es hogar
		{
			ArticuloHogarVO ahovo = new ArticuloHogarVO();
			ahovo.setCodigo(ahvo.getCodigo());
			ahovo.setCantidad(ahvo.getCantidad());
			ahovo.setColor(ahvo.getColor());
			ahovo.setDescripcion(ahvo.getDescripcion());
			ahovo.setPrecio(ahvo.getPrecio());
			ahovo.setSeccion(ahvo.getSeccion());
			
			ahovo.setLinea(art.getLinea());
			ahovo.setDetalles(art.getNombre());
			ahovo.setComposicion(art.getComposicion());
			ahovo.setCategoria(art.getCategoria());
			ahovo.setMedidas(art.getMedidas());
			
			return ahovo;
		}
		else //es ropa
		{
			ArticuloRopaVO arvo = new ArticuloRopaVO();
			arvo.setCodigo(ahvo.getCodigo());
			arvo.setCantidad(ahvo.getCantidad());
			arvo.setColor(ahvo.getColor());
			arvo.setDescripcion(ahvo.getDescripcion());
			arvo.setPrecio(ahvo.getPrecio());
			arvo.setSeccion(ahvo.getSeccion());
			arvo.setTalle(auxTalle);
			arvo.setOrigen(art.getOrigen());
			
			return arvo;
		}
	}

	public SolicitudDeReposicionVO getSolRepVOFromXMLSolRepFabrica(XMLSolRepFabrica sol)
	{
		SolicitudDeReposicionVO ret = new SolicitudDeReposicionVO();
		SolicitudFabricaVO sf = new SolicitudFabricaVO();
		ArticuloAReponerVO aar = new ArticuloAReponerVO();
		ArticuloHeaderVO arhead = new ArticuloHeaderVO();
		Collection<ArticuloAReponerVO> arts = new ArrayList<ArticuloAReponerVO>();
		
		ret.setFabrica(null);
		ret.setIdRep((int)sol.getCodigo());
		ret.setFechaEmision(this.getFechaHoraFromString(sol.getFecha()));
		sf.setIdFab(sol.getCodSolFab());
		aar.setCantidad(sol.getCantidad());
		arhead.setCodigo(sol.getCodArt());
		aar.setArt(arhead);
		arts.add(aar);
		ret.setArticulosAReponer(arts);
		ret.setSolFab(sf);
		
		return ret;
	}

	public SolicitudDistribucionVO getSolDisVOFromXMLSolDisLaCoruna(XMLSolDisLaCoruna sol)
	{
		SolicitudDistribucionVO ret = new SolicitudDistribucionVO();
		TiendaVO t = new TiendaVO();
		ret.setIdDis(11); //ACA VER PORQUE LO TENEMOS QUE GENERAR NOSOTROS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		
		//t.setCodigoTienda(sol.get) //VER PORQUE TIENEN VARIOS ARTICULOS DE TIENDAS DISTINTAS!!!!!!!!!
		
		
		Collection<ArticuloPedidoVO> arts = new ArrayList<ArticuloPedidoVO>();
		
		for(int i = 0; i<sol.getItems().size();i++)
		{
			ArticuloPedidoVO ar = new ArticuloPedidoVO();
			ArticuloHeaderVO arhead = new ArticuloHeaderVO();
			
			itemSolDis it = sol.getItems().elementAt(i);
			ar.setCantidad(it.getCantidad());
			arhead.setCodigo(it.getArticulos());
			ar.setArt(arhead);
			arts.add(ar);
		}
		
		ret.setArticulosPedidos(arts);
		ret.setFechaEmision(this.getFechaHoraFromString(sol.getFecha()));
		
		//ret.setTienda(t); //VER LO COMENTADO DE ARRIBA!!!!!!!!!!!!!!!!!!!
		
		return ret;
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
			fn2 = df1.parse(this.getHora(f));	
			fn.setHours(fn2.getHours());
			fn.setMinutes(fn2.getMinutes());
			fn.setSeconds(fn2.getSeconds());
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			return null;
		}
		return fn;
	}
}

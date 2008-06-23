package Varios;

import java.util.ArrayList;
import java.util.Collection;
import VO.ArticuloAReponerVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloPedidoVO;
import VO.ArticuloRopaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudFabricaVO;

public class XMLAdapter 
{
	public SolicitudDistribucionVO getSolDisVOFromXML(XMLSolDis sd)
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
		return disVO;
	}
	
	public SolicitudDeReposicionVO getSolRepVOFromXML(XMLSolRep sr)
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
		return ret;
	}
	
	public ArticuloRopaVO getArticuloRopaVOFromXML(XMLArticulo art)
	{
		return null;
	}
	
	public ArticuloRopaVO getArticuloHogarVOFromXML(XMLArticulo art)
	{
		return null;
	}
}

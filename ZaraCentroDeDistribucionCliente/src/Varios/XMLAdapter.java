package Varios;

import java.util.ArrayList;
import java.util.Collection;

import VO.ArticuloHeaderVO;
import VO.ArticuloPedidoVO;
import VO.ArticuloRopaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;

public class XMLAdapter 
{
	public SolicitudDistribucionVO getSolDisVOFromXML(XMLSolDis sd)
	{
		SolicitudDistribucionVO disVO = new SolicitudDistribucionVO();
		disVO.setNumero(sd.getNumero());
		disVO.setTienda(sd.getTienda());
		Collection<ArticuloPedidoVO> arts = new ArrayList<ArticuloPedidoVO>();
		
		for (int i =0 ; i < sd.getArticulosPedidos().size() ; i++){
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
		return null;
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

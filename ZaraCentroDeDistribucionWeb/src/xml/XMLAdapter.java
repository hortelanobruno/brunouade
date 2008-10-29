package xml;

import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloRopaVO;

public class XMLAdapter 
{
	public ArticuloHeaderVO getArticuloFromXMLArticuloLaCoruna(XMLArticuloLaCoruna art)
	{
		ArticuloHeaderVO ahvo = new ArticuloHeaderVO();

		ahvo.setCodigo(art.getReferencia());
		ahvo.setCantidad(0);
		ahvo.setColor(art.getColor());
		ahvo.setDescripcion(art.getDescripcion());
		ahvo.setPrecio(art.getPrecio());
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
}

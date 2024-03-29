package beans;

import java.util.ArrayList;

import javax.ejb.Local;

import vo.CentroDistribucionVO;
import vo.FabricaVO;
import vo.TiendaVO;

@Local
public interface AdministracionConfiguracion 
{
	public void guardarDatosCD(int codigo,String nombre,float longitud, float latitud, ArrayList<String> lineasRopa, ArrayList<String> categoriasHogar);
	public CentroDistribucionVO getCentro();
	public void guardarFabrica(FabricaVO fab);
	public ArrayList<FabricaVO> getFabricas();	
	public ArrayList<TiendaVO> getTiendas();
	public FabricaVO getFabrica();
	public void guardarTienda(TiendaVO tienda);
	
}

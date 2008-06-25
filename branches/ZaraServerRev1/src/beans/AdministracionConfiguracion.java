package beans;

import java.util.ArrayList;
import javax.ejb.Remote;
import VO.CentroDistribucionVO;
import VO.FabricaVO;
import VO.TiendaVO;

@Remote
public interface AdministracionConfiguracion 
{
	public void guardarDatosCD(int codigo,String nombre,float longitud, float latitud, ArrayList<String> lineasRopa, ArrayList<String> categoriasHogar);
	public CentroDistribucionVO getCentro();
	public void guardarFabrica(FabricaVO fab);
	public ArrayList<FabricaVO> getFabricas();	
	public ArrayList<TiendaVO> getTiendas();
	public FabricaVO getFabrica();
	/*public void addLineaRopa(int cod, String linea);
	public void addCategoriaHogar(int cod, String categoria);
	public Vector<String> getCategoriasHogar();
	public Vector<String> getLineasRopa();*/
	
}

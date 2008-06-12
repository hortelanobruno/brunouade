package beans;

import java.util.ArrayList;
import java.util.Vector;

import javax.ejb.Remote;

import VO.CentroDistribucionVO;
import VO.FabricaVO;

@Remote
public interface AdministracionConfiguracion 
{
	public void guardarDatosCD(int codigo,String nombre,float longitud, float latitud, ArrayList<String> lineasRopa, ArrayList<String> categoriasHogar);
	public CentroDistribucionVO getCentro();
	public void addLineaRopa(int cod, String linea);
	public void addCategoriaHogar(int cod, String categoria);
	public Vector<String> getCategoriasHogar();
	public Vector<String> getLineasRopa();
	public ArrayList<FabricaVO> getFabricas();
}

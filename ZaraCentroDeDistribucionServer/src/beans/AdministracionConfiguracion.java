package beans;

import java.util.Vector;

import javax.ejb.Remote;

@Remote
public interface AdministracionConfiguracion 
{
	public void guardarDatosCD(int codigo,String nombre,float longitud, float latitud, Vector<String> lineasRopa, Vector<String> categoriasHogar);
	public void addLineaRopa(String linea);
	public void addCategoriaHogar(String categoria);
	public Vector<String> getCategoriasHogar();
	public Vector<String> getLineasRopa();
}

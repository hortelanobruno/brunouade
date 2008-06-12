package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CentroDistribucionVO  implements Serializable
{
	private static final long serialVersionUID = 1459311864724348026L;
	private int codCentro;
	private String nombreCentro;
	private float latitud;
	private float longitud;
	private Collection<LineaRopaVO> lineasRopa;
	private Collection<CategoriaHogarVO> categoriasHogar;
	
	
	public CentroDistribucionVO() {
		// TODO Auto-generated constructor stub
	}
	
	public CentroDistribucionVO(int codigo, String nombre, float longitud, float latitud, Collection<LineaRopaVO> lineasRopa, Collection<CategoriaHogarVO> categoriasHogar)
	{
		this.setCodCentro(codigo);
		this.setNombreCentro(nombre);
		this.setLongitud(longitud);
		this.setLatitud(latitud);
		this.lineasRopa = new ArrayList<LineaRopaVO>();
		this.categoriasHogar = new ArrayList<CategoriaHogarVO>();
		this.setLineasRopa(lineasRopa);
		this.setCategoriasHogar(categoriasHogar);
	}


	public Collection<CategoriaHogarVO> getCategoriasHogar() {
		return categoriasHogar;
	}


	public void setCategoriasHogar(Collection<CategoriaHogarVO> categoriasHogar) {
		this.categoriasHogar = categoriasHogar;
	}


	public int getCodCentro() {
		return codCentro;
	}


	public void setCodCentro(int codCentro) {
		this.codCentro = codCentro;
	}


	public float getLatitud() {
		return latitud;
	}


	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}


	public Collection<LineaRopaVO> getLineasRopa() {
		return lineasRopa;
	}


	public void setLineasRopa(Collection<LineaRopaVO> lineasRopa) {
		this.lineasRopa = lineasRopa;
	}


	public float getLongitud() {
		return longitud;
	}


	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}


	public String getNombreCentro() {
		return nombreCentro;
	}


	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}
	
	
}

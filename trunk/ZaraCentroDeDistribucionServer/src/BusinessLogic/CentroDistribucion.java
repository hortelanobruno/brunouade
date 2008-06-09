package BusinessLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import VO.CategoriaHogarVO;
import VO.CentroDistribucionVO;
import VO.LineaRopaVO;

@Entity
@Table(name="CentroDeDistribucion")
public class CentroDistribucion implements Serializable
{
	private static final long serialVersionUID = 1459311864724348026L;
	private int codCentro;
	private String nombreCentro;
	private float latitud;
	private float longitud;
	private Collection<LineaRopa> lineasRopa;
	private Collection<CategoriaHogar> categoriasHogar;
	
	public CentroDistribucion()
	{
		this.lineasRopa = new ArrayList<LineaRopa>();
		this.categoriasHogar = new ArrayList<CategoriaHogar>();
	}
	
	public CentroDistribucion(int codigo, String nombre, float longitud, float latitud, Collection<LineaRopa> lineasRopa, Collection<CategoriaHogar> categoriasHogar)
	{
		this.setCodCentro(codigo);
		this.setNombreCentro(nombre);
		this.setLongitud(longitud);
		this.setLatitud(latitud);
		this.lineasRopa = new ArrayList<LineaRopa>();
		this.categoriasHogar = new ArrayList<CategoriaHogar>();
		this.setLineasRopa(lineasRopa);
		this.setCategoriasHogar(categoriasHogar);
	}
	
	@Id
	@Column
	public int getCodCentro()
	{
		return codCentro;
	}
	
	public void setCodCentro(int codCentro) 
	{
		this.codCentro = codCentro;
	}
	
	@Column
	public String getNombreCentro() 
	{
		return nombreCentro;
	}
	
	public void setNombreCentro(String nombreCentro) 
	{
		this.nombreCentro = nombreCentro;
	}
	
	@Column
	public float getLatitud() 
	{
		return latitud;
	}
	
	public void setLatitud(float latitud) 
	{
		this.latitud = latitud;
	}
	
	@Column
	public float getLongitud()
	{
		return longitud;
	}
	
	public void setLongitud(float longitud)
	{
		this.longitud = longitud;
	}
	
	@OneToMany(cascade={CascadeType.ALL})
	public Collection<LineaRopa> getLineasRopa()
	{
		return this.lineasRopa;
	}

	public void setLineasRopa(Collection<LineaRopa> liRopa) 
	{
		//int numerador = 1;
		/*Iterator it = liRopa.iterator();

		while(it.hasNext())
			this.lineasRopa.add((LineaRopa)it.next());

		/*for(int i = 0;i< liRopa.size();i++)
			this.lineasRopa.add(new LineaRopa((i+1),liRopa.elementAt(i)));*/
		
		this.lineasRopa = liRopa;
	}

	@OneToMany(cascade={CascadeType.ALL})
	public Collection<CategoriaHogar> getCategoriasHogar()
	{
		return this.categoriasHogar;
	}

	public void setCategoriasHogar(Collection<CategoriaHogar> catHogar) 
	{
		//int numerador = 1;
		/*Iterator it = catHogar.iterator();
		
		while(it.hasNext())
			this.categoriasHogar.add((CategoriaHogar)it.next());
		
		/*for(int i = 0; i< catHogar.size();i++)
			this.categoriasHogar.add(new CategoriaHogar((i+1),catHogar.elementAt(i)));*/
		
		this.categoriasHogar = catHogar;

	}
	
	public void setVO(CentroDistribucionVO centro){
		Iterator it = centro.getLineasRopa().iterator();
		Collection<LineaRopa> lineas = new ArrayList<LineaRopa>();
		while(it.hasNext()){
			LineaRopaVO lineaVO = (LineaRopaVO) it.next();
			LineaRopa linea = new LineaRopa(lineaVO.getIdLinea(),lineaVO.getLinea());
			lineas.add(linea);
		}
		
		Iterator it2 = centro.getCategoriasHogar().iterator();
		Collection<CategoriaHogar> categorias = new ArrayList<CategoriaHogar>();
		while(it2.hasNext()){
			CategoriaHogarVO catVO = (CategoriaHogarVO) it.next();
			CategoriaHogar cat = new CategoriaHogar(catVO.getIdCategoria(),catVO.getCategoria());
			categorias.add(cat);
		}
		
		
		this.setCodCentro(centro.getCodCentro());
		this.setNombreCentro(centro.getNombreCentro());
		this.setLatitud(centro.getLatitud());
		this.setLongitud(centro.getLongitud());
		this.setLineasRopa(lineas);
		this.setCategoriasHogar(categorias);
	}
	
	public CentroDistribucionVO getVO(){
		CentroDistribucionVO centroVO = new CentroDistribucionVO();
		centroVO.setNombreCentro(this.getNombreCentro());
		centroVO.setCodCentro(this.getCodCentro());
		centroVO.setLongitud(this.getLongitud());
		centroVO.setLatitud(this.getLatitud());
		
		Iterator it = this.getLineasRopa().iterator();
		Collection<LineaRopaVO> lineas = new ArrayList<LineaRopaVO>();
		while(it.hasNext()){
			LineaRopa linea = (LineaRopa) it.next();
			LineaRopaVO lineaVO = new LineaRopaVO(linea.getIdLinea(),linea.getLinea());
			lineas.add(lineaVO);
		}
		
		Iterator it2 = this.getCategoriasHogar().iterator();
		Collection<CategoriaHogarVO> categorias = new ArrayList<CategoriaHogarVO>();
		while(it2.hasNext()){
			CategoriaHogar cat = (CategoriaHogar) it.next();
			CategoriaHogarVO catVO = new CategoriaHogarVO(cat.getIdCategoria(),cat.getCategoria());
			categorias.add(catVO);
		}
		
		centroVO.setCategoriasHogar(categorias);
		centroVO.setLineasRopa(lineas);
		return centroVO;
	}
}

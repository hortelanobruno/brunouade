package businesslogic;

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
import javax.persistence.Transient;

import vo.CategoriaHogarVO;
import vo.CentroDistribucionVO;
import vo.LineaRopaVO;

@Entity
@Table(name = "CentroDeDistribucion")
public class CentroDistribucion implements Serializable 
{
	private static final long serialVersionUID = 1459311864724348026L;
	private int codCentro;
	private String nombreCentro;
	private float latitud;
	private float longitud;
	private Collection<LineaRopa> lineasRopa;
	private Collection<CategoriaHogar> categoriasHogar;
	
	public CentroDistribucion() {
		this.lineasRopa = new ArrayList<LineaRopa>();
		this.categoriasHogar = new ArrayList<CategoriaHogar>();
	}

	public CentroDistribucion(int codigo, String nombre, float longitud,
			float latitud, Collection<LineaRopa> lineasRopa,
			Collection<CategoriaHogar> categoriasHogar) {
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
	public int getCodCentro() {
		return codCentro;
	}

	public void setCodCentro(int codCentro) {
		this.codCentro = codCentro;
	}

	@Column
	public String getNombreCentro() {
		return nombreCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	@Column
	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	@Column
	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public Collection<LineaRopa> getLineasRopa() {
		return this.lineasRopa;
	}

	public void setLineasRopa(Collection<LineaRopa> liRopa) {
		// int numerador = 1;
		/*
		 * Iterator it = liRopa.iterator();
		 * 
		 * while(it.hasNext()) this.lineasRopa.add((LineaRopa)it.next());
		 * 
		 * /*for(int i = 0;i< liRopa.size();i++) this.lineasRopa.add(new
		 * LineaRopa((i+1),liRopa.elementAt(i)));
		 */

		this.lineasRopa = liRopa;
	}

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public Collection<CategoriaHogar> getCategoriasHogar() {
		return this.categoriasHogar;
	}

	public void setCategoriasHogar(Collection<CategoriaHogar> catHogar) {
		// int numerador = 1;
		/*
		 * Iterator it = catHogar.iterator();
		 * 
		 * while(it.hasNext())
		 * this.categoriasHogar.add((CategoriaHogar)it.next());
		 * 
		 * /*for(int i = 0; i< catHogar.size();i++)
		 * this.categoriasHogar.add(new
		 * CategoriaHogar((i+1),catHogar.elementAt(i)));
		 */

		this.categoriasHogar = catHogar;

	}

	@Transient
	public CentroDistribucionVO getVO() {
		CentroDistribucionVO cen = new CentroDistribucionVO();
		cen.setCodCentro(this.getCodCentro());
		cen.setLatitud(this.getLatitud());
		cen.setLongitud(this.getLongitud());
		cen.setNombreCentro(this.getNombreCentro());
		Collection<CategoriaHogarVO> cats = new ArrayList<CategoriaHogarVO>();
		Iterator it = (Iterator) this.getCategoriasHogar().iterator();
		while(it.hasNext()){
			CategoriaHogarVO cat = ((CategoriaHogar)it.next()).getVO();
			cats.add(cat);
		}
		cen.setCategoriasHogar(cats);
		
		Collection<LineaRopaVO> lins = new ArrayList<LineaRopaVO>();
		Iterator it2 = (Iterator) this.getLineasRopa().iterator();
		while(it2.hasNext()){
			LineaRopaVO lin = ((LineaRopa)it2.next()).getVO();
			lins.add(lin);
		}
		cen.setLineasRopa(lins);
		return cen;
	}

	public void setVO(CentroDistribucionVO centro) {
		this.setCodCentro(centro.getCodCentro());
		this.setLatitud(centro.getLatitud());
		this.setLongitud(centro.getLongitud());
		this.setNombreCentro(centro.getNombreCentro());
		
		Collection<CategoriaHogar> cats = new ArrayList<CategoriaHogar>();
		Iterator it = (Iterator) this.getCategoriasHogar().iterator();
		while(it.hasNext()){
			CategoriaHogar cat = new CategoriaHogar();
			cat.setVO((CategoriaHogarVO)it.next());
			cats.add(cat);
		}
		this.setCategoriasHogar(cats);
		
		Collection<LineaRopa> lins = new ArrayList<LineaRopa>();
		Iterator it2 = (Iterator) this.getLineasRopa().iterator();
		while(it2.hasNext()){
			LineaRopa lin = new LineaRopa();
			lin.setVO(((LineaRopaVO)it.next()));
			lins.add(lin);
		}
		this.setLineasRopa(lins);
		
	}

}

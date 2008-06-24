package BusinessLogic;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import VO.ArticuloAFabricarVO;
import VO.SolicitudFabricaVO;

@Entity
@DiscriminatorValue("fabricacion")
public class SolicitudDeFabricacion extends Solicitud
{
	private static final long serialVersionUID = 4970004249380972083L;
	private Fabrica fabrica;
	private Collection<ArticuloAFabricar> articulosAFabricar;
	private int idFab;
	
	public SolicitudDeFabricacion() {
		super();
		this.articulosAFabricar = new Vector<ArticuloAFabricar>();
	}
	
	public SolicitudDeFabricacion(int id,int n, Collection<ArticuloAFabricar> a, Date f, Fabrica fa)
	{
		super(id,f);
		this.idFab = n;
		this.fabrica = fa;
		this.setArticulosAFabricar(a);
	}
	
	@Column
	public int getIdFab() {
		return idFab;
	}

	public void setIdFab(int idFab) {
		this.idFab = idFab;
	}
	
	@ManyToOne
	public Fabrica getFabrica() {
		return fabrica;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	@OneToMany(cascade={CascadeType.MERGE})
	public Collection<ArticuloAFabricar> getArticulosAFabricar() {
		return articulosAFabricar;
	}

	public void setArticulosAFabricar(Collection<ArticuloAFabricar> articulosAFabricar) {
		this.articulosAFabricar = articulosAFabricar;
	}
	
	@Transient
	public SolicitudFabricaVO getVO()
	{
		SolicitudFabricaVO vo = new SolicitudFabricaVO();
		vo.setIdFab(this.getIdFab());
		vo.setFabrica(this.getFabrica().getVO());
		vo.setFechaEmision(this.getFechaHoraFromString(this.getFechaEmision()));
		vo.setCdVO(this.getCentro().getVO());
		vo.setId(this.getId());
		Iterator it = (Iterator) this.getArticulosAFabricar().iterator();
		Collection<ArticuloAFabricarVO> arts = new ArrayList<ArticuloAFabricarVO>();
		while(it.hasNext()){
			ArticuloAFabricar art = ((ArticuloAFabricar)it.next());
			arts.add(art.getVO());
		}
		vo.setArticulosAFabricar(arts);
		return vo;
	}

	public void setVO(SolicitudFabricaVO vo)
	{
		Collection<ArticuloAFabricar> recibidos = new ArrayList<ArticuloAFabricar>();
		Iterator rec = (Iterator) vo.getArticulosAFabricar().iterator();
		while(rec.hasNext()){
			ArticuloAFabricarVO art = (ArticuloAFabricarVO)rec.next();
			ArticuloAFabricar arti = new ArticuloAFabricar();
			arti.setVO(art);
			recibidos.add(arti);
		}
				
		
		CentroDistribucion cent = new CentroDistribucion();
		cent.setVO(vo.getCdVO());
		this.setCentro(cent);
		this.setIdFab(vo.getIdFab());
		this.setFechaEmision(this.getFechaHoraFromDate(vo.getFechaEmision()));
		Fabrica fab = new Fabrica();
		fab.setVO(vo.getFabrica());
		this.setFabrica(fab);
		this.setId(vo.getId());
		this.setArticulosAFabricar(recibidos);
		
	}
	
	@Transient
	private String getFecha(String f)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<f.indexOf(" ");i++)
			sb.append(f.charAt(i));
		
		return sb.toString();
	}
	
	@Transient
	private String getHora(String f)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = f.indexOf(" "); i<f.length();i++)
			sb.append(f.charAt(i));
		
		return sb.toString();
	}
	
	@Transient
	private String getFechaHoraFromDate(Date d)
	{
		String fecha;
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
		DateFormat df1 = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());
		fecha = df.format(d ) + " " +df1.format(d );
		return fecha;
	}
	
	@SuppressWarnings("deprecation")
	@Transient
	private Date getFechaHoraFromString(String f)
	{
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
		DateFormat df1 = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());
		
		Date fn;
		Date fn2;
		try 
		{
			fn = df.parse(this.getFecha(f));
			fn2 = df1.parse(this.getHora(f));	
			fn.setHours(fn2.getHours());
			fn.setMinutes(fn2.getMinutes());
			fn.setSeconds(fn2.getSeconds());
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			return null;
		}
		return fn;
	}
}

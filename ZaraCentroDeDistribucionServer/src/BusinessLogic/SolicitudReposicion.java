package BusinessLogic;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import VO.ArticuloAReponerVO;
import VO.SolicitudDeReposicionVO;

@Entity
@DiscriminatorValue("reposicion")
public class SolicitudReposicion extends Solicitud
{
	
	private static final long serialVersionUID = -3577225904639518643L;
	private Fabrica fabrica;
	private SolicitudDeFabricacion solFab;
	private Collection<ArticuloAReponer> articulosAReponer;
	private int idRep;



	public SolicitudReposicion() 
	{
		super();
	}
	
	public SolicitudReposicion(int id,int n, Collection<ArticuloAReponer> a, Date f, Fabrica fa,SolicitudDeFabricacion sol){
		super(id,f);
		this.solFab = sol;
		this.idRep = n;
		this.fabrica = fa;
		this.setArticulosAReponer(a);
	}
	

	@Column
	public int getIdRep() {
		return idRep;
	}

	public void setIdRep(int idRep) {
		this.idRep = idRep;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE})
	public Fabrica getFabrica() 
	{
		return fabrica;
	}
	
	public void setFabrica(Fabrica fabrica)
	{
		this.fabrica = fabrica;
	}
	
	@OneToMany(cascade={CascadeType.MERGE})
	public Collection<ArticuloAReponer> getArticulosAReponer() {
		return articulosAReponer;
	}

	public void setArticulosAReponer(Collection<ArticuloAReponer> articulosAReponer) {
		this.articulosAReponer = articulosAReponer;
	}

	@ManyToOne(cascade={CascadeType.MERGE})
	public SolicitudDeFabricacion getSolFab() {
		return solFab;
	}

	public void setSolFab(SolicitudDeFabricacion solFab) {
		this.solFab = solFab;
	}
	
	@Transient
	public SolicitudDeReposicionVO getVO()
	{
		SolicitudDeReposicionVO sol = new SolicitudDeReposicionVO();
		sol.setIdRep(this.getIdRep());
		sol.setFechaEmision(this.getFechaHoraFromString(this.getFechaEmision()));
		sol.setFabrica(this.getFabrica().getVO());
		sol.setSolFab(this.getSolFab().getVO());
		sol.setCdVO(this.getCentro().getVO());
		sol.setId(this.getId());
		Collection<ArticuloAReponerVO> arts = new ArrayList<ArticuloAReponerVO>();
		Iterator it = (Iterator) this.getArticulosAReponer().iterator();
		while(it.hasNext()){
			ArticuloAReponerVO art = ((ArticuloAReponer)it.next()).getVO();
			arts.add(art);
		}
		sol.setArticulosAReponer(arts);
		return sol;
	}

	public void setVO(SolicitudDeReposicionVO vo)
	{
		this.setIdRep(vo.getIdRep());
		this.setFechaEmision(this.getFechaHoraFromDate(vo.getFechaEmision()));
		Fabrica fab = new Fabrica();
		fab.setVO(vo.getFabrica());
		this.setFabrica(fab);
		CentroDistribucion centro = new CentroDistribucion();
		centro.setVO(vo.getCdVO());
		this.setCentro(centro);
		SolicitudDeFabricacion solFab = new SolicitudDeFabricacion();
		solFab.setVO(vo.getSolFab());
		this.setSolFab(solFab);
		this.setId(vo.getId());
		Collection<ArticuloAReponer> arts = new ArrayList<ArticuloAReponer>();
		Iterator it = (Iterator) vo.getArticulosAReponer().iterator();
		while(it.hasNext()){
			ArticuloAReponer art = new ArticuloAReponer();
			art.setVO(((ArticuloAReponerVO)it.next()));
			arts.add(art);
		}
		this.setArticulosAReponer(arts);
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

package businesslogic;

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

import vo.ArticuloAReponerVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudFabricaVO;

@Entity
@DiscriminatorValue("reposicion")
public class SolicitudReposicion extends Solicitud
{
	
	private static final long serialVersionUID = -3577225904639518643L;
	private Fabrica fabrica;
	private Collection<SolicitudDeFabricacion> solsFab;
	private Collection<ArticuloAReponer> articulosAReponer;
	private int idRep;
	private boolean procesada;



	public SolicitudReposicion() 
	{
		super();
	}
	
	public SolicitudReposicion(int id,int n, Collection<ArticuloAReponer> a, Date f, Fabrica fa,Collection<SolicitudDeFabricacion> sol){
		super(id,f);
		this.solsFab = sol;
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

	@OneToMany(cascade={CascadeType.MERGE})
	public Collection<SolicitudDeFabricacion> getSolsFab() {
		return solsFab;
	}

	public void setSolsFab(Collection<SolicitudDeFabricacion> solFab) {
		this.solsFab = solFab;
	}
	
	public void setProcesada(boolean procesada) {
		this.procesada = procesada;
	}

	@Column
	public boolean getProcesada() {
		return procesada;
	}
	
	@Transient
	public SolicitudDeReposicionVO getVO()
	{
		SolicitudDeReposicionVO sol = new SolicitudDeReposicionVO();
		sol.setIdRep(this.getIdRep());
		sol.setFechaEmision(this.getFechaHoraFromString(this.getFechaEmision()));
		sol.setFabrica(this.getFabrica().getVO());
		sol.setSolFab(this.getSolsFab().getVO());
		sol.setCdVO(this.getCentro().getVO());
		sol.setId(this.getId());
		Collection<ArticuloAReponerVO> arts = new ArrayList<ArticuloAReponerVO>();
		Iterator it = (Iterator) this.getArticulosAReponer().iterator();
		while(it.hasNext()){
			ArticuloAReponerVO art = ((ArticuloAReponer)it.next()).getVO();
			arts.add(art);
		}
		sol.setArticulosAReponer(arts);
		sol.setProcesada(this.getProcesada());
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
		Collection<SolicitudDeFabricacion> arts = new ArrayList<SolicitudDeFabricacion>();
		Iterator it1 = (Iterator) vo.getSolFab().iterator();
		while(it1.hasNext()){
			SolicitudDeFabricacion sol = new SolicitudDeFabricacion();
			sol.setVO(((SolicitudFabricaVO)it1.next()));
			arts.add(sol);
		}
		this.setSolsFab(arts);
		this.setId(vo.getId());
		Collection<ArticuloAReponer> arts1 = new ArrayList<ArticuloAReponer>();
		Iterator it = (Iterator) vo.getArticulosAReponer().iterator();
		while(it.hasNext()){
			ArticuloAReponer art = new ArticuloAReponer();
			art.setVO(((ArticuloAReponerVO)it.next()));
			arts1.add(art);
		}
		this.setArticulosAReponer(arts1);
		this.setProcesada(vo.getProcesada());
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

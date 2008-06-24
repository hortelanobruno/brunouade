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
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import VO.ArticuloAEnviarVO;
import VO.SolicitudEnvioVO;

@Entity
@DiscriminatorValue("envio")
public class SolicitudEnvioATienda extends Solicitud
{
	private static final long serialVersionUID = 5001930866418434765L;
	private Tienda tienda;
	private Collection<ArticuloAEnviar> articulosAEnviar;
	private int idEnv;
	


	public SolicitudEnvioATienda() {
		super();
	}
	
	public SolicitudEnvioATienda(int n, Collection<ArticuloAEnviar> a, Date f, Tienda t){
		super(f);
		this.idEnv = n;
		this.tienda = t;
		this.setArticulosAEnviar(a);
	}
	
	@Id
	@Column
	public int getIdEnv() {
		return idEnv;
	}

	public void setIdEnv(int idEnv) {
		this.idEnv = idEnv;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE})
	public Tienda getTienda() 
	{
		return tienda;
	}

	public void setTienda(Tienda tienda) 
	{
		this.tienda = tienda;
	}
	
	@OneToMany(cascade={CascadeType.MERGE})
	public Collection<ArticuloAEnviar> getArticulosAEnviar() {
		return articulosAEnviar;
	}

	public void setArticulosAEnviar(Collection<ArticuloAEnviar> articulosAEnviar) {
		this.articulosAEnviar = articulosAEnviar;
	}
	
	@Transient
	public SolicitudEnvioVO getVO(){
		SolicitudEnvioVO sol = new SolicitudEnvioVO();
		sol.setIdEnv(this.getIdEnv());
		sol.setFechaEmision(this.getFechaHoraFromString(this.getFechaEmision()));
		sol.setTienda(this.getTienda().getVO());
		sol.setCdVO(this.getCentro().getVO());
		Collection<ArticuloAEnviarVO> arts = new ArrayList<ArticuloAEnviarVO>();
		Iterator it = (Iterator) this.getArticulosAEnviar().iterator();
		while(it.hasNext()){
			ArticuloAEnviarVO art = ((ArticuloAEnviar)it.next()).getVO();
			arts.add(art);
		}
		sol.setArticulosAEnviar(arts);
		return sol;
	}

	public void setVO(SolicitudEnvioVO vo){
		this.setFechaEmision(this.getFechaHoraFromDate(vo.getFechaEmision()));
		this.setIdEnv(vo.getIdEnv());
		Tienda tienda = new Tienda();
		tienda.setVO(vo.getTienda());
		this.setTienda(tienda);
		CentroDistribucion centro = new CentroDistribucion();
		centro.setVO(vo.getCdVO());
		this.setCentro(centro);
		Collection<ArticuloAEnviar> arts = new ArrayList<ArticuloAEnviar>();
		Iterator it = (Iterator) vo.getArticulosAEnviar().iterator();
		while(it.hasNext()){
			ArticuloAEnviar art = new ArticuloAEnviar();
			art.setVO(((ArticuloAEnviarVO)it.next()));
			arts.add(art);
		}
		this.setArticulosAEnviar(arts);
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

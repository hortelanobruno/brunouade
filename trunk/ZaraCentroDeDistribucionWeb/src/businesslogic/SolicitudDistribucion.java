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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import vo.ArticuloPedidoVO;
import vo.SolicitudDistribucionVO;

@Entity
@DiscriminatorValue("distribucion")
public class SolicitudDistribucion extends Solicitud
{
	private static final long serialVersionUID = 1694955054674640622L;
	private Collection<ArticuloPedido> articulosPedidos;
	private boolean cerrada;
	private int idDis;


	public SolicitudDistribucion() {
		super();
		this.setCerrada(false);
	}

	public SolicitudDistribucion(int id,int n, Date f, Collection<ArticuloPedido> a) {
		super(id,f);
		this.idDis = n;
		this.setArticulosPedidos(a);
		this.setCerrada(false);
	}


	@Column
	public int getIdDis() {
		return idDis;
	}

	public void setIdDis(int idDis) {
		this.idDis = idDis;
	}

	@OneToMany(cascade={CascadeType.MERGE})
	public Collection<ArticuloPedido> getArticulosPedidos() {
		return articulosPedidos;
	}

	public void setArticulosPedidos(Collection<ArticuloPedido> articulosPedidos) {
		this.articulosPedidos = articulosPedidos;
	}
	
	@Column
	public boolean getCerrada() {
		return cerrada;
	}

	public void setCerrada(boolean cerrada) {
		this.cerrada = cerrada;
	}

	@Transient
	public SolicitudDistribucionVO getVO() {
		SolicitudDistribucionVO sol = new SolicitudDistribucionVO();
		sol.setIdDis(this.getIdDis());
		sol.setFechaEmision(this.getFechaHoraFromString(this.getFechaEmision()));
		sol.setCdVO(this.getCentro().getVO());
		sol.setId(this.getId());
		Collection<ArticuloPedidoVO> arts = new ArrayList<ArticuloPedidoVO>();
		Iterator it = (Iterator) this.getArticulosPedidos().iterator();
		while(it.hasNext()){
			ArticuloPedidoVO art = ((ArticuloPedido)it.next()).getVO();
			arts.add(art);
		}
		sol.setArticulosPedidos(arts);
		return sol;
	}

	public void setVO(SolicitudDistribucionVO vo) {
		this.setFechaEmision(this.getFechaHoraFromDate(vo.getFechaEmision()));
		this.setIdDis(vo.getIdDis());
		CentroDistribucion centro = new CentroDistribucion();
		centro.setVO(vo.getCdVO());
		this.setCentro(centro);
		this.setCerrada(vo.getCerrada());
		Collection<ArticuloPedido> arts = new ArrayList<ArticuloPedido>();
		Iterator it = (Iterator) vo.getArticulosPedidos().iterator();
		while(it.hasNext()){
			ArticuloPedido art = new ArticuloPedido();
			ArticuloPedidoVO artVO = ((ArticuloPedidoVO)it.next());
			art.setVO(artVO);
			arts.add(art);
		}
		this.setArticulosPedidos(arts);
		this.setId(vo.getId());
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

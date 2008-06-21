package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import BusinessLogic.SolicitudDeFabricacion;
import BusinessLogic.SolicitudDistribucion;
import BusinessLogic.SolicitudEnvioATienda;
import BusinessLogic.SolicitudReposicion;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;

@Stateless
public class AdministradorSolicitudesBean implements AdministradorSolicitudes
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	private EntityManager em;
	
	public void guardarSolFab(SolicitudFabricaVO solFab) 
	{
		SolicitudDeFabricacion s = em.find(SolicitudDeFabricacion.class, solFab.getNumero());
		if(s == null)
		{
			s = new SolicitudDeFabricacion();
			s.setVO(solFab);
			em.merge(s);
		}
	}

	public void actualizarSolFab(SolicitudFabricaVO solFabVO) {
		SolicitudDeFabricacion s = em.find(SolicitudDeFabricacion.class, solFabVO.getNumero());
		if(s != null)
		{
			s = new SolicitudDeFabricacion();
			s.setVO(solFabVO);
			em.merge(s);
		}
	}


	public ArrayList<SolicitudFabricaVO> getAllSolFab() 
	{
		Query q = em.createQuery("SELECT s FROM SolicitudDeFabricacion s");
		List l = q.getResultList();
		ArrayList<SolicitudFabricaVO> ret = new ArrayList<SolicitudFabricaVO>();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			SolicitudDeFabricacion aux = (SolicitudDeFabricacion)it.next();
			ret.add(aux.getVO());
		}
		return ret;
	}

	public SolicitudFabricaVO getSolFab(int codigo)
	{
		SolicitudDeFabricacion sf = em.find(SolicitudDeFabricacion.class, codigo);
		return sf.getVO();
	}

	public ArrayList<SolicitudFabricaVO> getSolsFab(int codTienda) 
	{
		
		return null;
	}

	public void guardarSolicitudDistribucion(SolicitudDistribucionVO soldist) 
	{
		SolicitudDistribucion sd = new SolicitudDistribucion();
		sd.setVO(soldist);
		em.merge(sd);
	}

	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv)
	{
		SolicitudEnvioATienda set = new SolicitudEnvioATienda();
		set.setVO(solEnv);
		em.persist(set);
	}

	public void guardarSolicitudFabricacion(SolicitudFabricaVO solFab) 
	{
		SolicitudDeFabricacion sf = new SolicitudDeFabricacion();
		sf.setVO(solFab);
		em.merge(sf);
	}

	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO) 
	{
		SolicitudReposicion sr = new SolicitudReposicion();
		sr.setVO(solRepVO);
		em.merge(sr);
	}

	public int getNumeroSolEnv() 
	{
		Query q = em.createQuery("SELECT MAX(s.numero) FROM SolicitudEnvioATienda s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 0;
		}else{
			int a = (Integer) l.get(0);
			return a;
		}
	}

	public int getNumeroSolFab() 
	{
		Query q = em.createQuery("SELECT MAX(s.numero) FROM SolicitudDeFabricacion s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 0;
		}else{
			int a = (Integer) l.get(0);
			return a;
		}
	}

	public int getNextIdArticuloReservado() {
		Query q = em.createQuery("SELECT MAX(s.idAR) FROM ArticuloReservado s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 0;
		}else{
			int a = (Integer) l.get(0);
			return a;
		}
	}
	
	public int getNextIdArticuloPedido()
	{
		Query q = em.createQuery("SELECT MAX(s.idAP) FROM ArticuloPedido s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 0;
		}else{
			int a = (Integer) l.get(0);
			return a;
		}
	}
	
	public int getNextIdArticuloAReponer() {
		Query q = em.createQuery("SELECT MAX(s.idAAR) FROM ArticuloAReponer s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 0;
		}else{
			int a = (Integer) l.get(0);
			return a;
		}
	}
	
	public int getNextIdArticuloAEnviar()
	{
		Query q = em.createQuery("SELECT MAX(s.idAAE) FROM ArticuloAEnviar s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 0;
		}else{
			int a = (Integer) l.get(0);
			return a;
		}
	}
	
	public int getNextIdArticuloAFabricar()
	{
		Query q = em.createQuery("SELECT MAX(s.id) FROM ArticuloAFabricar s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 0;
		}else{
			int a = (Integer) l.get(0);
			return a;
		}
	}

	public boolean existeSolDis(int codigo) 
	{
		return (em.find(SolicitudDistribucion.class, codigo) == null)?false:true;
	}

	public boolean existeSolRep(int codigo)
	{
		return (em.find(SolicitudReposicion.class, codigo) == null)?false:true;
	}
	
}

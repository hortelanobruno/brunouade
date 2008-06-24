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
import BusinessLogic.Tienda;
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
		SolicitudDeFabricacion s = em.find(SolicitudDeFabricacion.class, solFab.getIdFab());
		if(s == null)
		{
			s = new SolicitudDeFabricacion();
			s.setVO(solFab);
			em.merge(s);
		}
	}

	public void actualizarSolFab(SolicitudFabricaVO solFabVO) {
		SolicitudDeFabricacion s = em.find(SolicitudDeFabricacion.class, solFabVO.getIdFab());
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
		Query q = em.createQuery("SELECT s FROM SolicitudDeFabricacion s");
		List l = q.getResultList();
		SolicitudFabricaVO ret = new SolicitudFabricaVO();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			SolicitudDeFabricacion sol = (SolicitudDeFabricacion)it.next();
			if(sol.getIdFab() == codigo){
				return ret = sol.getVO();
			}
		}
		return ret;
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
		Query q = em.createQuery("SELECT MAX(s.idEnv) FROM SolicitudEnvioATienda s");
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
		Query q = em.createQuery("SELECT MAX(s.idFab) FROM SolicitudDeFabricacion s");
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
		Query q = em.createQuery("SELECT s FROM SolicitudDistribucion s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			SolicitudDistribucion sol = (SolicitudDistribucion)it.next();
			if(sol.getIdDis() == codigo){
				return true;
			}
		}
		return false;
	}

	public boolean existeSolRep(int codigo)
	{
		Query q = em.createQuery("SELECT s FROM SolicitudReposicion s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			SolicitudReposicion sol = (SolicitudReposicion)it.next();
			if(sol.getIdRep() == codigo){
				return true;
			}
		}
		return false;
	}

	public ArrayList<SolicitudDistribucionVO> getSolsDis(String tien) 
	{
		Query q = em.createQuery("SELECT t FROM Tienda t");
		List l = q.getResultList();
		ArrayList<SolicitudDistribucionVO> ret = new ArrayList<SolicitudDistribucionVO>();
		if(l == null) return null;
		else
		{
			Iterator tiendas = l.iterator();
			int cod = 0;
			while(tiendas.hasNext()){
				Tienda tiendaa = (Tienda) tiendas.next();
				if(tiendaa.getNombreTienda().equals(tien)){
					cod = tiendaa.getCodigoTienda();
				}
			}
			q = em.createQuery("SELECT s FROM SolicitudDistribucion s WHERE s.tienda.codigoTienda =:cod AND s.cerrada =:state");
			q.setParameter("cod", cod);
			q.setParameter("state", false);
			List lVO = q.getResultList();
			Iterator i = lVO.iterator();
			
			while(i.hasNext())
			{
				SolicitudDistribucion sol = (SolicitudDistribucion)i.next();
				ret.add(sol.getVO());
			}
		}
		return ret;
	}

	public void guardarSolEnv(SolicitudEnvioVO solEnvio)
	{
		Query q = em.createQuery("SELECT s FROM SolicitudEnvioATienda s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		int aux = 0;
		SolicitudEnvioATienda sol = new SolicitudEnvioATienda();
		while(it.hasNext())
		{
			sol = (SolicitudEnvioATienda)it.next();
			if(sol.getIdEnv() == solEnvio.getIdEnv()){
				aux++;
			}
		}
		if(aux == 0){
			sol.setVO(solEnvio);
			em.merge(sol);
		}
	}

	public void actualizarSolDis(SolicitudDistribucionVO solDis) 
	{
		Query q = em.createQuery("SELECT s FROM SolicitudDistribucion s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			SolicitudDistribucion sol = (SolicitudDistribucion)it.next();
			if(sol.getIdDis() == solDis.getIdDis()){
				sol.setVO(solDis);
				em.merge(sol);
			}
		}
	}

	public int getNextId() {
		Query q = em.createQuery("SELECT MAX(s.id) FROM Solicitud s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 0;
		}else{
			int a = (Integer) l.get(0);
			return a;
		}
	}
}
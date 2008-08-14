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
		Query q = em.createQuery("SELECT s FROM SolicitudDeFabricacion s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		int aux = 0;
		SolicitudDeFabricacion sol = new SolicitudDeFabricacion();
		while(it.hasNext())
		{
			sol = (SolicitudDeFabricacion)it.next();
			if(sol.getIdFab() == solFab.getIdFab()){
				aux++;
			}
		}
		if(aux == 0){
			sol = new SolicitudDeFabricacion();
			sol.setVO(solFab);
			em.merge(sol);
		}
	}

	public void actualizarSolFab(SolicitudFabricaVO solFabVO) {
		Query q = em.createQuery("SELECT s FROM SolicitudDeFabricacion s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			SolicitudDeFabricacion aux = (SolicitudDeFabricacion)it.next();
			if(aux.getIdFab() == solFabVO.getIdFab()){
				aux.setVO(solFabVO);
				em.merge(aux);
			}
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
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
		}
	}

	public int getNumeroSolFab() 
	{
		Query q = em.createQuery("SELECT MAX(s.idFab) FROM SolicitudDeFabricacion s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
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
			sol = new SolicitudEnvioATienda();
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
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
		}
	}

	public boolean existeSolFab(int numSolFab) {
		Query q = em.createQuery("SELECT s FROM SolicitudDeFabricacion s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			SolicitudDeFabricacion sol = (SolicitudDeFabricacion)it.next();
			if(sol.getIdFab() == numSolFab){
				return true;
			}
		}
		return false;
	}
}
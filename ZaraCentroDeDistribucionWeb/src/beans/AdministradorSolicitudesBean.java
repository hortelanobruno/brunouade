package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import vo.ArticuloAFabricarVO;
import vo.SolicitudDeReposicionVO;
import vo.SolicitudDistribucionVO;
import vo.SolicitudEnvioVO;
import vo.SolicitudFabricaVO;
import businesslogic.SolicitudDeFabricacion;
import businesslogic.SolicitudDistribucion;
import businesslogic.SolicitudEnvioATienda;
import businesslogic.SolicitudReposicion;

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

	public ArrayList<SolicitudDistribucionVO> getAllSolicitudesDistribucion() {
		Query q = em.createQuery("SELECT s FROM SolicitudDistribucion s WHERE s.cerrada =:state");
		q.setParameter("state", false);
		List lVO = q.getResultList();
		Iterator i = lVO.iterator();
		ArrayList<SolicitudDistribucionVO> ret = new ArrayList<SolicitudDistribucionVO>();
		while(i.hasNext())
		{
			SolicitudDistribucion sol = (SolicitudDistribucion)i.next();
			ret.add(sol.getVO());
		}
		return ret;
	}
	
	public SolicitudDistribucionVO obtenerSolicitudDistribucion(int codSolDis) {
		Query q = em.createQuery("SELECT s FROM SolicitudDistribucion s WHERE s.idDis =:cod");
		q.setParameter("cod", codSolDis);
		List lVO = q.getResultList();
		Iterator i = lVO.iterator();
		SolicitudDistribucion sol = (SolicitudDistribucion)i.next();
		return sol.getVO();
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

	public int getNextIdSolDis() {
		Query q = em.createQuery("SELECT MAX(s.idDis) FROM SolicitudDistribucion s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
		}
	}

	public int getNexIdSolRep() {
		Query q = em.createQuery("SELECT MAX(s.idRep) FROM SolicitudReposicion s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
		}
	}
	
	public int getNextIdSolEnv() {
		Query q = em.createQuery("SELECT MAX(s.idEnv) FROM SolicitudEnvioATienda s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
		}
	}

	public List<SolicitudDeReposicionVO> obtenerSolicitudesDeReposicionAProcesar() {
		List<SolicitudDeReposicionVO> solicitudes = new ArrayList<SolicitudDeReposicionVO>();
		Query q = em.createQuery("SELECT s FROM SolicitudReposicion s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			SolicitudReposicion sol = (SolicitudReposicion)it.next();
			if(sol.getProcesada() == false){
				solicitudes.add(sol.getVO());
			}
		}
		return solicitudes;
	}

	public List<SolicitudDistribucionVO> obtenerSolDisAbiertas() {
		List<SolicitudDistribucionVO> sols = new ArrayList<SolicitudDistribucionVO>();
		Query q = em.createQuery("SELECT s FROM SolicitudDistribucion s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			SolicitudDistribucion sol = (SolicitudDistribucion)it.next();
			if(sol.getCerrada() == false){
				sols.add(sol.getVO());
			}
		}
		return sols;
	}

	public List<SolicitudFabricaVO> getSolicitudesDeFabricacionAbiertas() {
		Query q = em.createQuery("SELECT s FROM SolicitudDeFabricacion s");
		List l = q.getResultList();
		Iterator it = l.iterator();
		List<SolicitudFabricaVO> sols = new ArrayList<SolicitudFabricaVO>();
		while(it.hasNext())
		{
			SolicitudDeFabricacion sol = (SolicitudDeFabricacion)it.next();
			if(sol.getCerrada()==false){
				sols.add(sol.getVO());
			}
		}
		return sols;
	}

	

	
}
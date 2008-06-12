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
import VO.FabricaVO;
import VO.SolicitudDeReposicionVO;
import VO.SolicitudDistribucionVO;
import VO.SolicitudEnvioVO;
import VO.SolicitudFabricaVO;

@Stateless
public class AdministradorSolicitudesBean implements AdministradorSolicitudes
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	private EntityManager em;
	
	public void actualizarSolFab(SolicitudFabricaVO solFab) 
	{
		SolicitudDeFabricacion s = em.find(SolicitudDeFabricacion.class, solFab.getNumero());
		if(s != null)
		{
			s.setVO(solFab);
			em.merge(s);
		}
	}

	public SolicitudFabricaVO cargarSolicitudFabricacion(long codigoSolFab)
	{
	
		return null;
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

	public SolicitudFabricaVO getSolFab(long codigo)
	{
		Query q = em.createQuery("SELECT s FROM SolicitudDeFabricacion s WHERE s.numero =: codigo");
		q.setParameter("codigo", codigo);
		List l = q.getResultList();
		Iterator it = l.iterator();
		SolicitudDeFabricacion aux = (SolicitudDeFabricacion)it.next();
		return aux.getVO();
	}

	public ArrayList<SolicitudFabricaVO> getSolsFab(int codTienda) 
	{
		
		return null;
	}

	public void guardarSolicitud(SolicitudDistribucionVO soldist) 
	{
		SolicitudDistribucion sd = new SolicitudDistribucion();
		sd.setVO(soldist);
		/*Tienda tien = new Tienda();
		tien.setVO(soldist.getTienda());
		em.persist(tien);*/
		em.persist(sd);
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
		em.persist(sf);
	}

	public void guardarSolicitudReposicion(SolicitudDeReposicionVO solRepVO) 
	{
		SolicitudReposicion sr = new SolicitudReposicion();
		sr.setVO(solRepVO);
		em.persist(sr);
	}

	public FabricaVO getFabrica()
	{
		return null;
	}

	public int getNumeroSolEnv() 
	{
		Query q = em.createQuery("SELECT MAX(s.numero) FROM SolicitudEnvioATienda s");
		List l = q.getResultList();
		return (((Integer)l.get(0)) + 1);
	}

	public int getNumeroSolFab() 
	{
		Query q = em.createQuery("SELECT MAX(s.numero) FROM SolicitudDeFabricacion s");
		List l = q.getResultList();
		return (((Integer)l.get(0)) + 1);
	}
}

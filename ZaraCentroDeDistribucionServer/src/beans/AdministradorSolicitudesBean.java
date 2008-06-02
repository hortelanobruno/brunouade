package beans;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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
import beans.AdministradorSolicitudes;

@Stateless
public class AdministradorSolicitudesBean implements AdministradorSolicitudes
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	private EntityManager em;
	
	public void actualizarSolFab(SolicitudFabricaVO solFab) 
	{
		
	}

	public SolicitudFabricaVO cargarSolicitudFabricacion(long codigoSolFab) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<SolicitudFabricaVO> getAllSolFab() 
	{
		Query q = em.createQuery("select s from SolicitudDeFabricacion");
		List l = q.getResultList();
		Vector<SolicitudFabricaVO> ret = new Vector<SolicitudFabricaVO>();
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
		Query q = em.createQuery("select s from SolicitudDeFabricacion where a.numero =: codigo");
		q.setParameter("codigo", codigo);
		List l = q.getResultList();
		Iterator it = l.iterator();
		SolicitudDeFabricacion aux = (SolicitudDeFabricacion)it.next();
		return aux.getVO();
	}

	public Vector<SolicitudFabricaVO> getSolsFab(int codTienda) {
		// TODO Auto-generated method stub
		return null;
	}

	public void guardarSolicitud(SolicitudDistribucionVO soldist) 
	{
		SolicitudDistribucion sd = new SolicitudDistribucion();
		sd.setVO(soldist);
		em.merge(sd);
	}

	public void guardarSolicitudDeEnvio(SolicitudEnvioVO solEnv)
	{
		SolicitudEnvioATienda set = new SolicitudEnvioATienda();
		set.setVO(solEnv);
		em.merge(set);
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
}

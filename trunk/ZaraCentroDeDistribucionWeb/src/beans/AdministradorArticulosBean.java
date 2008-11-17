package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import vo.ArticuloAEnviarVO;
import vo.ArticuloAFabricarVO;
import vo.ArticuloAReponerVO;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloRopaVO;
import businesslogic.Articulo;
import businesslogic.ArticuloAEnviar;
import businesslogic.ArticuloAFabricar;
import businesslogic.ArticuloHogar;
import businesslogic.ArticuloRopa;
import exceptions.ExistingProductException;

@Stateless
public class AdministradorArticulosBean implements AdministradorArticulos 
{
	@PersistenceContext(unitName="CentroDeDistribucion")
	private EntityManager em;

	public AdministradorArticulosBean() 
	{

	}
	
	public void actualizarStock(List<ArticuloAReponerVO> arts) 
	{
		for(int i = 0; i < arts.size();i++)
		{
			Articulo a = em.find(Articulo.class, arts.get(i).getArt().getCodigo());
			if(a != null)
			{
				a.setCantidad(a.getCantidad()+arts.get(i).getCantidadRecibida());
				em.merge(a);
			}
		}
	}

	public void guardarArticuloHogar(ArticuloHogarVO art) throws ExistingProductException
	{
		ArticuloHogar ah = em.find(ArticuloHogar.class, art.getCodigo());
		
		if(ah == null)
		{
			ah = new ArticuloHogar();
			ah.setArticuloHogarVO(art);
			em.persist(ah);
		}
		else throw new ExistingProductException("El producto ya existe.");
	}

	public void guardarArticuloRopa(ArticuloRopaVO art) throws ExistingProductException 
	{
		ArticuloRopa ar = em.find(ArticuloRopa.class, art.getCodigo());
		
		if(ar == null)
		{
			ar = new ArticuloRopa();
			ar.setArticuloRopaVO(art);
			em.persist(ar);
		}
		else throw new ExistingProductException("El producto ya existe.");
	}
	
	public ArticuloHeaderVO getArticulo(long codigo)
	{
		return em.find(Articulo.class, codigo).getVO();
	}

	public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2) {
		Iterator it = artic2.iterator();
		while(it.hasNext()){
			ArticuloAEnviarVO artVO = (ArticuloAEnviarVO) it.next();
			ArticuloAEnviar art = new ArticuloAEnviar();
			art.setVO(artVO);
			em.persist(art);
		}
	}

	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic2) {
		Iterator it = artic2.iterator();
		while(it.hasNext()){
			ArticuloAFabricarVO artVO = (ArticuloAFabricarVO) it.next();
			ArticuloAFabricar art = new ArticuloAFabricar();
			art.setVO(artVO);
			em.persist(art);
		}
	}

	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar() {
		Query q = em.createQuery("SELECT a FROM ArticuloAFabricar a");
		List l = q.getResultList();
		ArrayList<ArticuloAFabricarVO> ret = new ArrayList<ArticuloAFabricarVO>();
		Iterator it = l.iterator();
		while(it.hasNext()){
			ArticuloAFabricarVO artVO = ((ArticuloAFabricar)it.next()).getVO();
			if(((artVO.getCantidadAFabricar() == 0)&&(artVO.getCantidadRecibida()==0))||(artVO.getCantidadAFabricar() != artVO.getCantidadRecibida())){
				ret.add(artVO);
			}
			
		}
		return ret;
	}

	public boolean existeArticulo(long codigo) 
	{
		return (em.find(Articulo.class, codigo) == null)?false:true;
	}

	public ArrayList<Long> existenArts(ArrayList<Long> codigos) {
		ArrayList<Long> codVer = new ArrayList<Long>();
		for(int i = 0 ; i < codigos.size() ; i++){
			Articulo ar = em.find(Articulo.class, codigos.get(i));
			if(ar == null)
			{
				codVer.add(codigos.get(i));
			}
		}
		return codVer;
	}
	
	public int getNextIdArticuloPedido()
	{
		Query q = em.createQuery("SELECT MAX(s.idAP) FROM ArticuloPedido s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
		}
	}
	
	public int getNextIdArticuloAReponer() {
		Query q = em.createQuery("SELECT MAX(s.idAAR) FROM ArticuloAReponer s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
		}
	}
	
	public int getNextIdArticuloAEnviar()
	{
		Query q = em.createQuery("SELECT MAX(s.idAAE) FROM ArticuloAEnviar s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
		}
	}
	
	public int getNextIdArticuloAFabricar()
	{
		Query q = em.createQuery("SELECT MAX(s.id) FROM ArticuloAFabricar s");
		List l = q.getResultList();
		if(l.get(0) == null){
			return 1;
		}else{
			int a = (Integer) l.get(0);
			return a+1;
		}
	}

	public HashMap<Long, Integer> getStocks() {
		HashMap<Long,Integer> stocks = new HashMap<Long,Integer>();
		Query q = em.createQuery("SELECT a FROM Articulo a");
		List l = q.getResultList();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			Articulo a = (Articulo)it.next();
			stocks.put(a.getCodigo(), a.getCantidad());
		}
		return stocks;
	}

	public void actualizarStock(HashMap<Long, Integer> stocks2) {
		List<Long> codigos = new ArrayList<Long>(stocks2.keySet());
		ArrayList<Articulo> arts = new ArrayList<Articulo>();
		for(int i=0 ; i< codigos.size() ; i++){
			Articulo art = em.find(Articulo.class, codigos.get(i));
			arts.add(art);
		}
		
		for(int i=0 ; i < arts.size() ; i++){
			arts.get(i).setCantidad(stocks2.get(arts.get(i).getCodigo()));
			em.persist(arts.get(i));
		}
	}

	public void actualizarStock(ArrayList<ArticuloAEnviarVO> articulosAEnviar) {
		for(int i=0 ; i < articulosAEnviar.size() ; i++){
			Articulo art = em.find(Articulo.class, articulosAEnviar.get(i).getArt().getCodigo());
			art.setCantidad(art.getCantidad()-articulosAEnviar.get(i).getCantidadAEnviar());
			em.persist(art);
		}
	}
	
	
}
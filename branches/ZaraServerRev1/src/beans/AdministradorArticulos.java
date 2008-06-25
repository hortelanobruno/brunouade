package beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Remote;

import Exceptions.ExistingProductException;
import VO.ArticuloAEnviarVO;
import VO.ArticuloAFabricarVO;
import VO.ArticuloAReponerVO;
import VO.ArticuloHeaderVO;
import VO.ArticuloHogarVO;
import VO.ArticuloReservadoVO;
import VO.ArticuloRopaVO;


@Remote
public interface AdministradorArticulos 
{
	
	public void guardarArticuloHogar(ArticuloHogarVO a) throws ExistingProductException;
	public void guardarArticuloRopa(ArticuloRopaVO a) throws ExistingProductException;
	public ArrayList<String> getDescripciones(ArrayList<Long> cods);
	public ArrayList<Integer> getStocks(ArrayList<Long> codigos);
	public void actualizarStock(ArrayList<ArticuloAReponerVO> arts);
	public ArticuloHeaderVO getArticulo(long codigo);
	public void guardarArticulosAFabricar(Collection<ArticuloAFabricarVO> artic2);
	public void guardarArticulosAEnviar(Collection<ArticuloAEnviarVO> artic2);
	public void modificarStock(Collection<ArticuloReservadoVO> artiAEnv);
	public ArrayList<ArticuloAFabricarVO> getArticulosAFabricar();
	public boolean existeArticulo(long codigo);
	public void guardarArticulosReservados(Collection<ArticuloReservadoVO> artiReser);
	public ArrayList<ArticuloHeaderVO> getArticulos(ArrayList<Long> codigos);
	public ArrayList<ArticuloReservadoVO> getArtsReservados(int codSolDis);
	public void actArtsRes(ArrayList<ArticuloReservadoVO> articulosReservados);
	public void actualizarStock(ArrayList<ArticuloAEnviarVO> articulosAEnviar, ArrayList<ArticuloReservadoVO> articulosReservados);
	public ArrayList<ArticuloAEnviarVO> getArtsAEnv(int codSolDis);
	public ArrayList<Long> existenArts(ArrayList<Long> codigos);
}

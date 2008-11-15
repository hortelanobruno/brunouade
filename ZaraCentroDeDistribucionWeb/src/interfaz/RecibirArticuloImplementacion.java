package interfaz;

import org.apache.log4j.Logger;

import struts.model.BusinessDelegate;
import varios.XMLConverter;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloRopaVO;
import exceptions.ErrorConectionException;
import exceptions.ExistingProductException;

public class RecibirArticuloImplementacion {

	private BusinessDelegate bd;
	private Logger logger = Logger.getLogger("zara.centro");

	public RecibirArticuloImplementacion() {

	}

	public void guardarArticuloFromJMS(String msg) {
		try {
			bd = new BusinessDelegate();
			ArticuloHeaderVO art = XMLConverter.getArticuloFromString(msg);

			if (art instanceof ArticuloHogarVO) {
				if (bd.existeArticulo(((ArticuloHogarVO) art).getCodigo())) {
					logger.debug("El articulo ya existe en el Centro de Distribucion (cod: "+ ((ArticuloHogarVO) art).getCodigo() + ")");
				} else {
					bd.guardarArticuloHogar((ArticuloHogarVO) art);
					logger.debug("Se guardo un Articulo del Hogar");
				}
			} else {
				if (bd.existeArticulo(((ArticuloRopaVO) art).getCodigo())) {
					logger.debug("El articulo ya existe en el Centro de Distribucion (cod: "+ ((ArticuloRopaVO) art).getCodigo() + ")");
				} else {
					bd.guardarArticuloRopa((ArticuloRopaVO) art);
					logger.debug("Se guardo un Articulo de Ropa");
				}

			}
		} catch (ExistingProductException e) {
			e.printStackTrace();
		} catch (ErrorConectionException e) {
			e.printStackTrace();
		}
	}
}

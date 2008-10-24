package struts.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;

import vo.ArticuloAFabricarVO;


public class CargarArtFabForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8538815922886360165L;
	
	private List<ArticuloAFabricarVO> artsAFab;
	

	public void setArticulosAFabricar(List<ArticuloAFabricarVO> artsAFab) {
		this.artsAFab = artsAFab;
	}

	public List<ArticuloAFabricarVO> getArticulosAFabricar() {
		return artsAFab;
	}

	
	
}

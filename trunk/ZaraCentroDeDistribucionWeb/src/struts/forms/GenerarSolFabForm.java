package struts.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;

import vo.ArticuloAFabricarVO;


public class GenerarSolFabForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8538815922886360165L;
	
	private List<ArticuloAFabricarVO> artsAFab;
	
	private String prueba;
	
	public void setPrueba(String p){
		this.prueba = p;
	}
	
	public String getPrueba(){
		return prueba;
	}

	public void setArticulosAFabricar(List<ArticuloAFabricarVO> artsAFab) {
		this.artsAFab = artsAFab;
	}

	public List<ArticuloAFabricarVO> getArticulosAFabricar() {
		return artsAFab;
	}

	
	
}

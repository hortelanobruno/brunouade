package struts.forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import vo.ArticuloAReponerWebVO;


public class ReposicionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7674118774129018828L;
	private List<ArticuloAReponerWebVO> articulosAReponer = new ArrayList<ArticuloAReponerWebVO>();
	
	public void setArticulosAReponer(List<ArticuloAReponerWebVO> articulosAReponer) {
		this.articulosAReponer = articulosAReponer;
	}
	public List<ArticuloAReponerWebVO> getArticulosAReponer() {
		return articulosAReponer;
	}
	
	
	
}

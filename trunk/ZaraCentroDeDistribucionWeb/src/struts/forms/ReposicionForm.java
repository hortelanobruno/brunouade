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
	private boolean seAtendieronATodas = false;
	private boolean prenderBoton = true;
	
	public void setArticulosAReponer(List<ArticuloAReponerWebVO> articulosAReponer) {
		this.articulosAReponer = articulosAReponer;
	}
	public List<ArticuloAReponerWebVO> getArticulosAReponer() {
		return articulosAReponer;
	}
	public void setSeAtendieronATodas(boolean seAtendieronATodas) {
		this.seAtendieronATodas = seAtendieronATodas;
	}
	public boolean getSeAtendieronATodas() {
		return seAtendieronATodas;
	}
	public void setPrenderBoton(boolean prenderBoton) {
		this.prenderBoton = prenderBoton;
	}
	public boolean getPrenderBoton() {
		return prenderBoton;
	}
	
	
	
}

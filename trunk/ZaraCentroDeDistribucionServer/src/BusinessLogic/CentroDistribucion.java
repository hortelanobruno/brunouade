/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Id;

public class CentroDistribucion 
{
	private int codCentro;
	private String nombreCentro;
	
	@Id
	@Column
	public int getCodCentro() {
		return codCentro;
	}
	public void setCodCentro(int codCentro) {
		this.codCentro = codCentro;
	}
	@Column
	public String getNombreCentro() {
		return nombreCentro;
	}
	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}
    
    
}

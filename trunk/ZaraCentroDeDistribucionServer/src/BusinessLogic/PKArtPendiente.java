package BusinessLogic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PKArtPendiente  implements Serializable
{
	private static final long serialVersionUID = -6713662307233599891L;
	private long codArt;
	private int codSol;
	
	public PKArtPendiente()
	{
		
	}
	
	public PKArtPendiente(long codArt, int codSol)
	{
		this.setCodArt(codArt);
		this.setCodSol(codSol);
	}
	
	@Column
	public long getCodArt() {
		return codArt;
	}
	
	public void setCodArt(long codArt) {
		this.codArt = codArt;
	}
	
	@Column
	public int getCodSol() {
		return codSol;
	}
	
	public void setCodSol(int codSol) {
		this.codSol = codSol;
	}
}

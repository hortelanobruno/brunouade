package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PKArtPendiente 
{
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

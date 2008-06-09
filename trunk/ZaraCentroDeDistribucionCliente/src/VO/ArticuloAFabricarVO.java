package VO;

public class ArticuloAFabricarVO 
{
	private int idAAF;
	private ArticuloHeaderVO art;
	private SolicitudDistribucionVO sol;
	private FabricaVO fabrica;
	private int cantidadPedida;
	private int cantidadRecibida;
	
	public ArticuloAFabricarVO() {

	}

	public ArticuloAFabricarVO(int id, ArticuloHeaderVO art, SolicitudDistribucionVO sd, FabricaVO f, int cantP, int cantR)
	{
		this.setIdAAF(id);
		this.setArt(art);
		this.setSol(sd);
		this.setFabrica(f);
		this.setCantidadPedida(cantP);
		this.setCantidadRecibida(cantR);
	}
	
	
	public ArticuloHeaderVO getArt() {
		return art;
	}
	public void setArt(ArticuloHeaderVO art) {
		this.art = art;
	}
	public int getCantidadPedida() {
		return cantidadPedida;
	}
	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}
	public int getCantidadRecibida() {
		return cantidadRecibida;
	}
	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}
	public FabricaVO getFabrica() {
		return fabrica;
	}
	public void setFabrica(FabricaVO fabrica) {
		this.fabrica = fabrica;
	}
	public int getIdAAF() {
		return idAAF;
	}
	public void setIdAAF(int idAAF) {
		this.idAAF = idAAF;
	}
	public SolicitudDistribucionVO getSol() {
		return sol;
	}
	public void setSol(SolicitudDistribucionVO sol) {
		this.sol = sol;
	}

}

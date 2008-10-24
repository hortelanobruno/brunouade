package vo;

import java.io.Serializable;

public class ArticuloAFabricarVO implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int idAAF;
	private ArticuloHeaderVO art;
	private SolicitudDistribucionVO sol;
	private int cantidadPedida;
	private int cantidadRecibida;
	private int cantidadAFabricar;
	private int cantMinAPedir;

	public ArticuloAFabricarVO() {

	}

	public ArticuloAFabricarVO(int id, ArticuloHeaderVO art,SolicitudDistribucionVO sd, int cantP, int cantR, int cantF, int cantPed)
	{
		this.setIdAAF(id);
		this.setArt(art);
		this.setSol(sd);
		this.setCantidadPedida(cantP);
		this.setCantidadRecibida(cantR);
		this.setCantidadAFabricar(cantF);
		this.setCantMinAPedir(cantPed);
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

	public int getCantidadAFabricar() {
		return cantidadAFabricar;
	}

	public void setCantidadAFabricar(int cantidadAFabricar) {
		this.cantidadAFabricar = cantidadAFabricar;
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

	public void setCantMinAPedir(int cantMinAPedir) {
		this.cantMinAPedir = cantMinAPedir;
	}

	public int getCantMinAPedir() {
		return cantMinAPedir;
	}

}

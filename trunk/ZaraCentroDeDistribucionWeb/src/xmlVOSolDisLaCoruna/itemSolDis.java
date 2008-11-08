package xmlVOSolDisLaCoruna;

public class itemSolDis 
{
	private tienda tienda;
	private int cantidad;
	private long Articulos;
	
	public long getArticulos() {
		return Articulos;
	}
	public void setArticulos(long articulos) {
		Articulos = articulos;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public tienda getTienda() {
		return tienda;
	}
	public void setTienda(tienda tienda) {
		this.tienda = tienda;
	}
}

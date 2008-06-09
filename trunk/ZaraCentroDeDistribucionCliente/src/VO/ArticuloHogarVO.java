package VO;

public class ArticuloHogarVO extends ArticuloHeaderVO
{
	private static final long serialVersionUID = 2629378325506484202L;
	private String detalles; //Alfombra de piel de vaca con patchwork
	private String composicion;
	private String categoria;
	private String medidas;
	
	public ArticuloHogarVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArticuloHogarVO(String desc, long codigo, int cantidad,String color,float precio, String linea, String seccion,String detalles,String composicion, String categoria, String medidas)
	{
		super( desc,  codigo,  cantidad, color, precio,  linea,  seccion);
		this.setDetalles(detalles);
		this.setComposicion(composicion);
		this.setCategoria(categoria);
		this.setMedidas(medidas);
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getComposicion() {
		return composicion;
	}
	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public String getMedidas() {
		return medidas;
	}
	public void setMedidas(String medidas) {
		this.medidas = medidas;
	}

}

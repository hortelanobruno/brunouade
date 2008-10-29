package xml;

public class XMLArticuloLaCoruna 
{
	private long referencia;
	private String descripcion;
	private float precio;
	private String seccion;
	private String color;
	private String linea; //hogar
	private String nombre; //hogar
	private String composicion; //hogar
	private String categoria; //hogar
	private String medidas; //hogar
	private String talle;	//ropa
	private String origen;	//
	
	public XMLArticuloLaCoruna(long referencia, String descripcion, float precio, String seccion, 
							   String color,String linea, String nombre, String composicion, String categoria, 
							   String medidas, String talle, String origen )
	{
		this.setReferencia(referencia);
		this.setDescripcion(descripcion);
		this.setPrecio(precio);
		this.setSeccion(seccion);
		this.setColor(color);
		this.setLinea(linea);
		this.setNombre(nombre);
		this.setComposicion(composicion);
		this.setCategoria(categoria);
		this.setMedidas(medidas);
		this.setTalle(talle);
		this.setOrigen(origen);
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getComposicion() {
		return composicion;
	}
	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getMedidas() {
		return medidas;
	}
	public void setMedidas(String medidas) {
		this.medidas = medidas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public long getReferencia() {
		return referencia;
	}
	public void setReferencia(long referencia) {
		this.referencia = referencia;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getTalle() {
		return talle;
	}
	public void setTalle(String talle) {
		this.talle = talle;
	}
}

package BusinessLogic;

public abstract class Articulo
{
	private String referencia;
	private String descripcion;
	private float precio;
	private int codigo;
	private String seccion;
	private String color;
        private int cantidad;
        
        public Articulo(int codigo, int cantidad){
            this.cantidad=cantidad;
            this.codigo=codigo;
        }
        
        public Articulo(){
            
        }
	
	public String getColor() 
	{
		return color;
	}

	public void setColor(String color) 
	{
		this.color = color;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public float getPrecio() 
	{
		return precio;
	}
	
	public void setPrecio(float precio)
{
		this.precio = precio;
	}
	
	public String getReferencia() 
	{
		return referencia;
	}
	
	public void setReferencia(String referencia) 
	{
		this.referencia = referencia;
	}
	
	public int getCodigo()
	{
		return codigo;
	}
	
	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}

	public String getSeccion()
	{
		return seccion;
	}

	public void setSeccion(String seccion) 
	{
		this.seccion = seccion;
	}

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

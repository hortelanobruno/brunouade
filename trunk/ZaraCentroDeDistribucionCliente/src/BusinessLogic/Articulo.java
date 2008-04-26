package BusinessLogic;

public abstract class Articulo
{
	private String referencia;
	private String descripcion;
	private float precio;
	private int codigo;
	private String seccion;
	private String color;
	private String linea;
	private String estado;
    private int stock;
    
    public Articulo(int codigo, int cantidad)
    {
        this.stock=cantidad;
        this.codigo=codigo;
    }
    
    public Articulo()
    {
        
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

    public int getStock() 
    {
        return stock;
    }

    public void setCantidad(int cantidad) 
    {
        this.stock = cantidad;
    }

	public String getLinea() 
	{
		return linea;
	}

	public void setLinea(String linea) 
	{
		this.linea = linea;
	}

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado) 
	{
		this.estado = estado;
	}
}

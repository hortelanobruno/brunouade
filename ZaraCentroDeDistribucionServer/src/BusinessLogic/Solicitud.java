package BusinessLogic;

import java.util.Date;
import java.util.Vector;

public abstract class Solicitud 
{
	private int numero;
	private Vector<ArticuloHogar> articuloshogar = new Vector<ArticuloHogar>();
    private Vector<ArticuloRopa> articulosropa = new Vector<ArticuloRopa>();
    private Date fechaEmision;
    
	public Vector<ArticuloHogar> getArticuloshogar() 
	{
		return articuloshogar;
	}
	
	public void setArticuloshogar(Vector<ArticuloHogar> articuloshogar) 
	{
		this.articuloshogar = articuloshogar;
	}
	
	public Vector<ArticuloRopa> getArticulosropa()
	{
		return articulosropa;
	}
	
	public void setArticulosropa(Vector<ArticuloRopa> articulosropa) 
	{
		this.articulosropa = articulosropa;
	}
	
	public int getNumero() 
	{
		return numero;
	}
	
	public void setNumero(int numero) 
	{
		this.numero = numero;
	}
	
	public void agregarArticuloRopa(ArticuloRopa ar)
    {
		this.getArticulosropa().add(ar);
    }

    public void agregarArticuloHogar(ArticuloHogar ar)
    {
        this.getArticuloshogar().add(ar);
    }

	public Date getFecha()
	{
		return fechaEmision;
	}

	public void setFecha(Date fecha)
	{
		this.fechaEmision = fecha;
	}
    
}

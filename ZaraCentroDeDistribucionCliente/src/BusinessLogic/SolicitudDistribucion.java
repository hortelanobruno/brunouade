package BusinessLogic;

import java.util.Vector;

public class SolicitudDistribucion 
{
    private Vector<ArticuloHogar> articuloshogar = new Vector<ArticuloHogar>();
    private Vector<ArticuloRopa> articulosropa = new Vector<ArticuloRopa>();
    private String numero;
    private Tienda tienda;
    
    public void agregarArticuloRopa(ArticuloRopa ar)
    {
		articulosropa.add(ar);
    }

    public void agregarArticuloHogar(ArticuloHogar ar)
    {
        articuloshogar.add(ar);
    }
    
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

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

	public Tienda getTienda() 
	{
		return tienda;
	}

	public void setTienda(Tienda tienda) 
	{
		this.tienda = tienda;
	}
}

package BusinessLogic;

public class Tienda 
{
	private int codigoTienda;
	private String nombreTienda;
	
	public Tienda()
	{
		
	}
	
	public Tienda(int cod, String nombre)
	{
		this.setCodTienda(cod);
		this.setNombreTienda(nombre);
	}
	
	public int getCodTienda() 
	{
		return codigoTienda;
	}
	
	public void setCodTienda(int codTienda) 
	{
		this.codigoTienda = codTienda;
	}
	
	public String getNombreTienda() 
	{
		return nombreTienda;
	}
	
	public void setNombreTienda(String nombreTienda) 
	{
		this.nombreTienda = nombreTienda;
	}
}

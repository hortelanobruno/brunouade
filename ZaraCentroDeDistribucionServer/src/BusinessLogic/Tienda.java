package BusinessLogic;

public class Tienda 
{
	private int codTienda;
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
		return codTienda;
	}
	
	public void setCodTienda(int codTienda) 
	{
		this.codTienda = codTienda;
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

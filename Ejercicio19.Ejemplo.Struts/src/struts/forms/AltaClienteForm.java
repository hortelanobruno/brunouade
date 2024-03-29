package struts.forms;


import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import util.BookBean;

/**
 * Implementa el formulario de Alta para un Cliente
 * 
 *  @author rorosco
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class AltaClienteForm extends ActionForm
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** campo nombre */
	private String _nombreCliente = null;
	
	/** campo direccion */
	private String _direccionCliente = null;

	/** campo codigo */
	private Integer _codigoCliente = null;

	private ArrayList<BookBean> books = null;
	
	private String edad = null;
	
	private String prueba = null;
	
	public String getPrueba(){
		return prueba;
	}
	
	public void setPrueba(String p){
		prueba = p;
	}
	
	public String getEdad(){
		return edad;
	}
	
	public void setEdad(String e){
		edad=e;
	}
	
	public ArrayList<BookBean> getBooks()
	{
	    return books;
	}
	
	public void setBooks(ArrayList<BookBean> pMochila)
	{
		books = pMochila;
	}
	
	public void setCodigoCliente(Integer pCodigoCliente)
	{ 
		_codigoCliente = pCodigoCliente;
	}
    
	public Integer getCodigoCliente()
	{
		return _codigoCliente;
	}

    public void setNombreCliente(String pNombreCliente)
    { 
    	_nombreCliente = pNombreCliente;
    }
    
    public String getNombreCliente()
    {
    	return _nombreCliente;
    }
    
    public void setDireccionCliente(String pDireccionCliente)
    {
    	_direccionCliente = pDireccionCliente;
    }
    
    public String getDireccionCliente()
    {
    	return _direccionCliente;
    }
    
}

package struts.forms;

import org.apache.struts.action.ActionForm;

public class CargarDatosForm extends ActionForm{

	private String nombre;
	
	private String direccion;
	
	private String[] calle;
	
	private String[] ciudad;
	
	public String[] getCalle(){
		return calle;
	}
	
	public void setCalle(String[] c){
		calle=c;
	}
	
	public String[] getCiudad(){
		return ciudad;
	}
	
	public void setCiudad(String[] c){
		ciudad=c;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String n){
		nombre=n;
	}
	
	public String getDireccion(){
		return direccion;
	}
	
	public void setDireccion(String n){
		direccion=n;
	}
}

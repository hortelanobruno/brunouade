package interfaz;

import struts.model.BusinessDelegate;
import varios.XMLConverter;
import vo.ArticuloHeaderVO;
import vo.ArticuloHogarVO;
import vo.ArticuloRopaVO;
import exceptions.ErrorConectionException;
import exceptions.ExistingProductException;

public class RecibirArticuloImplementacion 
{

	private BusinessDelegate bd;
	
	public RecibirArticuloImplementacion() 
	{
		
	}
	
	public void guardarArticuloFromJMS(String msg)
	{
		try 
		{
			bd = new BusinessDelegate();
			ArticuloHeaderVO art =XMLConverter.getArticuloFromString(msg);
			
			if(art instanceof ArticuloHogarVO) bd.guardarArticuloHogar((ArticuloHogarVO)art);
			else bd.guardarArticuloRopa((ArticuloRopaVO)art);
		}  
		catch (ExistingProductException e) 
		{
			e.printStackTrace();
		} 
		catch (ErrorConectionException e) 
		{
			e.printStackTrace();
		}
	}
}

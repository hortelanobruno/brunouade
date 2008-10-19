package exceptions;

import java.io.Serializable;


public class ExistingProductException extends Exception implements Serializable
{
	private static final long serialVersionUID = -5407677594438902954L;
	
	public ExistingProductException(String msg)
	{
		super(msg);
	}
}

package interfaz;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import varios.Constantes;
import businesslogic.ServerFacade;
import exceptions.ErrorConectionException;

public class FacadeInterface 
{
	private ServerFacade modCD = null;
	private String naming = Constantes.BEAN_STRING;
	private static FacadeInterface fi;
	
	public static FacadeInterface getInstance() throws ErrorConectionException
	{
		if(fi == null)
		{
			fi = new FacadeInterface();
			return fi;
		}
		else return fi;
	}
	
	private FacadeInterface() throws ErrorConectionException
	{
		this.getConnection();
	}
	
	@SuppressWarnings("unchecked")
	protected void getConnection() throws ErrorConectionException {
        try {
        	Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL,"jnp://192.168.1.103:1099");
			InitialContext context = new InitialContext(props);
			this.modCD = (ServerFacade) context.lookup(naming);
        } catch (Exception e) {
        	throw new ErrorConectionException("No se pudo conectar");
        }
    }    
	
	private ServerFacade getModCD() 
	{
		return modCD;
	}
	
    @SuppressWarnings("unused")
	private static Context getInitialContext() throws javax.naming.NamingException {
        return new javax.naming.InitialContext();
    }

    public void guardarArticuloFromJMS(String mens)
    {
    	this.getModCD().guardarArticuloFromJMS(mens);
    }
    
    public void guardarSolDisFromWS(String solDis)
    {
    	this.getModCD().guardarSolDisFromWS(solDis);
    }
    
    public void guardarSolRepFromWS(String solRep)
    {
    	this.getModCD().guardarSolRepFromWS(solRep);
    }
}

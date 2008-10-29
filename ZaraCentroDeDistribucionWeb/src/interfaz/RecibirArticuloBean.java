package interfaz;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;

import exceptions.ErrorConectionException;

import varios.Constantes;
import businesslogic.ServerFacade;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/queueArticulos")
})

public class RecibirArticuloBean implements MessageDrivenBean, MessageListener
{
	private static final long serialVersionUID = 1537709922823367360L;
	private ServerFacade modCD = null;
	private String naming = Constantes.BEAN_STRING;
	
	@SuppressWarnings("unchecked")
	protected void getConnection() throws ErrorConectionException {
        try {
        	Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL,"jnp://127.0.0.1:1099");
			InitialContext context = new InitialContext(props);
			this.modCD = (ServerFacade) context.lookup(naming);
        } catch (Exception e) {
        	throw new ErrorConectionException("No se pudo conectar");
        }
    }    
	
	public ServerFacade getModCD() {
		return modCD;
	}
	
    @SuppressWarnings("unused")
	private static Context getInitialContext() throws javax.naming.NamingException {
        return new javax.naming.InitialContext();
    }
	
	public void onMessage(Message message) 
	{
		TextMessage msg = null;
		
		try {
			if (message instanceof TextMessage) {
				msg = (TextMessage) message;			
				String mens = msg.getText();
				
				System.out.println(mens);
				//this.getConnection();
				//this.getModCD().guardarArticuloFromJMS(mens);
			}
				
		} catch (JMSException e) {
			System.out.println("error");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("error1");
			e.printStackTrace();
		}
	}

	public void ejbRemove() throws EJBException 
	{
	
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException 
	{

	}
}

package interfaz;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import javax.ejb.CreateException;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/testQueue")
})

public class RecibirArticuloBean implements MessageDrivenBean, MessageListener
{
//	private ServerFacade modCD = null;
//private String naming = Constantes.BEAN_STRING;
	
	/*@SuppressWarnings("unchecked")
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
    }    */
	
	public RecibirArticuloBean()
	{
		
	}
	
	/*public ServerFacade getModCD() {
		return modCD;
	}
	
    @SuppressWarnings("unused")
	private static Context getInitialContext() throws javax.naming.NamingException {
        return new javax.naming.InitialContext();
    }*/

	public void ejbRemove() throws EJBException {
		// TODO Auto-generated method stub

	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
		throws EJBException {
		// TODO Auto-generated method stub

	}

	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		TextMessage msg = null;
		
		try {
			if (arg0 instanceof TextMessage) {
				msg = (TextMessage) arg0;
				System.out.println("MESSAGE BEAN: Mensaje recibido: "+ msg.getText() );
			} else {
				System.out.println("MESSAGE BEAN: Mensaje de tipo incorrecto ");
			}
				
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() throws CreateException {
		// TODO Auto-generated method stub
	}
}

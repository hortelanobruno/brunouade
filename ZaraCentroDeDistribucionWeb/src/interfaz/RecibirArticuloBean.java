package interfaz;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/colaArticulos")
})

public class RecibirArticuloBean implements MessageDrivenBean, MessageListener
{
	private static final long serialVersionUID = -4262941444807681915L;
	
	
	public RecibirArticuloBean()
	{
		
	}

	public void ejbRemove() throws EJBException {

	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)throws EJBException 
	{

	}

	public void onMessage(Message m) 
	{	
		TextMessage msg = null;
		
		try 
		{
			if (m instanceof TextMessage) 
			{
				msg = (TextMessage) m;
				String mens = msg.getText();
				RecibirArticuloImplementacion rArt = new RecibirArticuloImplementacion(); 
				rArt.guardarArticuloFromJMS(mens);
			} 
			else System.out.println("MESSAGE BEAN: Mensaje de tipo incorrecto ");
				
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

	}
}

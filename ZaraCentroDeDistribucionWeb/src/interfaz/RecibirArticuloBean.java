package interfaz;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/testQueue")
})

public class RecibirArticuloBean implements MessageDrivenBean, MessageListener
{
	private static final long serialVersionUID = 1537709922823367360L;

	public void onMessage(Message message) 
	{

	}

	public void ejbRemove() throws EJBException 
	{
	
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException 
	{

	}
}

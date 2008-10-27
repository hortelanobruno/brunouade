package integracion;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageSender {
	
	private Hashtable props = new Hashtable();
	private QueueConnectionFactory qfactory;
	private Queue queue;
	private QueueConnection qCon;
	private QueueSession qSession;
	private QueueSender qSender;
	
	@SuppressWarnings("unchecked")
	public MessageSender(String cola)
	{
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY , "org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		
		try 
		{
			initCola(cola);
		} 
		catch (Exception e) 
		{
			System.out.println("Error al inicializar la cola. " + e);
		}
	}

	private void initCola(String cola) throws NamingException, JMSException 
	{
		InitialContext ctx = new javax.naming.InitialContext(props);
		qfactory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		queue = (Queue) ctx.lookup(cola);
		qCon = qfactory.createQueueConnection();
		qSession = qCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		qSender = qSession.createSender(queue);
	}
	
	public void sendMessage(String mensaje)
	{
		try
		{
			TextMessage message = qSession.createTextMessage();
			message.setText(mensaje);
			qSender.send(message);
		}
		catch(Exception e)
		{
			System.out.println("Error al enviar mensaje. " + e);
		}
		
	}

}

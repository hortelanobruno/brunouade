package cliente;
import java.util.Hashtable;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class ClienteJMS {

	public static void main(String[] args) {
		efectuarPedido();
	}
	
	@SuppressWarnings("unchecked")
	private static void efectuarPedido() {
		Hashtable props = new Hashtable();
		
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY , "org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		
		try {
//			InitialContext ctx = new InitialContext(props);
			InitialContext ctx = new javax.naming.InitialContext(props);
			
			// buscar la ConnectionFactory en JNDI
			QueueConnectionFactory qfactory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
			
			// buscar la Cola en JNDI
			Queue queue = (Queue) ctx.lookup("queue/queueArticulos");
			
			// crear la connection y la session a partir de la connection
			QueueConnection qCon = qfactory.createQueueConnection();
			QueueSession qSession = qCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			 
			// crear un producer para enviar mensajes usando la session
			QueueSender qSender = qSession.createSender(queue);
			
			// crear un mensaje de tipo text y setearle el contenido
			TextMessage message = qSession.createTextMessage();
			
			String msgHogar = 
				"<XMLArticulo> <REFERENCIA>2</REFERENCIA>" +
				"<DESCRIPCION>alfombra havana</DESCRIPCION>" +
				"<PRECIOVENTAUNITARIO>200</PRECIOVENTAUNITARIO>" +
				"<SECCION>alfombra</SECCION>" +
				"<COLOR>unico</COLOR> " +
				"<LINEA>unica</LINEA> +" +
				"<NOMBRE>alfombra de piel de vaca con patchwork</NOMBRE>" +
				"<COMPOSICION>100% piel bovina</COMPOSICION>" +
				"<CATEGORIA>cama</CATEGORIA>" +
				"<MEDIDAS>150 X 220 cm</MEDIDAS>" +
				"</XMLArticulo>";
			
			String msgRopa = 
				"<XMLArticulo> <REFERENCIA>2</REFERENCIA>" +
				"<DESCRIPCION>alfombra havana</DESCRIPCION> " +
				"<PRECIOVENTAUNITARIO>200</PRECIOVENTAUNITARIO>" +
				"<SECCION>alfombra</SECCION>" +
				"<COLOR>unico</COLOR> " +
				"<TALLE>5</TALLE>" +
				"<ORIGEN>arg</ORIGEN>" +
				"</XMLArticulo>";
			
			message.setText(msgHogar);
			
			// enviar el mensaje
			qSender.send(message);
			
			System.out.println("Mande el de hogar\n");
			
			message.setText(msgRopa);
			
			// enviar el mensaje
			qSender.send(message);
			System.out.println("Mande el de ropa\n");
			
		} catch (Exception e) {
			System.out.println("Error al efectuar pedido " + e);
		}
	}
	
}

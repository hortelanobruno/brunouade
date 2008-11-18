package cliente;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class ClienteJMS 
{
	private List<String> lista;
	private List<String> lista2;
	
	public static void main(String[] args) {
		new ClienteJMS();
	}
	
	private void cargarMensajesArticulos()
	{
		System.out.println("Llenando lista...");
		
	/*	String art1000 = 
			"<XMLArticulo> " +
				"<referencia>1000</referencia>" +
				"<descripcion>camisa</descripcion>" +
				"<precioventaunitario>200</precioventaunitario>" +
				"<seccion>hombre</seccion>" +
				"<color>blanco</color> " +
				"<talle>m</talle>" +
				"<origen>argentina</origen>" +
			"</XMLArticulo>";
		this.lista.add(art1000);
		
		String art1001 = 
			"<XMLArticulo> " +
				"<referencia>1001</referencia>" +
				"<descripcion>pantalon</descripcion>" +
				"<precioventaunitario>200</precioventaunitario>" +
				"<seccion>hombre</seccion>" +
				"<color>negro</color> " +
				"<talle>m</talle>" +
				"<origen>argentina</origen>" +
			"</XMLArticulo>";
		
		this.lista.add(art1001);
		
		String art1002 =
			"<XMLArticulo> " +
				"<referencia>1002</referencia>" +
				"<descripcion>chomba</descripcion>" +
				"<precioventaunitario>120</precioventaunitario>" +
				"<seccion>hombre</seccion>" +
				"<color>celeste</color> " +
				"<talle>s</talle>" +
				"<origen>argentina</origen>" +
			"</XMLArticulo>";

		this.lista.add(art1002);
		
		String art1003 = 
			"<XMLArticulo> " +
				"<referencia>1003</referencia>" +
				"<descripcion>chomba fashion</descripcion>" +
				"<precioventaunitario>180</precioventaunitario>" +
				"<seccion>hombre</seccion>" +
				"<color>celeste</color> " +
				"<talle>m</talle>" +
				"<origen>argentina</origen>" +
			"</XMLArticulo>";

		this.lista.add(art1003);
		
		String art1100 = 
			"<XMLArticulo> " +
				"<referencia>1100</referencia>" +
				"<descripcion>florero</descripcion>" +
				"<precioventaunitario>200</precioventaunitario>" +
				"<seccion>decoracion</seccion>" +
				"<color>blanco</color> " +
				"<linea>nueva</linea> +" +
				"<nombre>florero con plantitas</nombre>" +
				"<composicion>porcelana</composicion>" +
				"<categoria>casa</categoria>" +
				"<medidas>5x2</medidas>" +
			"</XMLArticulo>";
		this.lista.add(art1100);
		
		String art1101 = 
			"<XMLArticulo> " +
				"<referencia>1101</referencia>" +
				"<descripcion>jean levis</descripcion>" +
				"<precioventaunitario>170</precioventaunitario>" +
				"<seccion>hombre</seccion>" +
				"<color>negro</color> " +
				"<talle>m</talle>" +
				"<origen>china</origen>" +
			"</XMLArticulo>";
		this.lista.add(art1101);
		
		String art1102 = 
			"<XMLArticulo> " +
				"<referencia>1102</referencia>" +
				"<descripcion>corbata london tie</descripcion>" +
				"<precioventaunitario>70</precioventaunitario>" +
				"<seccion>hombre</seccion>" +
				"<color>vino</color> " +
				"<talle>unico</talle>" +
				"<origen>inglaterra</origen>" +
			"</XMLArticulo>";
		this.lista.add(art1102);
		
		String art1103 =
			"<XMLArticulo> " +
				"<referencia>1103</referencia>" +
				"<descripcion>cuadro chino</descripcion>" +
				"<precioventaunitario>700</precioventaunitario>" +
				"<seccion>decoracion</seccion>" +
				"<color>unico</color> " +
				"<linea>chinese</linea> +" +
				"<nombre>cuadro con letras chinas</nombre>" +
				"<composicion>madera</composicion>" +
				"<categoria>cuadros</categoria>" +
				"<medidas>2m x 2m</medidas>" +
			"</XMLArticulo>";
		this.lista.add(art1103);
		
		String art1 =
			"<XMLArticulo> " +
				"<referencia>1</referencia>" +
				"<descripcion>camisa</descripcion>" +
				"<precioventaunitario>100</precioventaunitario>" +
				"<seccion>mujer</seccion>" +
				"<color>blanco</color> " +
				"<talle>l</talle>" +
				"<origen>argentina</origen>" +
			"</XMLArticulo>";
		this.lista.add(art1);
		
		String art2 =
			"<XMLArticulo> " +
				"<referencia>2</referencia>" +
				"<descripcion>alfombra havana</descripcion>" +
				"<precioventaunitario>200</precioventaunitario>" +
				"<seccion>alfombra</seccion>" +
				"<color>unico</color> " +
				"<linea>unica</linea> +" +
				"<nombre>alfombra de piel de vaca con patchwork</nombre>" +
				"<composicion>100% piel bovina</composicion>" +
				"<categoria>cama</categoria>" +
				"<medidas>150 X 220 cm</medidas>" +
			"</XMLArticulo>";
		this.lista.add(art2);*/
		
		String art = "<vo.ArtPersonalVO> <referencia>9697005</referencia> <linea>Woman</linea>"
		  + "<descripcion>Julian</descripcion> <color>Blanco</color> <seccion>Mujer</seccion>"
		  +"<precioVentaUnitario>32.0</precioVentaUnitario> <ultimoPedido>2008-11-03 10:30:31.412 GST</ultimoPedido>"
		  + "<mesRebaja>3</mesRebaja> <centros class='set'> <CentroDistribucion> <codigo>2</codigo> <nombre>Centro</nombre>"
		      +"<pais>Argentina</pais> </CentroDistribucion> <CentroDistribucion> <codigo>4</codigo><nombre>Parque Norte</nombre>"
		      +"<pais>Chile</pais> </CentroDistribucion> <CentroDistribucion> <codigo>5</codigo><nombre>Shopping Centro</nombre>"
		      +"<pais>Uruguay</pais></CentroDistribucion><CentroDistribucion> <codigo>6</codigo><nombre>Montevideo Sur</nombre>"
		      +"<pais>Uruguay</pais> </CentroDistribucion></centros><esNuevo>true</esNuevo> <talle>32</talle> <origen>Brasil</origen>"
		+"</vo.ArtPersonalVO>";
		this.lista.add(art);
		
		art = "<vo.ArtPersonalVO> <referencia>9697002</referencia> <linea>Woman</linea>"
		  + "<descripcion>Mulo</descripcion> <color>Verga</color> <seccion>Mujer</seccion>"
		  +"<precioVentaUnitario>2.0</precioVentaUnitario> <ultimoPedido>2008-11-03 10:30:31.412 GST</ultimoPedido>"
		  + "<mesRebaja>3</mesRebaja> <centros class='set'> <CentroDistribucion> <codigo>2</codigo> <nombre>Centro</nombre>"
		      +"<pais>Argentina</pais> </CentroDistribucion> <CentroDistribucion> <codigo>4</codigo><nombre>Parque Norte</nombre>"
		      +"<pais>Chile</pais> </CentroDistribucion> <CentroDistribucion> <codigo>5</codigo><nombre>Shopping Centro</nombre>"
		      +"<pais>Uruguay</pais></CentroDistribucion><CentroDistribucion> <codigo>6</codigo><nombre>Montevideo Sur</nombre>"
		      +"<pais>Uruguay</pais> </CentroDistribucion></centros><esNuevo>true</esNuevo> <talle>2</talle> <origen>Wilde</origen>"
		+"</vo.ArtPersonalVO>";
		this.lista.add(art);
		
		art = "<vo.ArtPersonalVO> <referencia>1234</referencia> <linea>SuperMan</linea>"
			  + "<descripcion>Nacho</descripcion> <color>Blanco</color> <seccion>Groso</seccion>"
			  +"<precioVentaUnitario>320.0</precioVentaUnitario> <ultimoPedido>2008-11-03 10:30:31.412 GST</ultimoPedido>"
			  + "<mesRebaja>3</mesRebaja> <centros class='set'> <CentroDistribucion> <codigo>2</codigo> <nombre>Centro</nombre>"
			      +"<pais>Argentina</pais> </CentroDistribucion> <CentroDistribucion> <codigo>4</codigo><nombre>Parque Norte</nombre>"
			      +"<pais>Chile</pais> </CentroDistribucion> <CentroDistribucion> <codigo>5</codigo><nombre>Shopping Centro</nombre>"
			      +"<pais>Uruguay</pais></CentroDistribucion><CentroDistribucion> <codigo>6</codigo><nombre>Montevideo Sur</nombre>"
			      +"<pais>Uruguay</pais> </CentroDistribucion></centros><esNuevo>true</esNuevo> <talle>32</talle> <origen>Ramos Mejia</origen>"
			+"</vo.ArtPersonalVO>";
			this.lista.add(art);
			
			
			art = "<vo.ArtPersonalVO> <referencia>1111</referencia> <linea>Woman</linea>"
				  + "<descripcion>Mulo2</descripcion> <color>Verga</color> <seccion>Mujer</seccion>"
				  +"<precioVentaUnitario>2.0</precioVentaUnitario> <ultimoPedido>2008-11-03 10:30:31.412 GST</ultimoPedido>"
				  + "<mesRebaja>3</mesRebaja> <centros class='set'> <CentroDistribucion> <codigo>2</codigo> <nombre>Centro</nombre>"
				      +"<pais>Argentina</pais> </CentroDistribucion> <CentroDistribucion> <codigo>4</codigo><nombre>Parque Norte</nombre>"
				      +"<pais>Chile</pais> </CentroDistribucion> <CentroDistribucion> <codigo>5</codigo><nombre>Shopping Centro</nombre>"
				      +"<pais>Uruguay</pais></CentroDistribucion><CentroDistribucion> <codigo>6</codigo><nombre>Montevideo Sur</nombre>"
				      +"<pais>Uruguay</pais> </CentroDistribucion></centros><esNuevo>true</esNuevo> <talle>2</talle> <origen>Wilde</origen>"
				+"</vo.ArtPersonalVO>";
				this.lista2.add(art);
				
				art = "<vo.ArtPersonalVO> <referencia>2222</referencia> <linea>Woman</linea>"
					  + "<descripcion>Mulo3</descripcion> <color>Verga</color> <seccion>Mujer</seccion>"
					  +"<precioVentaUnitario>2.0</precioVentaUnitario> <ultimoPedido>2008-11-03 10:30:31.412 GST</ultimoPedido>"
					  + "<mesRebaja>3</mesRebaja> <centros class='set'> <CentroDistribucion> <codigo>2</codigo> <nombre>Centro</nombre>"
					      +"<pais>Argentina</pais> </CentroDistribucion> <CentroDistribucion> <codigo>4</codigo><nombre>Parque Norte</nombre>"
					      +"<pais>Chile</pais> </CentroDistribucion> <CentroDistribucion> <codigo>5</codigo><nombre>Shopping Centro</nombre>"
					      +"<pais>Uruguay</pais></CentroDistribucion><CentroDistribucion> <codigo>6</codigo><nombre>Montevideo Sur</nombre>"
					      +"<pais>Uruguay</pais> </CentroDistribucion></centros><esNuevo>true</esNuevo> <talle>2</talle> <origen>Wilde</origen>"
					+"</vo.ArtPersonalVO>";
					this.lista2.add(art);
					
					art = "<vo.ArtPersonalVO> <referencia>3333</referencia> <linea>Woman</linea>"
						  + "<descripcion>Mulo4</descripcion> <color>Verga</color> <seccion>Mujer</seccion>"
						  +"<precioVentaUnitario>2.0</precioVentaUnitario> <ultimoPedido>2008-11-03 10:30:31.412 GST</ultimoPedido>"
						  + "<mesRebaja>3</mesRebaja> <centros class='set'> <CentroDistribucion> <codigo>2</codigo> <nombre>Centro</nombre>"
						      +"<pais>Argentina</pais> </CentroDistribucion> <CentroDistribucion> <codigo>4</codigo><nombre>Parque Norte</nombre>"
						      +"<pais>Chile</pais> </CentroDistribucion> <CentroDistribucion> <codigo>5</codigo><nombre>Shopping Centro</nombre>"
						      +"<pais>Uruguay</pais></CentroDistribucion><CentroDistribucion> <codigo>6</codigo><nombre>Montevideo Sur</nombre>"
						      +"<pais>Uruguay</pais> </CentroDistribucion></centros><esNuevo>true</esNuevo> <talle>2</talle> <origen>Wilde</origen>"
						+"</vo.ArtPersonalVO>";
						this.lista2.add(art);
	}
	
	public ClienteJMS()
	{
		this.lista = new ArrayList<String>();
		this.lista2 = new ArrayList<String>();
		cargarMensajesArticulos();
		efectuarPedido();
	}
	
	@SuppressWarnings("unchecked")
	private void efectuarPedido() {
		Hashtable props = new Hashtable();
		
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY , "org.jnp.interfaces.NamingContextFactory");
		//props.put(InitialContext.PROVIDER_URL, "jnp://127.0.0.1:1099");
		props.put(InitialContext.PROVIDER_URL, "jnp://192.168.9.127:1099");
		
		try {
//			InitialContext ctx = new InitialContext(props);
			InitialContext ctx = new javax.naming.InitialContext(props);
			
			// buscar la ConnectionFactory en JNDI
			QueueConnectionFactory qfactory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
			
			// buscar la Cola en JNDI
			Queue queue = (Queue) ctx.lookup("queue/testQueue");
			
			// crear la connection y la session a partir de la connection
			QueueConnection qCon = qfactory.createQueueConnection();
			QueueSession qSession = qCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			 
			// crear un producer para enviar mensajes usando la session
			QueueSender qSender = qSession.createSender(queue);
			
			// crear un mensaje de tipo text y setearle el contenido
			TextMessage message = qSession.createTextMessage();
			
			System.out.println("Mandando mensajes....");
			
			for(int i = 0; i< this.lista.size();i++)
			{
				message.setText(this.lista.get(i));
				qSender.send(message);
				message.setText(this.lista2.get(i));
				qSender.send(message);
			}
			
			System.out.println(this.lista.size() + this.lista.size() + " mensajes enviados.");
			
		} catch (Exception e) {
			System.out.println("Error al efectuar pedido " + e);
		}
	}
	
}

package ejemplo;

import java.io.FileOutputStream;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;




public class DOMAppGenerarXML {

	public static void main (String args[]) throws Exception
	{
		Element root = new Element ("tienda");
		root.setAttribute ("nombre", "Tienda 1");
		root.setAttribute ("ubicación", "Buenos Aires, Argentina");
		Element item1 = new Element ("computadora");
		item1.setAttribute ("nombre", "iBook");
		item1.setAttribute ("precio", "$1200");
		Element itemPc = new Element("Procesador");
		itemPc.addContent("amd k2");
		item1.addContent(itemPc);
		root.addContent (item1);
		
		XMLOutputter out = new XMLOutputter ();
		try{
			out.output (new Document(root), new FileOutputStream ("files/tienda.xml"));
			System.out.println("Se generó el XML");
		   } 
		catch (Exception e){
		    e.getMessage();
		} 



	}


	public DOMAppGenerarXML ()
	{
		
	}


	

}

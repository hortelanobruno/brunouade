package ejemplo;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;




public class DOMAppLeerXML {

	public static void main (String args[]) throws Exception
	{
		SAXBuilder builder = new SAXBuilder ();
		
		String xml = 	"<?xml version=\"1.0\"?>"+

		"<poema descripcion=\"ejemplo DOM IS2\">"+
			"<titulo>Roses are Red</titulo>	<lineas><linea>Roses are red,</linea><linea>Violets are blue;</linea><linea>Sugar is sweet,</linea>	<linea>And I love you.</linea></lineas></poema>";
		
		Reader in = new StringReader(xml);
		
		//Document doc = builder.build (new FileInputStream ("files/tienda.xml"));
		Document doc = builder.build (in);
		Element root = doc.getRootElement();
//		System.out.print("Elemento :"+root.getName());                       
//		System.out.println(" | Valor Atributo descripcion: "+root.getAttributeValue ("descripcion"));    
//		List hijos = root.getChildren ();
//		for (Object hijo : hijos) {
//			System.out.print("Elemento: "+((Element)hijo).getName());
//			System.out.println(" | Contenido: "+ ((Element)hijo).getText());
//		}
		
		Element lineas = root.getChild("lineas");
		List hijos = lineas.getChildren();
		for (Object hijo : hijos) {
			System.out.print("Elemento: "+((Element)hijo).getName());
			System.out.println(" | Contenido: "+ ((Element)hijo).getText());
		}
	}



	

}

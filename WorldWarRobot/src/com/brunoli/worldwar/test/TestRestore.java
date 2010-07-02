package com.brunoli.worldwar.test;

import java.util.Map;

import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.parser.ObtainRestore;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.util.RestoreValue;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class TestRestore {

	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private ObtainInformation obtainInformation;
	private ObtainRestore obtainRestore;
	private HttpGetUrl get;
	
	
	public static void main(String[] arg) {
		new TestRestore();
	}
	
	public TestRestore() {
		get = new HttpGetUrl();
		obtainInformation = new ObtainInformation();
		obtainRestore = new ObtainRestore();
		try {
			StringBuilder page = get.getUrl(urlInicio);
			//Leo datos
			obtainInformation.mostrarDatos(obtainInformation.leerDatosUsuario(page));
			//Leo links
			Map<String,String> links = obtainInformation.leerLinks(page);
			System.out.println("Go to hospital.");
			String unitUrl = links.get(Menus.HOSPITAL.getValue());
			System.out.println("Link... "+unitUrl);
			page = get.getUrl(unitUrl);
			System.out.println("Restore health");
			RestoreValue rv = obtainRestore.leerDatos(page);
			if(rv.getValueVault()>rv.getValueRestore()){
				page = get.getUrl(rv.getUrlRestore());
				System.out.println("Se hizo el restore health.");
			}else{
				System.out.println("No alcanza para el restore health.");
			}
			System.out.println("FIN");
		} catch (Exception e) {
			System.out.println("Error en el get. "+e.getMessage());
		}
		get.close();
	}


}

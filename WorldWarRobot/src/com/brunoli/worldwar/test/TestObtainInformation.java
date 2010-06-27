package com.brunoli.worldwar.test;

import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class TestObtainInformation {

	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private ObtainInformation obtainInformation;
	private HttpGetUrl get;
	
	
	public static void main(String[] arg) {
		new TestObtainInformation();
	}
	
	public TestObtainInformation() {
		get = new HttpGetUrl();
		obtainInformation = new ObtainInformation();
		try {
			StringBuilder page = get.getUrl(urlInicio);
			//Leo datos
			obtainInformation.mostrarDatos(obtainInformation.leerDatosUsuario(page));
			//Leo links
			obtainInformation.mostrarDatos(obtainInformation.leerLinks(page));
			
			
		} catch (Exception e) {
			System.out.println("Error en el get. "+e.getMessage());
		}
	}
}

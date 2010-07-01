package com.brunoli.worldwar.test;

import java.util.HashMap;
import java.util.Map;

import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class TestBankDeposit {

	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private ObtainInformation obtainInformation;
	private HttpGetUrl get;
	
	
	public static void main(String[] arg) {
		new TestBankDeposit();
	}
	
	public TestBankDeposit() {
		get = new HttpGetUrl();
		obtainInformation = new ObtainInformation();
		try {
			StringBuilder page = get.getUrl(urlInicio);
			//Leo datos
			obtainInformation.mostrarDatos(obtainInformation.leerDatosUsuario(page));
			//Leo links
			Map<String,String> links = obtainInformation.leerLinks(page);
			System.out.println("Go to bank.");
			String unitUrl = links.get(Menus.BANK.getValue());
			System.out.println("Link... "+unitUrl);
			page = get.getUrl(unitUrl);
			System.out.println("Deposit 1000");
			Map<String,String> params = new HashMap<String,String>();
			params.put("depositAmount", "1000");
			params.put("action", "Deposit");
			params.put("sk", "1");
			page = get.postUrl("http://wwar.storm8.com/bank.php", params);
		} catch (Exception e) {
			System.out.println("Error en el get. "+e.getMessage());
		}
		get.close();
	}
	
}

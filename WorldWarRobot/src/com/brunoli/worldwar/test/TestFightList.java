package com.brunoli.worldwar.test;

import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Enemy;
import com.brunoli.worldwar.parser.ObtainFight;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class TestFightList {

	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private ObtainInformation obtainInformation;
	private ObtainFight obtainFight;
	private HttpGetUrl get;
	
	
	public static void main(String[] arg) {
		new TestFightList();
	}
	
	public TestFightList() {
		get = new HttpGetUrl();
		obtainInformation = new ObtainInformation();
		obtainFight = new ObtainFight();
		try {
			StringBuilder page = get.getUrl(urlInicio);
			//Leo datos
			System.out.println("Leo datos.");
			obtainInformation.mostrarDatos(obtainInformation.leerDatosUsuario(page));
			//Leo links
			System.out.println("Leo links.");
			Map<String,String> links = obtainInformation.leerLinks(page);
			String battleUrl = links.get(Menus.BATTLE.name());
			System.out.println("Link... "+battleUrl);
			page = get.getUrl(battleUrl);
			System.out.println("Fight....");
			List<Enemy> enemies = obtainFight.leerEnemyList(page);
			obtainFight.mostrarEnemys(enemies);
			
			System.out.println("FIN.");
		} catch (Exception e) {
			System.out.println("Error en el get. "+e.getMessage());
		}
		get.close();
	}
}

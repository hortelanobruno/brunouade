package com.brunoli.worldwar.test;

import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.parser.ObtainUnits;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.util.UnitType;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class TestReloadInfoBuilding {

	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private ObtainInformation obtainInformation;
	private ObtainUnits obtainUnits;
	private HttpGetUrl get;
	private DBManager dbManager;
	
	
	public static void main(String[] arg) {
		new TestReloadInfoBuilding();
	}
	
	public TestReloadInfoBuilding() {
		get = new HttpGetUrl();
		obtainInformation = new ObtainInformation();
		obtainUnits = new ObtainUnits();
		dbManager = new DBManager();
		try {
			List<Unit> units = dbManager.getUnits();
			StringBuilder page = get.getUrl(urlInicio);
			//Leo datos
			System.out.println("Leo datos.");
			obtainInformation.mostrarDatos(obtainInformation.leerDatosUsuario(page));
			//Leo links
			System.out.println("Leo links.");
			Map<String,String> links = obtainInformation.leerLinks(page);
			obtainInformation.mostrarDatos(links);
			System.out.println("Go missions.");
			String unitUrl = links.get(Menus.UNITS.name());
			System.out.println("Link... "+unitUrl);
			page = get.getUrl(unitUrl);
			System.out.println("Units....");
			Map<UnitType,String> linksUnits = obtainUnits.leerLinksUnits(page);
			System.out.println("Actualizando units...");
			for(UnitType ut : linksUnits.keySet()){
				System.out.println("Go to "+ut.getValue());
				page = get.getUrl(linksUnits.get(ut));
				obtainUnits.cargarDatosUnits(page, units);
			}
			obtainUnits.mostrarUnits(units);
			dbManager.guardarUnits(units);
			System.out.println("FIN.");
		} catch (Exception e) {
			System.out.println("Error en el get. "+e.getMessage());
		}
		get.close();
	}
}

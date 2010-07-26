package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.util.UnitType;
import com.brunoli.worldwar.util.UtilsWW;

public class ObtainUnits {

	private DBManager dbManager;

	public static void main(String[] arg) {
		ObtainUnits o = new ObtainUnits();
		StringBuilder page = o.leerArchivo("./files/unitPage.txt");
		if (page != null) {
			o.parsearPagina(new StringBuilder(page.toString().replaceAll("\"",
					"\'")));
		} else {
			System.out.println("Page not found.");
			System.exit(0);
		}
	}

	public ObtainUnits() {
		dbManager = new DBManager();
	}

	public void parsearPagina(StringBuilder page) {
		// BAJO TODO A COMILLA SIMPLE
		List<Unit> units = dbManager.getUnits();
		Map<UnitType, String> linksUnits = leerLinksUnits(page);
		cargarDatosUnits(page, units);
		mostrarDatos(linksUnits);
		mostrarUnits(units);
	}

	public void mostrarUnits(List<Unit> units) {
		System.out.println("---------------------------------");
		for (Unit unit : units) {
			System.out.println(unit.toString());
		}
		System.out.println("---------------------------------");
	}

	public void cargarDatosUnits(StringBuilder page, List<Unit> units) {
		int i = 0;
		String url;
		String url2;
		String name = null;
		String cantBuild;
		String cash = null;
		for (String a : page.toString().split("class='equipmentTable'")) {
			if (i > 0) {
				if (!a.contains("equipmentNameLocked")) {
					try {
						url = a.split("src='")[1].split("'")[0];
						name = a.split("'equipmentName'")[1].split("</")[0]
								.split(">")[1];
						cantBuild = a.split("ownedNum")[1].split("</")[0]
								.split(">")[1];
						if(a.split("money.png").length==2){
							cash = a.split("money.png")[1].split("</")[0].split(">")[1];
						}else if(a.split("money.png").length==3){
							cash = a.split("money.png")[2].split("</")[0].split(">")[1];
						}
						url2 = "http://wwar.storm8.com/equipment.php"
								+ a.split("equipmentActionInner")[0]
										.split("/equipment.php")[1].split("'")[0];
						cash = cash.replaceAll(" ", "").trim();
						Unit unit = findUnit(name, units);
						unit.setCantBuild(Integer.parseInt(cantBuild));
						unit.setUrlImg(url);
						unit.setUrlDeploy(url2);
						unit.setPrice(UtilsWW.parsearMoney(cash));
					} catch (Exception ex) {
						System.out.println("Error al cargar los datos de la unit: "+name+".");
					}
				}
			}

			i++;
		}
	}

	public Unit findUnit(String name, List<Unit> units) {
		for (Unit unit : units) {
			if (unit.getName().equalsIgnoreCase(name)) {
				return unit;
			}
		}
		System.out.println("No se encontro unit: " + name);
		return null;
	}

	public Map<UnitType, String> leerLinksUnits(StringBuilder page) {
		String a = page.toString().split("id='sectionTabs'")[1].split("</div>")[0];
		int i = 0;
		Map<UnitType, String> links = new HashMap<UnitType, String>();
		for (String b : a.split("<li")) {
			if (i > 0) {
				links.put(
						UnitType.getType(b.split("<a")[1].split("</a>")[0]
								.split(">")[1]),
						"http://wwar.storm8.com/"
								+ b.split("Request\\('")[1].split("',")[0]);
			}
			i++;
		}

		return links;
	}

	public void mostrarDatos(Map<UnitType, String> datosUsuario) {
		System.out.println("---------------------------------");
		for (UnitType key : datosUsuario.keySet()) {
			System.out.println(key.getValue() + ": " + datosUsuario.get(key));
		}
		System.out.println("---------------------------------");
	}

	public StringBuilder leerArchivo(String pathFile) {
		try {
			Scanner scanner = new Scanner(new File(pathFile));

			StringBuilder page = new StringBuilder();

			while (scanner.hasNext()) {
				page.append(scanner.nextLine());
			}

			return page;
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (Exception e) {
			System.out.println("Exception. " + e.getMessage());
		}
		return null;
	}
}

package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.util.BuildingEnum;
import com.brunoli.worldwar.util.BuildingType;

public class ObtainBuildings {

	public static void main(String[] arg) {
		ObtainBuildings o = new ObtainBuildings();
		StringBuilder page = o.leerArchivo("./files/building.txt");
		if (page != null) {
			o.parsearPagina(page);
		} else {
			System.out.println("Page not found.");
			System.exit(0);
		}
	}

	public ObtainBuildings() {
		// TODO Auto-generated constructor stub
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

	public void parsearPagina(StringBuilder page) {
		List<Building> buildings = leerBuildings(new StringBuilder(page));
		mostrarBuildings(buildings);
	}

	private void mostrarBuildings(List<Building> buildings) {
		System.out.println("---------------------------------");
		for (Building key : buildings) {
			System.out.println(key.toString());
		}
		System.out.println("---------------------------------");
	}

	private List<Building> leerBuildings(StringBuilder page) {
		List<Building> buildings = new ArrayList<Building>();
		// BUILDING
		Building building;
		int i = 0;
		for (String a : page.toString().split("investItem")) {
			if (i > 0) {
				try {
					building = new Building();
					// name
					String name = a.split("reName")[1].split("</div>")[0]
							.split(">")[1];
					building.setName(name);
					// type
					String type = a.split("reInfoItem")[1].split("<span")[0]
							.split(">")[1].replaceAll(":", "").trim();
					building.setCategory(BuildingType.getType(type));
					String typeValue;
					String price;
					String cantBuild;
					String url;
					switch (building.getCategory()) {
					case INCOME:
						// type value
						typeValue = a.split("money.png")[1].split("</span>")[0]
								.split(">")[1].replaceAll(",", "").replaceAll(
								" ", "");
						try {
							building.setTypeValue(Long.parseLong(typeValue));
						} catch (Exception ex) {

						}
						// price
						price = a.split("money.png")[2].split("</span>")[0]
								.split(">")[1].replaceAll(",", "").replaceAll(
								"\\.", "").replaceAll(" ", "");
						try {
							building.setInitialCost(parsearPrice(price));
						} catch (Exception ex) {

						}
						// cantBuild
						cantBuild = a.split("ownedNum")[1].split("</span>")[0]
								.split(">")[1];
						try {
							building.setCantBuild(Integer.parseInt(cantBuild));
						} catch (Exception ex) {

						}
						// url
						url = "/investment.php"
								+ a.split("reBuyActionInner")[0]
										.split("/investment.php")[1].split("'")[0];
						building.setUrl(url);
						break;
					case DEFENSE:
						typeValue = a.split("class='defense'")[1]
								.split("</span>")[0].split(">")[1].replaceAll(
								"\\+", "").replaceAll(" ", "");
						try {
							building.setTypeValue(Long.parseLong(typeValue));
						} catch (Exception ex) {

						}
						// price
						price = a.split("money.png")[1].split("</span>")[0]
								.split(">")[1].replaceAll(",", "").replaceAll(
								"\\.", "").replaceAll(" ", "");
						try {
							building.setInitialCost(parsearPrice(price));
						} catch (Exception ex) {

						}
						// cantBuild
						cantBuild = a.split("ownedNum")[1].split("</span>")[0]
								.split(">")[1];
						try {
							building.setCantBuild(Integer.parseInt(cantBuild));
						} catch (Exception ex) {

						}
						// url
						url = "/investment.php"
								+ a.split("reBuyActionInner")[0]
										.split("/investment.php")[1].split("'")[0];
						building.setUrl(url);
						break;
					case ENERGY:
						typeValue = a.split("class='defense'")[1].split(" ")[0]
								.split(">")[1].replaceAll("\\+", "")
								.replaceAll(" ", "");
						try {
							building.setTypeValue(Long.parseLong(typeValue));
						} catch (Exception ex) {

						}
						// price
						price = a.split("money.png")[1].split("</span>")[0]
								.split(">")[1].replaceAll(",", "").replaceAll(
								"\\.", "").replaceAll(" ", "");
						try {
							building.setInitialCost(parsearPrice(price));
						} catch (Exception ex) {

						}
						// cantBuild
						cantBuild = a.split("ownedNum")[1].split("</span>")[0]
								.split(">")[1];
						try {
							building.setCantBuild(Integer.parseInt(cantBuild));
						} catch (Exception ex) {

						}
						// url
						url = "/investment.php"
								+ a.split("reBuyActionInner")[0]
										.split("/investment.php")[1].split("'")[0];
						building.setUrl(url);
						break;
					}
					buildings.add(building);
				} catch (Exception ex) {
					System.out.println("asdasd");
				}
			}
			i++;
		}
		return buildings;
	}

	private Long parsearPrice(String price) {
		if (price.contains("K")) {
			// k
			price = price.replace("K", "000");
		} else if (price.contains("mil")) {
			price = price.replace("mil", "000000");
		}
		return Long.parseLong(price);
	}

}

package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.event.EventManager;
import com.brunoli.worldwar.util.BuildingType;
import com.brunoli.worldwar.util.UtilsWW;

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

	public void mostrarBuildings(List<Building> buildings) {
		System.out.println("---------------------------------");
		for (Building key : buildings) {
			System.out.println(key.toString());
		}
		System.out.println("---------------------------------");
	}

	public void cargarDatosBuildings(StringBuilder page,
			List<Building> buildings) {
		// BUILDING
		int i = 0;
		Building building;
		for (String a : page.toString().split("investItem")) {
			if (i > 0) {
				try {
					// name
					if (!a.contains("Unlock")) {
						String name = a.split("reName")[1].split("</div>")[0]
								.split(">")[1];
						building = findBuilding(name, buildings);
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
							typeValue = a.split("money.png")[1]
									.split("</span>")[0].split(">")[1]
									.replaceAll(",", "").replaceAll(" ", "");
							try {
								building.setTypeValue(Long.parseLong(typeValue));
							} catch (Exception ex) {

							}
							// price
							price = a.split("money.png")[2].split("</span>")[0]
									.split(">")[1].replaceAll(" ", "");
							try {
								building.setInitialCost(UtilsWW.parsearMoney(price));
							} catch (Exception ex) {

							}
							// cantBuild
							cantBuild = a.split("ownedNum")[1].split("</span>")[0]
									.split(">")[1];
							try {
								building.setCantBuild(Integer
										.parseInt(cantBuild));
							} catch (Exception ex) {

							}
							// url deploy
							url = "http://wwar.storm8.com/investment.php"
									+ a.split("reBuyActionInner")[0]
											.split("/investment.php")[1]
											.split("'")[0];
							building.setUrlDeploy(url);
							// url img
							url = a.split("src='")[1].split("'")[0];
							building.setUrlImg(url);
							break;
						case DEFENSE:
							typeValue = a.split("class='defense'")[1]
									.split("</span>")[0].split(">")[1]
									.replaceAll("\\+", "").replaceAll(" ", "");
							try {
								building.setTypeValue(Long.parseLong(typeValue));
							} catch (Exception ex) {

							}
							// price
							price = a.split("money.png")[1].split("</span>")[0]
									.split(">")[1].replaceAll(" ", "");
							try {
								building.setInitialCost(UtilsWW.parsearMoney(price));
							} catch (Exception ex) {

							}
							// cantBuild
							cantBuild = a.split("ownedNum")[1].split("</span>")[0]
									.split(">")[1];
							try {
								building.setCantBuild(Integer
										.parseInt(cantBuild));
							} catch (Exception ex) {

							}
							// url deploy
							url = "http://wwar.storm8.com/investment.php"
									+ a.split("reBuyActionInner")[0]
											.split("/investment.php")[1]
											.split("'")[0];
							building.setUrlDeploy(url);
							// url img
							url = a.split("src='")[1].split("'")[0];
							building.setUrlImg(url);
							break;
						case ENERGY:
							typeValue = a.split("class='defense'")[1]
									.split(" ")[0].split(">")[1].replaceAll(
									"\\+", "").replaceAll(" ", "");
							try {
								building.setTypeValue(Long.parseLong(typeValue));
							} catch (Exception ex) {

							}
							// price
							price = a.split("money.png")[1].split("</span>")[0]
									.split(">")[1].replaceAll(" ", "");
							try {
								building.setInitialCost(UtilsWW.parsearMoney(price));
							} catch (Exception ex) {

							}
							// cantBuild
							cantBuild = a.split("ownedNum")[1].split("</span>")[0]
									.split(">")[1];
							try {
								building.setCantBuild(Integer
										.parseInt(cantBuild));
							} catch (Exception ex) {

							}
							// url deploy
							url = "http://wwar.storm8.com/investment.php"
									+ a.split("reBuyActionInner")[0]
											.split("/investment.php")[1]
											.split("'")[0];
							building.setUrlDeploy(url);
							// url img
							url = a.split("src='")[1].split("'")[0];
							building.setUrlImg(url);
							break;
						}
						buildings.add(building);
					}
				} catch (Exception ex) {
					EventManager.getInstance().error("Error al cargar info building. ", ex);
				}
			}
			i++;
		}
	}

	private Building findBuilding(String name, List<Building> buildings) {
		for (Building building : buildings) {
			if (building.getName().equalsIgnoreCase(name)) {
				return building;
			}
		}
		System.out.println("No se encontro building: "+name);
		return null;
	}

	public List<Building> leerBuildings(StringBuilder page) {
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
								.split(">")[1].replaceAll(",", "")
								.replaceAll("\\.", "").replaceAll(" ", "");
						try {
							building.setInitialCost(UtilsWW.parsearMoney(price));
						} catch (Exception ex) {

						}
						// cantBuild
						cantBuild = a.split("ownedNum")[1].split("</span>")[0]
								.split(">")[1];
						try {
							building.setCantBuild(Integer.parseInt(cantBuild));
						} catch (Exception ex) {

						}
						// url deploy
						url = "/investment.php"
								+ a.split("reBuyActionInner")[0]
										.split("/investment.php")[1].split("'")[0];
						building.setUrlDeploy(url);
						// url img
						url = a.split("src='")[1].split("'")[0];
						building.setUrlImg(url);
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
								.split(">")[1].replaceAll(",", "")
								.replaceAll("\\.", "").replaceAll(" ", "");
						try {
							building.setInitialCost(UtilsWW.parsearMoney(price));
						} catch (Exception ex) {

						}
						// cantBuild
						cantBuild = a.split("ownedNum")[1].split("</span>")[0]
								.split(">")[1];
						try {
							building.setCantBuild(Integer.parseInt(cantBuild));
						} catch (Exception ex) {

						}
						// url deploy
						url = "/investment.php"
								+ a.split("reBuyActionInner")[0]
										.split("/investment.php")[1].split("'")[0];
						building.setUrlDeploy(url);
						// url img
						url = a.split("src='")[1].split("'")[0];
						building.setUrlImg(url);
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
								.split(">")[1].replaceAll(",", "")
								.replaceAll("\\.", "").replaceAll(" ", "");
						try {
							building.setInitialCost(UtilsWW.parsearMoney(price));
						} catch (Exception ex) {

						}
						// cantBuild
						cantBuild = a.split("ownedNum")[1].split("</span>")[0]
								.split(">")[1];
						try {
							building.setCantBuild(Integer.parseInt(cantBuild));
						} catch (Exception ex) {

						}
						// url deploy
						url = "/investment.php"
								+ a.split("reBuyActionInner")[0]
										.split("/investment.php")[1].split("'")[0];
						building.setUrlDeploy(url);
						// url img
						url = a.split("src='")[1].split("'")[0];
						building.setUrlImg(url);
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

}

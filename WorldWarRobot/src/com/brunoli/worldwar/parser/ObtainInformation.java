package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.util.Menus;

public class ObtainInformation {

	public static void main(String[] arg) {
		ObtainInformation o = new ObtainInformation();
		StringBuilder page = o.leerArchivo("./files/home.txt");
		if (page != null) {
			o.parsearPagina(page);
		} else {
			System.out.println("Page not found.");
			System.exit(0);
		}
	}

	public ObtainInformation() {
	}

	public void parsearPagina(StringBuilder page) {
		// BAJO TODO A COMILLA SIMPLE
		Map<String, String> datosUsuario = leerDatosUsuario(new StringBuilder(
				page));
		mostrarDatos(datosUsuario);
		Map<String, String> links = leerLinks(new StringBuilder(page));
		mostrarDatos(links);
	}

	public Map<String, String> leerLinks(StringBuilder page) {
		Map<String, String> datos = new HashMap<String, String>();
		// MISSION
		String aux = "http://wwar.storm8.com/missions.php"
				+ page.toString().split("/missions.php")[1].split("'>")[0];
		datos.put(Menus.MISSION.getValue(), aux);
		aux = "http://wwar.storm8.com/fight.php"
				+ page.toString().split("/fight.php")[1].split("'>")[0];
		datos.put(Menus.BATTLE.getValue(), aux);
		aux = "http://wwar.storm8.com/equipment.php"
				+ page.toString().split("/equipment.php")[1].split("'>")[0];
		datos.put(Menus.UNITS.getValue(), aux);
		aux = "http://wwar.storm8.com/investment.php"
				+ page.toString().split("/investment.php")[1].split("'>")[0];
		datos.put(Menus.BUILDINGS.getValue(), aux);
		aux = "http://wwar.storm8.com/hospital.php?";
		datos.put(Menus.HOSPITAL.getValue(), aux);
		aux = "http://wwar.storm8.com/bank.php"
				+ page.toString().split("/bank.php")[1].split("'>")[0];
		datos.put(Menus.BANK.getValue(), aux);
		datos.put(Menus.HOME.getValue(), aux.replace("/bank", "/home"));
		// http://wwar.storm8.com/home.php?formNonce=c53d174637936122af34e61842d0ba98f3721e19&h=cccecf39695b47f5b1f33a20dce647778311b9b6#&setTab0Badge=&changeApplicationBadge=0&setTab4Badge=18
		return datos;
	}

	public void mostrarDatos(Map<String, String> datosUsuario) {
		System.out.println("---------------------------------");
		for (String key : datosUsuario.keySet()) {
			System.out.println(key + ": " + datosUsuario.get(key));
		}
		System.out.println("---------------------------------");
	}

	public Map<String, String> leerDatosUsuario(StringBuilder page) {
		Map<String, String> datos = new HashMap<String, String>();
		// LEVEL
		String aux = page.toString().split("levelFrontTopArea")[1]
				.split("</a>")[0].split(">")[2];
		datos.put("Level", aux);
		// ALIANZE SIZE
		aux = page.toString().split("'crewCount'")[1].split("</")[0].split(">")[1];
		datos.put("AlianzeSize", aux);
		// EXPERIENCIA
		aux = page.toString().split("expText")[1].split("</span>")[0]
				.split(">")[1];
		datos.put("Experience", aux);
		// CURRENT ENERGY
		aux = page.toString().split("energyCurrent")[1].split("</span>")[0]
				.split(">")[1];
		datos.put("EnergyCurrent", aux);
		// MAX ENERGY
		aux = page.toString().split("energyMax")[1].split("</span>")[0]
				.split(">")[1];
		datos.put("EnergyMax", aux);
		// CURRENT HEALTH
		aux = page.toString().split("healthCurrent")[1].split("</span>")[0]
				.split(">")[1];
		datos.put("HealthCurrent", aux);
		// MAX HEALTH
		aux = page.toString().split("healthMax")[1].split("</span>")[0]
				.split(">")[1];
		datos.put("HealthMax", aux);
		// CURRENT STAMINA
		aux = page.toString().split("staminaCurrent")[1].split("</span>")[0]
				.split(">")[1];
		datos.put("StaminaCurrent", aux);
		// MAX STAMINA
		aux = page.toString().split("staminaMax")[1].split("</span>")[0]
				.split(">")[1];
		datos.put("StaminaMax", aux);
		// MONEY
		aux = page.toString().split("'cashCurrent'")[1].split("</")[0]
				.split(">")[1];
		datos.put("Money", parsearMony(aux).toString());
		return datos;
	}
	
	public Long parsearMony(String money){
		return Long.parseLong(money.replaceAll(",", "").replaceAll("\\.", ""));
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

	public void leerDatosUsuario(StringBuilder page, Profile profile) {
		// LEVEL
		String aux = page.toString().split("'levelFrontTopArea'")[1]
				.split("</a>")[0].split("'>")[1];
		try {
			profile.setLevel(Integer.parseInt(aux.replaceAll("!", "")));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL LEVEL.");
		}
		// ALIANZE SIZE
		aux = page.toString().split("'crewCount'")[1].split("</")[0].split(">")[1];
		try {
			profile.setAlianzeSize(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL ALIANZE SIZE.");
		}
		// EXPERIENCIA
		aux = page.toString().split("expText")[1].split("</span>")[0]
				.split(">")[1];
		profile.setExperience(aux);
		// CURRENT ENERGY
		aux = page.toString().split("energyCurrent")[1].split("</span>")[0]
				.split(">")[1];
		try {
			profile.setEnergyCurrent(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL CURRENT ENERGY.");
		}

		// MAX ENERGY
		aux = page.toString().split("energyMax")[1].split("</span>")[0]
				.split(">")[1];
		try {
			profile.setEnergyMax(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MAX ENERGY.");
		}
		// CURRENT HEALTH
		aux = page.toString().split("healthCurrent")[1].split("</span>")[0]
				.split(">")[1];
		try {
			profile.setHealthCurrent(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL CURRENT HEALTH.");
		}
		// MAX HEALTH
		aux = page.toString().split("healthMax")[1].split("</span>")[0]
				.split(">")[1];
		try {
			profile.setHealthMax(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MAX HEALTH.");
		}
		// CURRENT STAMINA
		aux = page.toString().split("staminaCurrent")[1].split("</span>")[0]
				.split(">")[1];
		try {
			profile.setStaminaCurrent(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL CURRENT STAMINA.");
		}
		// MAX STAMINA
		aux = page.toString().split("staminaMax")[1].split("</span>")[0]
				.split(">")[1];
		try {
			profile.setStaminaMax(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MAX STAMINA.");
		}
		// MONEY
		aux = page.toString().split("'cashCurrent'")[1].split("</")[0]
				.split(">")[1];
		try {
			profile.setMoney(parsearMony(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MONEY.");
		}
	}

	public void leerLinks(StringBuilder page, Profile profile) {
		Map<String,String> links = leerLinks(page);
		for(String key : links.keySet()){
			profile.getMenuUrls().put(Menus.getType(key), links.get(key));
		}
		
	}
}

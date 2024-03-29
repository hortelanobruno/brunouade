package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.util.FightResultType;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.util.UtilsWW;

public class ObtainInformation {

	public static void main(String[] arg) {
		ObtainInformation o = new ObtainInformation();
		StringBuilder page = o.leerArchivo("./files/homeAtaques.txt");
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
		page = new StringBuilder(page.toString().replaceAll("\"", "'"));
		Map<String, String> datosUsuario = leerDatosUsuario(new StringBuilder(
				page));
		mostrarDatos(datosUsuario);
		Map<String, String> links = leerLinks(new StringBuilder(page));
		mostrarDatos(links);
		System.out.println("Time to gain money: "+getTimeToGainMoney(new StringBuilder(page)));
	}
	
	public Long getTimeToGainMoney(StringBuilder page){
		//'cashType'
		String aux = page.toString().split("'cashType'")[1].split("</")[0].split(">")[1].trim();
		Long time = Long.parseLong(aux.split(":")[1]) +( Long.parseLong(aux.split(":")[0]) * 60);
		return time;
	}
	
	public List<FightResultType> leerUltimosAtaquesRecibidos(StringBuilder page){
		//http://wwar.storm8.com/ajax/getNewsFeedStories.php?selectedTab=fight
		int i=0;
		List<FightResultType> results = new ArrayList<FightResultType>();
		for(String a : page.toString().split("'newsFeedItem'")){
			if(i>0){
				if(a.contains("WON")){
					//GANE
					results.add(FightResultType.WON);
				}else if(a.contains("LOST")){
					//PERDI
					results.add(FightResultType.LOST);
				}
			}
			i++;
		}
		return results;
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
		String aux;
		try {
			aux = page.toString().split("levelFrontTopArea")[1].split("</a>")[0]
					.split(">")[2];
			datos.put("Level", aux.replaceAll("!", ""));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL LEVEL.");
		}
		try {
			// ALIANZE SIZE
			aux = page.toString().split("'crewCount'")[1].split("</")[0]
					.split(">")[1];
			datos.put("AlianzeSize", aux);
		} catch (Exception ex) {
		}
		try {
			// EXPERIENCIA
			aux = page.toString().split("expText")[1].split("</span>")[0]
					.split(">")[1];
			datos.put("Experience", aux);
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EXPERIENCIA.");
		}
		try {
			// CURRENT ENERGY
			aux = page.toString().split("energyCurrent")[1].split("</span>")[0]
					.split(">")[1];
			datos.put("EnergyCurrent", aux);
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL CURRENT ENERGY.");
		}
		try {
			// MAX ENERGY
			aux = page.toString().split("energyMax")[1].split("</span>")[0]
					.split(">")[1];
			datos.put("EnergyMax", aux);
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MAX ENERGY.");
		}
		try {
			// CURRENT HEALTH
			aux = page.toString().split("healthCurrent")[1].split("</span>")[0]
					.split(">")[1];
			datos.put("HealthCurrent", aux);
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL CURRENT HEALTH.");
		}
		try {
			// MAX HEALTH
			aux = page.toString().split("healthMax")[1].split("</span>")[0]
					.split(">")[1];
			datos.put("HealthMax", aux);
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MAX HEALTH.");
		}
		try {
			// CURRENT STAMINA
			aux = page.toString().split("staminaCurrent")[1].split("</span>")[0]
					.split(">")[1];
			datos.put("StaminaCurrent", aux);
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL CURRENT STAMINA.");
		}
		try {
			// MAX STAMINA
			aux = page.toString().split("staminaMax")[1].split("</span>")[0]
					.split(">")[1];
			datos.put("StaminaMax", aux);
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MAX STAMINA.");
		}
		try {
			// MONEY
			aux = page.toString().split("'cashCurrent'")[1].split("</")[0]
					.split(">")[1];
			datos.put("Money", UtilsWW.parsearMoney(aux).toString());
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MONEY.");
		}
		return datos;
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
		String aux;
		try {
			aux = page.toString().split("'levelFrontTopArea'")[1]
			                                   				.split("</a>")[0].split("'>")[1];
			profile.setLevel(Integer.parseInt(aux.replaceAll("!", "")));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL LEVEL.");
		}
		try {
			// ALIANZE SIZE
			aux = page.toString().split("'crewCount'")[1].split("</")[0].split(">")[1];
			profile.setAlianzeSize(Integer.parseInt(aux));
		} catch (Exception ex) {
		}
		// EXPERIENCIA
		aux = page.toString().split("expText")[1].split("</span>")[0]
				.split(">")[1];
		profile.setExperience(aux);
		try {
			// CURRENT ENERGY
			aux = page.toString().split("energyCurrent")[1].split("</span>")[0]
					.split(">")[1];
			profile.setEnergyCurrent(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL CURRENT ENERGY.");
		}
		try {

			// MAX ENERGY
			aux = page.toString().split("energyMax")[1].split("</span>")[0]
					.split(">")[1];
			profile.setEnergyMax(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MAX ENERGY.");
		}
		try {
			// CURRENT HEALTH
			aux = page.toString().split("healthCurrent")[1].split("</span>")[0]
					.split(">")[1];
			profile.setHealthCurrent(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL CURRENT HEALTH.");
		}
		try {
			// MAX HEALTH
			aux = page.toString().split("healthMax")[1].split("</span>")[0]
					.split(">")[1];
			profile.setHealthMax(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MAX HEALTH.");
		}
		try {
			// CURRENT STAMINA
			aux = page.toString().split("staminaCurrent")[1].split("</span>")[0]
					.split(">")[1];
			profile.setStaminaCurrent(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL CURRENT STAMINA.");
		}
		try {
			// MAX STAMINA
			aux = page.toString().split("staminaMax")[1].split("</span>")[0]
					.split(">")[1];
			profile.setStaminaMax(Integer.parseInt(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MAX STAMINA.");
		}
		try {
			// MONEY
			aux = page.toString().split("'cashCurrent'")[1].split("</")[0]
					.split(">")[1];
			profile.setMoney(UtilsWW.parsearMoney(aux));
		} catch (Exception ex) {
			System.out.println("ERROR AL OBTENER EL MONEY.");
		}
	}

	public void leerLinks(StringBuilder page, Profile profile) {
		Map<String, String> links = leerLinks(page);
		for (String key : links.keySet()) {
			profile.getMenuUrls().put(Menus.getType(key), links.get(key));
		}

	}
}

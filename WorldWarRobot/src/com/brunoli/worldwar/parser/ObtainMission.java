package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.brunoli.worldwar.beans.Mission;

public class ObtainMission {

	public static void main(String[] arg) {
		ObtainMission obtainMission = new ObtainMission();
		//mission.txt
		//mission(doitarriba).txt
		//mission(faltanunits).txt
		StringBuilder page = obtainMission.leerArchivo("./files/mission(faltanunits).txt");
		if (page != null) {
			obtainMission.parsearPagina(page);
		} else {
			System.out.println("Page not found.");
			System.exit(0);
		}
	}
	
	public ObtainMission() {
		
	}
	
	public void parsearPagina(StringBuilder page) {
		// BAJO TODO A COMILLA SIMPLE
		page = new StringBuilder(page.toString().replaceAll("\"", "\'"));
		
		if(checkDeployUnit(new StringBuilder(page))){
			String urlDeploy = obtainUrlToDeployUnit(new StringBuilder(page));
			System.out.println("URL Deploy: "+urlDeploy);
		}else{
			Mission missionHeader = leerMissionHeader(new StringBuilder(page));
			System.out.println(missionHeader.getMissionName()+": "+missionHeader.getMissionUrl());
		}
		
		
		
//		List<Mission> missions = leerMissions(new StringBuilder(page));
//		mostrarMissions(missions);
	}

	public void mostrarMissions(List<Mission> missions) {
		System.out.println("---------------------------------");
		for(Mission mission : missions){
			System.out.println(mission.getMissionName()+": "+mission.getMissionUrl());
		}
		System.out.println("---------------------------------");	
	}

	public List<Mission> leerMissions(StringBuilder page) {
		List<Mission> missions = new ArrayList<Mission>();
		String missionName = null;
		String missionUrl = null;
		Mission mission = null;
		int i=0;
		for(String a : page.toString().split("missionName")){
			if(i>0){
				missionName = a.split("</div>")[0].split(">")[1];
				missionUrl = "http://wwar.storm8.com/missions.php"+a.split("/missions.php")[1].split("'")[0];
				mission = new Mission();
				mission.setMissionName(missionName);
				mission.setMissionUrl(missionUrl);
				missions.add(mission);
			}
			i++;
		}
		return missions;
	}
	
	public boolean checkDeployUnit(StringBuilder page){
		if(page.toString().contains("value='Deploy'")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean checkRefillEnergy(StringBuilder page){
		if(page.toString().contains("Refill Energy")){
			return true;
		}else{
			return false;
		}
	}
	
	public String obtainUrlToDeployUnit(StringBuilder page){
		String url = page.toString().split("value='Deploy'")[0].split("btnDoAgain")[1];
		url = "http://wwar.storm8.com/missions.php"+url.split("/missions.php")[1].split("'")[0];
		return url;
	}

	public Mission leerMissionHeader(StringBuilder page){
		Mission mission = null;
		String missionName = null;
		String missionUrl = null;
		String buttonText = "do again";
		int i=0;
		if(page.toString().contains("missionHeader")){
			for(String a : page.toString().split("missionHeader")){
				if(i>0){
					missionName = a.split("</div>")[0].split(">")[1];
					//puede ser do againt o do it
					if(a.contains(buttonText)){
						missionUrl = "http://wwar.storm8.com/missions.php"+a.split("do again")[0].split("/missions.php")[1].split("'")[0];
					}else{
						missionUrl = "http://wwar.storm8.com/missions.php"+a.split("do it")[0].split("/missions.php")[1].split("'")[0];
					}
					mission = new Mission();
					mission.setMissionName(missionName);
					mission.setMissionUrl(missionUrl);
				}
				i++;
			}
		}else if(page.toString().contains("messageBoxSuccess")){
			for(String a : page.toString().split("messageBoxSuccess")){
				if(i>0){
					missionName = "FALTA EL NAMEEEEEEEE";
					//puede ser do againt o do it
					if(a.contains(buttonText)){
						missionUrl = "http://wwar.storm8.com/missions.php"+a.split("/missions.php")[1].split("'")[0];
					}else{
						missionUrl = "http://wwar.storm8.com/missions.php"+a.split("/missions.php")[1].split("'")[0];
					}
					mission = new Mission();
					mission.setMissionName(missionName);
					mission.setMissionUrl(missionUrl);
				}
				i++;
			}
		}
		return mission;
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

	public Mission getMission(String miName, List<Mission> missions) {
		for(Mission mi : missions){
			if(mi.getMissionName().equalsIgnoreCase(miName)){
				return mi;
			}
		}
		return null;
	}

	public Boolean checkDeployUnitsOK(StringBuilder page) {
		if(page.toString().contains("have enough money")){
			return false;
		}else{
			return true;
		}
	}
}

package com.brunoli.worldwar.test;

import java.util.List;
import java.util.Map;

import com.brunoli.beans.Mission;
import com.brunoli.parser.ObtainInformation;
import com.brunoli.parser.ObtainMission;
import com.brunoli.util.Menus;
import com.brunoli.webmanager.HttpGetUrl;

public class TestDoOneMission {

	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private ObtainInformation obtainInformation;
	private ObtainMission obtainMission;
	private HttpGetUrl get;
	
	
	public static void main(String[] arg) {
		new TestDoOneMission();
	}
	
	public TestDoOneMission() {
		get = new HttpGetUrl();
		obtainInformation = new ObtainInformation();
		obtainMission = new ObtainMission();
		try {
			StringBuilder page = get.getUrl(urlInicio);
			//Leo datos
			System.out.println("Leo datos.");
			obtainInformation.mostrarDatos(obtainInformation.leerDatosUsuario(page));
			//Leo links
			System.out.println("Leo links.");
			Map<String,String> links = obtainInformation.leerLinks(page);
			obtainInformation.mostrarDatos(links);
			System.out.println("Go missions.");
			String missionUrl = links.get(Menus.MISSION.name());
			System.out.println("Link... "+missionUrl);
			page = get.getUrl(missionUrl);
			System.out.println("Missions....");
			List<Mission> missions = obtainMission.leerMissions(page);
			Mission mission = obtainMission.getMission("Repel Counterattack",missions);
			page = get.getUrl(mission.getMissionUrl());
			System.out.println("Leo datos.");
			obtainInformation.mostrarDatos(obtainInformation.leerDatosUsuario(page));
			System.out.println("FIN.");
		} catch (Exception e) {
			System.out.println("Error en el get. "+e.getMessage());
		}
		get.close();
	}
}

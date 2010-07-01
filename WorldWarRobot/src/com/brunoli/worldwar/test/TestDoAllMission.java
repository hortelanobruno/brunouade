package com.brunoli.worldwar.test;

import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Mission;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.parser.ObtainMission;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class TestDoAllMission {
	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private ObtainInformation obtainInformation;
	private ObtainMission obtainMission;
	private HttpGetUrl get;

	public static void main(String[] arg) {
		TestDoAllMission t = new TestDoAllMission();
		t.ejecutarAllMissions();
	}

	public TestDoAllMission() {

	}

	public void ejecutarAllMissions() {
		get = new HttpGetUrl();
		obtainInformation = new ObtainInformation();
		obtainMission = new ObtainMission();
		try {
			StringBuilder page = get.getUrl(urlInicio);
			// Leo datos
			System.out.println("Leo datos.");
			Map<String, String> datos = obtainInformation
					.leerDatosUsuario(page);
			obtainInformation.mostrarDatos(datos);

			// Chequeo si tengo energia para procesar misiones
			if (Integer.parseInt(datos.get("EnergyCurrent")) > 60) {
				// Leo links
				System.out.println("Leo links.");
				Map<String, String> links = obtainInformation.leerLinks(page);
				obtainInformation.mostrarDatos(links);
				System.out.println("Go missions.");
				String missionUrl = links.get(Menus.MISSION.getValue());
				System.out.println("Link... " + missionUrl);
				page = get.getUrl(missionUrl);
				consumeAllMission(page);
			} else {
				System.out.println("No tengo energia para procesar misiones");
			}
			System.out.println("FIN.");
		} catch (Exception e) {
			System.out.println("Error en el get. " + e.getMessage());
		}
		get.close();
	}

	public void consumeAllMission(StringBuilder page) {
		try {
			System.out.println("Missions....");
			// Obtengo misiones
			List<Mission> missions = obtainMission.leerMissions(page);
			// Obtengo la mision deseada
			Mission mission = obtainMission.getMission("Repel Counterattack",
					missions);
			// Consumo la primer mision
			System.out.println("Proceso primer mision");
			page = get.getUrl(mission.getMissionUrl());
			// ACA sigo consumiendo hasta el fin
			while (!obtainMission.checkRefillEnergy(page)) {
				// Tengo energia
				if (obtainMission.checkDeployUnit(page)) {
					System.out.println("Tengo que deployar");
					// Tengo que deployar units
					String urlDeploy = obtainMission
							.obtainUrlToDeployUnit(page);
					page = get.getUrl(urlDeploy);
					if(!obtainMission.checkDeployUnitsOK(page)){
						System.out.println("NO TENGO PLATA PARA DEPLOYEAR, ESPERAR PROXIMA RUEDA");
						return;
					}
				}
				// Proceso mision
				System.out.println("Procesando siguiente mision");
				mission = obtainMission.leerMissionHeader(page);
				page = get.getUrl(mission.getMissionUrl());

			}
			System.out.println("Se acabo la energia.");
			// No tengo mas energia
			// Leo datos para el final
			System.out.println("Leo datos.");
			obtainInformation.mostrarDatos(obtainInformation
					.leerDatosUsuario(page));
		} catch (Exception e) {
			System.out.println("Error en el get2. " + e.getMessage());
		}
	}
}

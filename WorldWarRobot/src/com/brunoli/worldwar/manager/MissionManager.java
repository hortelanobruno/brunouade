package com.brunoli.worldwar.manager;

import java.util.List;

import com.brunoli.worldwar.beans.Mission;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.parser.ObtainMission;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class MissionManager {
	
	private ObtainInformation obtainInformation;
	private ObtainMission obtainMission;
	private DBManager dbManager;

	public MissionManager() {
		obtainInformation = new ObtainInformation();
		obtainMission = new ObtainMission();
		dbManager = new DBManager();
	}
	
	public void doAllMission(HttpGetUrl get, StringBuilder pageMission, Profile profile){
		try {
			System.out.println("Missions....");
			// Obtengo misiones
			List<Mission> missions = lerrMisionesDisponibles(get);
			// Obtengo la mision deseada
			Mission mission = obtenerMissionParaHacer(profile,missions);
			while(canDoMission(profile,mission)){
				// Consumo la mision
				System.out.println("Proceso mision: "+mission.getMissionName());
				pageMission = get.getUrl(mission.getMissionUrl());
				//Chequeo si se hizo bien
				// Tengo energia
				if (obtainMission.checkDeployUnit(pageMission)) {
					System.out.println("Tengo que deployar");
					// Tengo que deployar units
					String urlDeploy = obtainMission
							.obtainUrlToDeployUnit(pageMission);
					pageMission = get.getUrl(urlDeploy);
					if(!obtainMission.checkDeployUnitsOK(pageMission)){
						System.out.println("NO TENGO PLATA PARA DEPLOYEAR, ESPERAR PROXIMA RUEDA");
						return;
					}else{
						// Proceso mision
						System.out.println("Procesando mision luego de deployear.");
						mission = obtainMission.leerMissionHeader(pageMission);
						pageMission = get.getUrl(mission.getMissionUrl());
					}
				}
				//Se hizo la mision
				// Actualizo los datos
				obtainInformation.leerDatosUsuario(pageMission, profile);
				// Actualizo las misiones
				missions = obtainMission.leerMissions(pageMission);
				// Obtengo la mision deseada
				mission = obtenerMissionParaHacer(profile,missions);
			}
			System.out.println("Se acabo la energia.");
			// No tengo mas energia
		} catch (Exception e) {
			System.out.println("Error en el get2. " + e.getMessage());
		}
	}

	private List<Mission> lerrMisionesDisponibles(HttpGetUrl get) {
		// http://wwar.storm8.com/missions.php?cat=
		// obtainMission.leerMissions(pageMission)
		String url = "http://wwar.storm8.com/missions.php?cat=";
		List<Mission> missions;
		for(int i = 1 ; i < 10 ; i++){
			System.out.println("Checking tab "+i);
			try {
				missions = obtainMission.leerMissions(get.getUrl(url+i));
				for(Mission m : missions){
					if(m.getPercentCompleted()<100){
						return missions;
					}
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	private boolean canDoMission(Profile profile,
			Mission mission) {
		if(mission!=null){
			if(profile.getEnergyCurrent()>mission.getEnergyRequiered()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	private Mission obtenerMissionParaHacer(Profile profile, List<Mission> missions) {
		for (Mission mission : missions) {
			if(mission.getPercentCompleted()<100){
				if(profile.getAlianzeSize()>= mission.getAlianzeSizeRequiered()){
					return mission;
				}
			}
		}
		//BUsco la mision que me da mejor rentabilidad
		Mission m = null;
		for (Mission mission : missions) {
			if(mission.getPercentCompleted()==100){
				if(m!=null){
					Integer a1 = (m.getMaxMoneyGained()-m.getMinMoneyGained())/2;
					a1 = a1/m.getEnergyRequiered();
					Integer a2 = (mission.getMaxMoneyGained()-mission.getMinMoneyGained())/2;
					a2 = a2/mission.getEnergyRequiered();
					if(a1<a2){
						m = mission;
					}
				}else{
					m=mission;
				}
			}
		}
		return m;
	}
}

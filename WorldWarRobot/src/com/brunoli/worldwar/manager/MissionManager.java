package com.brunoli.worldwar.manager;

import java.util.List;

import com.brunoli.worldwar.beans.Mission;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.event.EventManager;
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
			EventManager.getInstance().info("Missions....");
			// Obtengo misiones
			List<Mission> missions = leerMisionesDisponibles(get);
			// Obtengo la mision deseada
			Mission mission = obtenerMissionParaHacer(profile,missions);
			EventManager.getInstance().info("Energy: "+profile.getEnergyCurrent()+"/"
					+profile.getEnergyMax()+". Mission requiered energy: "+mission.getEnergyRequiered());
			while(canDoMission(profile,mission)){
				// Consumo la mision
				EventManager.getInstance().info("Proceso mision: "+mission.getMissionName()+". Percent: "+mission.getPercentCompleted()+"%.");
				pageMission = get.getUrl(mission.getMissionUrl());
				//Chequeo si se hizo bien
				// Tengo energia
				if (obtainMission.checkDeployUnit(pageMission)) {
					EventManager.getInstance().info("Tengo que deployar");
					// Tengo que deployar units
					String urlDeploy = obtainMission
							.obtainUrlToDeployUnit(pageMission);
					pageMission = get.getUrl(urlDeploy);
					if(!obtainMission.checkDeployUnitsOK(pageMission)){
						EventManager.getInstance().info("NO TENGO PLATA PARA DEPLOYEAR, ESPERAR PROXIMA RUEDA");
						return;
					}else{
						// Proceso mision
						EventManager.getInstance().info("Procesando mision luego de deployear.");
						mission = obtainMission.leerMissionHeader(pageMission);
						pageMission = get.getUrl(mission.getMissionUrl());
					}
				}
				//Se hizo la mision
				// Actualizo los datos
				obtainInformation.leerDatosUsuario(pageMission, profile);
				// Actualizo las misiones
				missions = obtainMission.leerMissions(pageMission);
				dbManager.actualizarMissiones(missions);
				// Obtengo la mision deseada
				mission = obtenerMissionParaHacer(profile,missions);
				EventManager.getInstance().info("Energy: "+profile.getEnergyCurrent()+"/"
						+profile.getEnergyMax()+". Mission requiered energy: "+mission.getEnergyRequiered());
			}
			EventManager.getInstance().info("Se acabo la energia.");
			// No tengo mas energia
		} catch (Exception e) {
			EventManager.getInstance().error("Error en el get2. " + e.getMessage(),e);
		}
	}

	private List<Mission> leerMisionesDisponibles(HttpGetUrl get) {
		// http://wwar.storm8.com/missions.php?cat=
		// obtainMission.leerMissions(pageMission)
		String url = "http://wwar.storm8.com/missions.php?cat=";
		List<Mission> missions;
		for(int i = 1 ; i < 10 ; i++){
			EventManager.getInstance().info("Checking tab "+i);
			try {
				missions = obtainMission.leerMissions(get.getUrl(url+i));
				for(Mission m : missions){
					if(m.getPercentCompleted()<100){
						//aca hay que actualizar con la base unos campos
						dbManager.actualizarMissiones(missions);
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
		//Busco la mision que me da mejor rentabilidad
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

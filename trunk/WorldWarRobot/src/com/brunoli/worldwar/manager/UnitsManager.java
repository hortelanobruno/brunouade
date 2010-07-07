package com.brunoli.worldwar.manager;

import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.event.EventManager;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.parser.ObtainUnits;
import com.brunoli.worldwar.util.UnitType;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class UnitsManager {

	private ObtainUnits obtainUnits;
	private ObtainInformation obtainInformation;
	
	public UnitsManager() {
		obtainUnits = new ObtainUnits();
		obtainInformation = new ObtainInformation();
	}
	
	public void buyUnitsAttack(HttpGetUrl get, StringBuilder pageUnit, Profile profile, String unitAttack){
		try {
			EventManager.getInstance().info("Contruyendo units ataque...");
			obtainInformation.leerDatosUsuario(pageUnit, profile);
			Map<UnitType, String> linksUnits = obtainUnits.leerLinksUnits(pageUnit);
			//voy al tab air
			pageUnit = get.getUrl(linksUnits.get(UnitType.AIR));
			Unit unit = getMejorUnitAtaque(unitAttack,profile.getUnits());
			int cantAbuy = profile.getAlianzeSize()*6-unit.getCantBuild();
			for(int i=0;i<cantAbuy;i++){
				if(profile.getMoney()>unit.getPrice()){
					EventManager.getInstance().info("Contruyendo: "+unit.toString());
					pageUnit = get.getUrl(unit.getUrlDeploy());
					leerUnits(pageUnit,profile);
					unit = getMejorUnitAtaque(unitAttack,profile.getUnits());
					obtainInformation.leerDatosUsuario(pageUnit, profile);
				}else{
					EventManager.getInstance().info("Se me acabo la plata para comprar units.");
					break;
				}
			}
			EventManager.getInstance().info("FIN Contruir units ataque.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void buyUnitsDefense(HttpGetUrl get, StringBuilder pageUnit, Profile profile, String unitDefense){
		try {
			EventManager.getInstance().info("Contruyendo units defensa...");
			obtainInformation.leerDatosUsuario(pageUnit, profile);
			Map<UnitType, String> linksUnits = obtainUnits.leerLinksUnits(pageUnit);
			//voy al tab air
			pageUnit = get.getUrl(linksUnits.get(UnitType.WATER));
			Unit unit = getMejorUnitAtaque(unitDefense,profile.getUnits());
			int cantAbuy = profile.getAlianzeSize()*6-unit.getCantBuild();
			int cantUnit = unit.getCantBuild();
			for(int i=0;i<cantAbuy;i++){
				if(profile.getMoney()>unit.getPrice()){
					EventManager.getInstance().info("Contruyendo: "+unit.toString()+". Unit price: "+unit.getPrice()+". Profile money: "+profile.getMoney()+".");
					pageUnit = get.getUrl(unit.getUrlDeploy());
					leerUnits(pageUnit,profile);
					unit = getMejorUnitAtaque(unitDefense,profile.getUnits());
					obtainInformation.leerDatosUsuario(pageUnit, profile);
					if(cantUnit == unit.getCantBuild()){
						EventManager.getInstance().info("Fin contruccion unit por iteracion invalida.");
						break;
					}else{
						cantUnit = unit.getCantBuild();
					}
				}else{
					EventManager.getInstance().info("Se me acabo la plata para comprar units.");
					break;
				}
			}
			EventManager.getInstance().info("FIN Contruir units defensa.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void leerUnits(StringBuilder pageUnit, Profile profile) {
		obtainUnits.cargarDatosUnits(pageUnit, profile.getUnits());
	}

	private Unit getMejorUnitAtaque(String unitName, List<Unit> units) {
		// IAI Harop UAV
		for(Unit unit : units){
			if(unit.getName().equalsIgnoreCase(unitName)){
				return unit;
			}
		}
		return null;
	}
}

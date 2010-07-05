package com.brunoli.worldwar.manager;

import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.beans.Unit;
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
	
	public void buyUnitsAttack(HttpGetUrl get, StringBuilder pageUnit, Profile profile){
		try {
			System.out.println("Contruyendo units...");
			Map<UnitType, String> linksUnits = obtainUnits.leerLinksUnits(pageUnit);
			//voy al tab air
			pageUnit = get.getUrl(linksUnits.get(UnitType.AIR));
			Unit unit = getMejorUnitAtaque(profile.getUnits());
			int cantAbuy = profile.getAlianzeSize()*6-unit.getCantBuild();
			for(int i=0;i<cantAbuy;i++){
				if(profile.getMoney()>unit.getPrice()){
					System.out.println("Contruyendo: "+unit.toString());
					pageUnit = get.getUrl(unit.getUrlDeploy());
					leerUnits(pageUnit,profile);
					unit = getMejorUnitAtaque(profile.getUnits());
					obtainInformation.leerDatosUsuario(pageUnit, profile);
				}else{
					System.out.println("Se me acabo la plata para comprar units.");
					break;
				}
			}
			System.out.println("FIN Contruir units.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void leerUnits(StringBuilder pageUnit, Profile profile) {
		obtainUnits.cargarDatosUnits(pageUnit, profile.getUnits());
	}

	private Unit getMejorUnitAtaque(List<Unit> units) {
		// IAI Harop UAV
		String n = "IAI Harop UAV";
		for(Unit unit : units){
			if(unit.getName().equalsIgnoreCase(n)){
				return unit;
			}
		}
		return null;
	}
}

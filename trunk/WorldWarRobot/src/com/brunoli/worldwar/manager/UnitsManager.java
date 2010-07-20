package com.brunoli.worldwar.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.event.EventManager;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.parser.ObtainUnits;
import com.brunoli.worldwar.util.UnitType;
import com.brunoli.worldwar.util.UtilsWW;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class UnitsManager {

	private ObtainUnits obtainUnits;
	private ObtainInformation obtainInformation;
	private DBManager dbManager;
	
	public UnitsManager() {
		obtainUnits = new ObtainUnits();
		obtainInformation = new ObtainInformation();
		dbManager = new DBManager();
	}
	
	public void buyUnitsAttack(HttpGetUrl get, StringBuilder pageUnit, Profile profile, String unitAttack){
		try {
			EventManager.getInstance().info("Contruyendo units ataque...");
			obtainInformation.leerDatosUsuario(pageUnit, profile);
			Map<UnitType, String> linksUnits = obtainUnits.leerLinksUnits(pageUnit);
			//voy al tab air
			pageUnit = get.getUrl(linksUnits.get(UnitType.AIR));
			Unit unit = getMejorUnit(unitAttack,profile.getUnits());
			int cantAbuy = profile.getAlianzeSize()*6-unit.getCantBuild();
			for(int i=0;i<cantAbuy;i++){
				if(profile.getMoney()>unit.getPrice()){
					EventManager.getInstance().info("Contruyendo: "+unit.getName()+". Unit price: "+unit.getPrice()+". Profile money: "+profile.getMoney()+". Cant: "+unit.getCantBuild());
					EventManager.getInstance().other("Contruyendo: "+unit.getName()+". Unit price: "+UtilsWW.toMoney(unit.getPrice())+". Profile money: "+UtilsWW.toMoney(profile.getMoney())+". Cant: "+unit.getCantBuild());
					pageUnit = get.getUrl(unit.getUrlDeploy());
					leerUnits(pageUnit,profile);
					unit = getMejorUnit(unitAttack,profile.getUnits());
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
			Unit unit = getMejorUnit(unitDefense,profile.getUnits());
			int cantAbuy = profile.getAlianzeSize()*6-unit.getCantBuild();
			int cantUnit = unit.getCantBuild();
			for(int i=0;i<cantAbuy;i++){
				if(profile.getMoney()>unit.getPrice()){
					EventManager.getInstance().info("Contruyendo: "+unit.getName()+". Unit price: "+unit.getPrice()+". Profile money: "+profile.getMoney()+". Cant: "+unit.getCantBuild());
					EventManager.getInstance().other("Contruyendo: "+unit.getName()+". Unit price: "+UtilsWW.toMoney(unit.getPrice())+". Profile money: "+UtilsWW.toMoney(profile.getMoney())+". Cant: "+unit.getCantBuild());
					pageUnit = get.getUrl(unit.getUrlDeploy());
					leerUnits(pageUnit,profile);
					unit = getMejorUnit(unitDefense,profile.getUnits());
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

	private Unit getMejorUnit(String unitName, List<Unit> units) {
		for(Unit unit : units){
			if(unit.getName().equalsIgnoreCase(unitName)){
				return unit;
			}
		}
		return null;
	}

	public void buyUnitsAttack(HttpGetUrl get, StringBuilder pageUnit,
			Profile profile, List<String> unitsAttack) {
		try {
			EventManager.getInstance().info("Contruyendo units ataque...");
			obtainInformation.leerDatosUsuario(pageUnit, profile);
			Map<UnitType, String> linksUnits = obtainUnits.leerLinksUnits(pageUnit);
			Unit unit = getMejorUnit(unitsAttack,profile);
			//voy al tab
			pageUnit = get.getUrl(linksUnits.get(unit.getUnitType()));
			int cantUnit = getCantUnit(unitsAttack,profile);
			int cantAbuy = profile.getAlianzeSize()*6-cantUnit;
			for(int i=0;i<cantAbuy;i++){
				if(profile.getMoney()>unit.getPrice()){
					EventManager.getInstance().info("Contruyendo: "+unit.getName()+". Unit price: "+unit.getPrice()+". Profile money: "+profile.getMoney()+". Cant: "+unit.getCantBuild());
					EventManager.getInstance().other("Contruyendo: "+unit.getName()+". Unit price: "+UtilsWW.toMoney(unit.getPrice())+". Profile money: "+UtilsWW.toMoney(profile.getMoney())+". Cant: "+unit.getCantBuild());
					pageUnit = get.getUrl(unit.getUrlDeploy());
					leerUnits(pageUnit,profile);
					unit = getMejorUnit(unitsAttack,profile);
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

	private int getCantUnit(List<String> unitsAttack, Profile profile) {
		int cant = 0;
		Unit unit = null;
		for(String un : unitsAttack){
			unit = getMejorUnit(un,profile.getUnits());
			if(unit.getCantBuild()!=null){
				cant += unit.getCantBuild();
			}
		}
		return cant;
	}

	private Unit getMejorUnit(List<String> unitsAttack, Profile profile) {
		List<String> auxUnits = new ArrayList<String>(unitsAttack);
		Collections.reverse(auxUnits);
		Unit unit;
		for(String un : auxUnits){
			unit = getMejorUnit(un,profile.getUnits());
			if(unit.getLevelRequiered()<profile.getLevel()){
				return dbManager.updateUnit(unit);
			}
		}
		return null;
	}

	public void buyUnitsDefense(HttpGetUrl get, StringBuilder page,
			Profile profile, List<String> unitsDefense) {
		try {
			EventManager.getInstance().info("Contruyendo units ataque...");
			obtainInformation.leerDatosUsuario(page, profile);
			Map<UnitType, String> linksUnits = obtainUnits.leerLinksUnits(page);
			Unit unit = getMejorUnit(unitsDefense,profile);
			//voy al tab
			page = get.getUrl(linksUnits.get(unit.getUnitType()));
			int cantUnit = getCantUnit(unitsDefense,profile);
			int cantAbuy = profile.getAlianzeSize()*6-cantUnit;
			for(int i=0;i<cantAbuy;i++){
				if(profile.getMoney()>unit.getPrice()){
					EventManager.getInstance().info("Contruyendo: "+unit.getName()+". Unit price: "+unit.getPrice()+". Profile money: "+profile.getMoney()+". Cant: "+unit.getCantBuild());
					EventManager.getInstance().other("Contruyendo: "+unit.getName()+". Unit price: "+UtilsWW.toMoney(unit.getPrice())+". Profile money: "+UtilsWW.toMoney(profile.getMoney())+". Cant: "+unit.getCantBuild());
					page = get.getUrl(unit.getUrlDeploy());
					leerUnits(page,profile);
					unit = getMejorUnit(unitsDefense,profile);
					obtainInformation.leerDatosUsuario(page, profile);
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
}

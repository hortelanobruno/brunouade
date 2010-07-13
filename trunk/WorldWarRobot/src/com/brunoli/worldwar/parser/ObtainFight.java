package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.beans.Enemy;
import com.brunoli.worldwar.beans.EnemyProfile;
import com.brunoli.worldwar.beans.FightResult;
import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.event.EventManager;
import com.brunoli.worldwar.util.FightResultType;
import com.brunoli.worldwar.util.FileWriterWrapper;
import com.brunoli.worldwar.util.UtilsWW;

public class ObtainFight {

	public static void main(String[] arg) {
		ObtainFight o = new ObtainFight();
		StringBuilder page = o.leerArchivo("./files/fightPeleaGanada.htm");
		if (page != null) {
			o.parsearPagina(page);
		} else {
			System.out.println("Page not found.");
			System.exit(0);
		}
	}

	public ObtainFight() {
		// TODO Auto-generated constructor stub
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
	
	public void parsearPagina(StringBuilder page) {
		page = new StringBuilder(page.toString().replaceAll("\"", "'"));
//		List<Enemy> enemys = leerEnemyList(page);
//		mostrarEnemys(enemys);
		
		
//		EnemyProfile profile = leerEnemyProfile(new StringBuilder(page));
//		Enemy enemy = new Enemy();
//		enemy.setAlianceSize(4);
//		enemy.setProfile(profile);
//		mostrarProfile(profile);
//		System.out.println("Points defense: "+enemy.calcularPointDefense());
		
		FightResult result = resultFight(page);
	}

	public void mostrarProfile(EnemyProfile profile) {
		// TODO Auto-generated method stub
		System.out.println("Fight won: "+profile.getBattleWon());
		System.out.println("Fight lost: "+profile.getBattleLost());
		for(Unit unit: profile.getUnits().keySet()){
			System.out.println("Unit: "+unit.getName()+". Cant: "+profile.getUnits().get(unit));
		}
		for(Building building: profile.getBuildings().keySet()){
			System.out.println("Building: "+building.getName()+". Cant: "+profile.getBuildings().get(building));
		}
	}

	public EnemyProfile leerEnemyProfile(StringBuilder page) {
		EnemyProfile profile = new EnemyProfile();
		//leo url attack
		profile.setAttackUrl("http://wwar.storm8.com/fight.php"+page.toString().split("/fight.php")[1].split("'")[0]);
		//leo battles
		profile.setBattleWon(Integer.parseInt(page.toString().split("class='statsCol4'")[1].split("</")[0].split(">")[1].replaceAll(" ", "").replaceAll("\\n", "")));
		profile.setBattleLost(Integer.parseInt(page.toString().split("class='statsCol4'")[2].split("</")[0].split(">")[1].replaceAll(" ", "").replaceAll("\\n", "")));
		int i=0;
		String url;
		String cant;
		DBManager dbManager = new DBManager();
		Unit unit;
		int j=0;
		//leo units
		for(String c : page.toString().split("class='equipmentItems'")){
			if(i>0&&i<5){
				j=0;
				for(String d : c.split("<img")){
					if(j>0){
						url = d.split("src='")[1].split("'")[0];
						unit = dbManager.getUnitByUrlImg(url);
						if(unit!=null){
							cant = d.split("<div>x")[1].split("<")[0];
							profile.getUnits().put(unit, Integer.parseInt(cant));
						}else{
							//System.out.println("URL NOT FOUND FOR UNIT: "+url);
						}
					}
					j++;
				}
			}
			i++;
		}
		//leo buildings
		try{
			String a = page.toString().split("Buildings")[1];
			i=0;
			Building building;
			for(String img : a.split("<img")){
				if(i>0){
					url = img.split("src='")[1].split("'")[0];
					building = dbManager.getBuildingByUrlImg(url);
					if(building!=null){
						cant = img.split("<div>x")[1].split("<")[0];
						profile.getBuildings().put(building, Integer.parseInt(cant));
					}else{
						System.out.println("URL NOT FOUND FOR BUILDING: "+url);
					}
				}
				i++;
			}
		}catch(Exception ex){
			
		}
		return profile;
	}


	public void mostrarEnemys(List<Enemy> enemys) {
		System.out.println("---------------------------------");
		for (Enemy key : enemys) {
			System.out.println(key.toString());
		}
		System.out.println("---------------------------------");
	}

	public List<Enemy> leerEnemyList(StringBuilder page) {
		List<Enemy> enemys = new ArrayList<Enemy>();
		// BUILDING
		Enemy enemy;
		int i = 0;
		for (String a : page.toString().split("fightItem")) {
			if (i > 0) {
				try {
					enemy = new Enemy();
					// name
					String name = a.split("class='fightMobster'")[1].split("</a>")[0]
							.split("'>")[1];
					enemy.setName(name);
					String url = "http://wwar.storm8.com/profile.php"+a.split("/profile.php")[1].split("'")[0];
					enemy.setProfileUrl(url);
					String level = a.split("Level")[1].split("</div>")[0]
					        .replaceAll(" ", "");
					enemy.setLevel(Integer.parseInt(level));
					String alianceSize = a.split("fightMobSize")[1].split("</td>")[0].split(">")[1].replaceAll(" ", "").replaceAll("\\n", "");
					enemy.setAlianceSize(Integer.parseInt(alianceSize));
					enemys.add(enemy);
				} catch (Exception ex) {
					EventManager.getInstance().error("Error al leer enemy list. ", ex);
				}
			}
			i++;
		}
		return enemys;
	}

	public FightResult resultFight(StringBuilder page) {
		FightResult result = new FightResult();
		if(page.toString().contains("won")){
			//WON
			result.setResult(FightResultType.WON);
			try{
				String a = page.toString().split("You took")[1].split("gained")[0].split("</")[0];
				result.setMoney(UtilsWW.parsearMoney(a.split(">")[a.split(">").length-1]));
			}catch(Exception ex){
				System.out.println("Error al obtener la plata ganada. "+ex.getMessage());
				FileWriterWrapper fww = new FileWriterWrapper("./files/errores/errorMonyFightGanada.txt");
				fww.write(page.toString());
				System.exit(0);
			}
		}else if(page.toString().contains("lost")){
			//LOST
			result.setResult(FightResultType.LOST);
		}else if(page.toString().contains("retreating")){
			//RETRITMENT
			result.setResult(FightResultType.FORCES_RETRITMENT);
		}else{
			result.setResult(FightResultType.OTHER);
			System.out.println("Error fight result. Grabando archivo.");
			FileWriterWrapper fww = new FileWriterWrapper("./files/errores/errorFightResult.txt");
			fww.write(page.toString());
		}
		return result;
	}
	

	public String obtainAttackAgainUrl(StringBuilder page) {
		String url = "http://wwar.storm8.com/fight.php"+page.toString().split("'Attack Again'")[1].split("/>")[0].split("/fight.php")[1].split("'")[0];
		return url;
	}

}

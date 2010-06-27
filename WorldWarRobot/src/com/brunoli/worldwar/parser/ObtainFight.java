package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.brunoli.worldwar.beans.Enemy;
import com.brunoli.worldwar.beans.EnemyProfile;
import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.DBManager;

public class ObtainFight {

	public static void main(String[] arg) {
		ObtainFight o = new ObtainFight();
		StringBuilder page = o.leerArchivo("./files/fightJugador1.htm");
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
		//List<Enemy> enemys = leerEnemyList(page);
		//mostrarEnemys(enemys);
		EnemyProfile profile = leerEnemyProfile(new StringBuilder(page));
		mostrarProfile(profile);
	}

	private void mostrarProfile(EnemyProfile profile) {
		// TODO Auto-generated method stub
		System.out.println("Fight won: "+profile.getBattleWon());
		System.out.println("Fight lost: "+profile.getBattleLost());
		for(Unit unit: profile.getUnits().keySet()){
			System.out.println("Unit: "+unit.getName()+". Cant: "+profile.getUnits().get(unit));
		}
	}

	private EnemyProfile leerEnemyProfile(StringBuilder page) {
		EnemyProfile profile = new EnemyProfile();
		profile.setBattleWon(Integer.parseInt(page.toString().split("class='statsCol4'")[1].split("</")[0].split(">")[1].replaceAll(" ", "")));
		profile.setBattleLost(Integer.parseInt(page.toString().split("class='statsCol4'")[2].split("</")[0].split(">")[1].replaceAll(" ", "")));
		int i=0;
		String url;
		String cant;
		DBManager dbManager = new DBManager();
		Unit unit;
		int j=0;
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
							System.out.println("URL NOT FOUND: "+url);
						}
					}
					j++;
				}
			}
			i++;
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
					String url = "/profile.php"+a.split("/profile.php")[1].split("'")[0];
					enemy.setProfileUrl(url);
					String level = a.split("Level")[1].split("</div>")[0]
					        .replaceAll(" ", "");
					enemy.setLevel(Integer.parseInt(level));
					String alianceSize = a.split("fightMobSize")[1].split("</td>")[0].split(">")[1].replaceAll(" ", "").replaceAll("\\n", "");
					enemy.setAlianceSize(Integer.parseInt(alianceSize));
					enemys.add(enemy);
				} catch (Exception ex) {
					System.out.println("asdasd");
				}
			}
			i++;
		}
		return enemys;
	}

}

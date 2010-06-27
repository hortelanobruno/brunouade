package com.brunoli.worldwar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.brunoli.beans.Enemy;

public class ObtainFight {

	public static void main(String[] arg) {
		ObtainFight o = new ObtainFight();
		StringBuilder page = o.leerArchivo("./files/fightList.htm");
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
		List<Enemy> enemys = leerEnemyList(new StringBuilder(page));
		mostrarEnemys(enemys);
		getProfileEnemy(enemys.get(0));
	}

	private void getProfileEnemy(Enemy enemy) {
		// TODO Auto-generated method stub
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

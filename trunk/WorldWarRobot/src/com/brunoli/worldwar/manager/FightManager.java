package com.brunoli.worldwar.manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Enemy;
import com.brunoli.worldwar.beans.EnemyProfile;
import com.brunoli.worldwar.beans.FightResult;
import com.brunoli.worldwar.beans.FightStats;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.parser.ObtainFight;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.util.FightResultType;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class FightManager {

	private ObtainFight obtainFight;
	private ObtainInformation obtainInformation;
	private Map<String, FightStats> fightStats;

	public FightManager() {
		obtainFight = new ObtainFight();
		obtainInformation = new ObtainInformation();
		fightStats = new HashMap<String, FightStats>();
	}

	public void startFighting(HttpGetUrl httpGet, Profile profile) {
		String urlPageFight = profile.getMenuUrls().get(Menus.BATTLE);
		String attackAgainUrl = null;
		StringBuilder pageFight = null;
		StringBuilder pageEnemy = null;
		EnemyProfile enemyProfile = null;
		FightResult fightResult = null;
		try {
			while (hasEnergyToAttack(profile)) {
				// 1 - Voy a la pagina de FIGHT
				pageFight = httpGet.getUrl(urlPageFight);
				// 2 - Obtengo lista de enemies
				List<Enemy> newEnemies = obtainFight.leerEnemyList(pageFight);
				// 3 - Elijo mejor enemy
				Enemy enemyToAttack = elegirMejorEnemy(newEnemies);
				if (enemyToAttack != null) {
					// 3 - Voy al profile de un enemy
					pageEnemy = httpGet.getUrl(enemyToAttack.getProfileUrl());
					// 4 - Leo el profile
					enemyProfile = obtainFight.leerEnemyProfile(pageEnemy);
					enemyToAttack.setProfile(enemyProfile);
					// 5 - Calculo si le puedo ganar
					if (canAttack(profile, enemyToAttack)) {
						System.out.println("Atacando a "
								+ enemyToAttack.getName());
						// 6 - Attack
						pageEnemy = httpGet.getUrl(enemyToAttack.getProfile()
								.getAttackUrl());
						fightResult = obtainFight.resultFight(pageEnemy);
						mostrarResultadoFight(profile, enemyToAttack,fightResult);
						if (fightResult.getResult().equals(FightResultType.WON)) {
							// WON
							recargarInfoProfile(profile, pageEnemy);
							recargoFightStats(enemyToAttack, fightResult);
							if (sigoAtacando(enemyToAttack)) {
								do {
									System.out.println("Atacando de nuevo a "
											+ enemyToAttack.getName());
									attackAgainUrl = obtainFight
											.obtainAttackAgainUrl(pageEnemy);
									pageEnemy = httpGet.getUrl(attackAgainUrl);
									fightResult = obtainFight
											.resultFight(pageEnemy);
									recargarInfoProfile(profile, pageEnemy);
									recargoFightStats(enemyToAttack,
											fightResult);
									mostrarResultadoFight(profile, enemyToAttack,fightResult);
								} while (fightResult.getResult().equals(
										FightResultType.WON)
										&& hasEnergyToAttack(profile));
							}
						} else if (fightResult.getResult().equals(FightResultType.LOST)) {
							// LOST
							recargarInfoProfile(profile, pageEnemy);
							recargoFightStats(enemyToAttack, fightResult);
						} else {
							// RETRITMENT
						}
					}
				}
			}
			System.out.println("SE ME ACABO LA ENERGIA. FIN.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void mostrarResultadoFight(Profile profile, Enemy enemy, FightResult fightResult) {
		switch (fightResult.getResult()) {
		case WON:
			System.out.print("Le gane a "+enemy.getName()+". Recaude "+fightResult.getMoney()+". ");
			System.out.print("Health: "+profile.getHealthCurrent()+"/"+profile.getHealthMax()+". ");
			System.out.println("Stamina: "+profile.getStaminaCurrent()+"/"+profile.getStaminaMax()+".");
			break;
		case LOST:
			System.out.print("Perdi con "+enemy.getName()+". ");
			System.out.print("Health: "+profile.getHealthCurrent()+"/"+profile.getHealthMax()+". ");
			System.out.println("Stamina: "+profile.getStaminaCurrent()+"/"+profile.getStaminaMax()+".");
			break;
		case FORCES_RETRITMENT:
			System.out.println(enemy.getName()+" se retiro.");
			break;
		}
	}

	private boolean sigoAtacando(Enemy enemy) {
		FightStats stats = fightStats.get(enemy.getName());
		if (stats.isRentable()) {
			return true;
		} else {
			return false;
		}
	}

	private void recargoFightStats(Enemy enemy, FightResult fightResult) {
		if (fightStats.containsKey(enemy.getName())) {
			// LO TENGO
			fightStats.get(enemy.getName()).actualizarStats(fightResult);
		} else {
			FightStats stats = new FightStats();
			stats.actualizarStats(fightResult);
			fightStats.put(enemy.getName(), stats);
		}
	}

	private void recargarInfoProfile(Profile profile, StringBuilder page) {
		obtainInformation.leerDatosUsuario(page, profile);
	}

	private boolean canAttack(Profile profile, Enemy enemyToAttack) {
		Integer enemyDefensePoints = enemyToAttack.calcularPointDefense();
		Integer myAttackPoints = profile.calcularPointAttack();
		if (myAttackPoints >= (enemyDefensePoints + Profile.DIFF_POINT_MINIMA)) {
			return true;
		}
		return false;
	}

	private boolean hasEnergyToAttack(Profile profile) {
		if (profile.getStaminaCurrent() > 0 && profile.getHealthCurrent() > 27) {
			return true;
		}
		return false;
	}

	private Enemy elegirMejorEnemy(List<Enemy> newEnemies) {
		Collections.sort(newEnemies, new EnemyComparator());
		FightStats stats = null;
		for (Enemy enemy : newEnemies) {
			if (!fightStats.containsKey(enemy.getName())) {
				// NO LO TENGO ASI QUE PODRIA SER.
				return enemy;
			} else {
				// SI LO TENGO, CALCULO SI ES RENTABLE
				stats = fightStats.get(enemy.getName());
				if (stats.isRentable()) {
					return enemy;
				}
			}
		}
		return null;
	}

	private class EnemyComparator implements Comparator<Enemy> {
		// Comparator interface requires defining compare method.
		public int compare(Enemy enemy1, Enemy enemy2) {
			return enemy1.getAlianceSize().compareTo(enemy2.getAlianceSize());
		}
	}

}

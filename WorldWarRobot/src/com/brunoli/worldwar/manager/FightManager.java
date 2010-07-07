package com.brunoli.worldwar.manager;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.brunoli.worldwar.event.EventManager;
import com.brunoli.worldwar.parser.ObtainFight;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.test.RunnableAll;
import com.brunoli.worldwar.util.FightResultType;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class FightManager {

	private ObtainFight obtainFight;
	private ObtainInformation obtainInformation;
	private Map<String, FightStats> fightStats;
	private List<String> enemyRetired;
	private Calendar initTime;

	public FightManager() {
		enemyRetired = new ArrayList<String>();
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
		initTime = Calendar.getInstance();
		Long moneyGained = 0L;
		try {
			while (seguirPeleando()) {
				// 1 - Voy a la pagina de FIGHT
				pageFight = httpGet.getUrl(urlPageFight);
				recargarInfoProfile(profile, pageFight);
				if (hasEnergyToAttack(profile)) {
					// 2 - Obtengo lista de enemies
					List<Enemy> newEnemies = obtainFight
							.leerEnemyList(pageFight);
					// 3 - Elijo mejor enemy
					Enemy enemyToAttack = elegirMejorEnemy(newEnemies);
					if (enemyToAttack != null) {
						// 3 - Voy al profile de un enemy
						pageEnemy = httpGet.getUrl(enemyToAttack
								.getProfileUrl());
						// 4 - Leo el profile
						enemyProfile = obtainFight.leerEnemyProfile(pageEnemy);
						enemyToAttack.setProfile(enemyProfile);
						// 5 - Calculo si le puedo ganar
						if (canAttack(profile, enemyToAttack)) {
							EventManager.getInstance().info("Atacando a "
									+ enemyToAttack.getName());
							// 6 - Attack
							pageEnemy = httpGet.getUrl(enemyToAttack
									.getProfile().getAttackUrl());
							fightResult = obtainFight.resultFight(pageEnemy);
							if (fightResult.getResult().equals(
									FightResultType.WON)) {
								// WON
								recargarInfoProfile(profile, pageEnemy);
								recargoFightStats(enemyToAttack, fightResult);
								moneyGained += fightResult.getMoney();
								mostrarResultadoFight(profile, enemyToAttack,
										fightResult);
								if (hasEnergyToAttack(profile)
										&& sigoAtacando(enemyToAttack)) {
									do {
										EventManager.getInstance().info("Atacando de nuevo a "
														+ enemyToAttack
																.getName());
										attackAgainUrl = obtainFight
												.obtainAttackAgainUrl(pageEnemy);
										pageEnemy = httpGet
												.getUrl(attackAgainUrl);
										fightResult = obtainFight
												.resultFight(pageEnemy);
										recargarInfoProfile(profile, pageEnemy);
										recargoFightStats(enemyToAttack,
												fightResult);
										if (fightResult != null
												&& fightResult.getMoney() != null) {
											moneyGained += fightResult
													.getMoney();
										}
										mostrarResultadoFight(profile,
												enemyToAttack, fightResult);
									} while (fightResult.getResult().equals(
											FightResultType.WON)
											&& hasEnergyToAttack(profile));
									if (fightResult.getResult().equals(
											FightResultType.FORCES_RETRITMENT)) {
										enemyRetired.add(enemyToAttack
												.getName());
									}
								}
							} else if (fightResult.getResult().equals(
									FightResultType.LOST)) {
								// LOST
								recargarInfoProfile(profile, pageEnemy);
								recargoFightStats(enemyToAttack, fightResult);
								mostrarResultadoFight(profile, enemyToAttack,
										fightResult);
							} else {
								// RETRITMENT
								enemyRetired.add(enemyToAttack.getName());
								mostrarResultadoFight(profile, enemyToAttack,
										fightResult);
							}
						} else {
							enemyRetired.add(enemyToAttack.getName());
						}
					}
				} else {
					EventManager.getInstance().info("SE ME ACABO LA ENERGIA. "+"Health: " + profile.getHealthCurrent() + "/"
							+ profile.getHealthMax() + ". "+"Stamina: " + profile.getStaminaCurrent() + "/"
							+ profile.getStaminaMax() + ". FIN.");
					break;
				}
			}
			EventManager.getInstance().info("Fin peleas. Money ganada : " + moneyGained
					+ " .");
		} catch (Exception e) {
			EventManager.getInstance().error("Exception222. ",e);
		}

	}

	private boolean seguirPeleando() {
		Calendar aux = Calendar.getInstance();
		long timeMax = 1000 * 60 * 5;
		if (initTime.getTimeInMillis() + timeMax > aux.getTimeInMillis()) {
			return true;
		} else {
			EventManager.getInstance().info("Se me acabo el tiempo para pelear");
			return false;
		}
	}

	private void mostrarResultadoFight(Profile profile, Enemy enemy,
			FightResult fightResult) {
		switch (fightResult.getResult()) {
		case WON:
			EventManager.getInstance().info("Le gane a " + enemy.getName() + ". Recaude "
					+ fightResult.getMoney() + ". "+"Health: " + profile.getHealthCurrent() + "/"
					+ profile.getHealthMax() + ". "+"Stamina: " + profile.getStaminaCurrent() + "/"
					+ profile.getStaminaMax() + ".");
			break;
		case LOST:
			EventManager.getInstance().info("Perdi con " + enemy.getName() + ". "+"Health: " + profile.getHealthCurrent() + "/"
					+ profile.getHealthMax() + ". "+"Stamina: " + profile.getStaminaCurrent() + "/"
					+ profile.getStaminaMax() + ".");
			break;
		case FORCES_RETRITMENT:
			EventManager.getInstance().info(enemy.getName() + " se retiro. "+"Health: " + profile.getHealthCurrent() + "/"
					+ profile.getHealthMax() + ". "+"Stamina: " + profile.getStaminaCurrent() + "/"
					+ profile.getStaminaMax() + ".");
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
		EventManager.getInstance().info("Checking enemy " + enemyToAttack.getName()
				+ " can attack: MyAttackPoints: " + myAttackPoints
				+ ". EnemyDefensePoints:" + enemyDefensePoints + ".");
		if (myAttackPoints >= (enemyDefensePoints + RunnableAll.DIFF_POINT_MINIMA)) {
			return true;
		}
		return false;
	}

	private boolean hasEnergyToAttack(Profile profile) {
		if (profile.getStaminaCurrent() > 0 && profile.getHealthCurrent() > 27) {
			return true;
		}else if (profile.getStaminaCurrent() == 0) {
			EventManager.getInstance().info("Duermo 2 min para recuperar stamina");
			try {
				Thread.sleep(1000 * 120);
			} catch (InterruptedException e) {
			}
		}
		return false;
	}

	private Enemy elegirMejorEnemy(List<Enemy> newEnemies) {
		Collections.sort(newEnemies, new EnemyComparator());
		FightStats stats = null;
		for (Enemy enemy : newEnemies) {
			if (!enemyRetired.contains(enemy.getName())) {
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
		}
		return null;
	}

	private class EnemyComparator implements Comparator<Enemy> {
		// Comparator interface requires defining compare method.
		public int compare(Enemy enemy1, Enemy enemy2) {
			return enemy1.getAlianceSize().compareTo(enemy2.getAlianceSize());
		}
	}

	public boolean canDoAttacks(Profile profile) {
		return hasEnergyToAttack(profile);
	}

}

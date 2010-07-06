package com.brunoli.worldwar.beans;

import java.util.ArrayList;
import java.util.List;

import com.brunoli.worldwar.test.RunnableAll;

public class FightStats {

	private Integer fightsWon = 0;
	private Integer fightsLost = 0;
	private Double avgMoneyGain = 0D;
	private Long totalMoneyGain = 0L;
	private List<Long> moneyWon;
	

	public FightStats() {
		moneyWon = new ArrayList<Long>();
	}
	
	public List<Long> getMoneyWon() {
		return moneyWon;
	}
	
	public void setMoneyWon(List<Long> moneyWon) {
		this.moneyWon = moneyWon;
	}

	public Integer getFightsWon() {
		return fightsWon;
	}

	public void setFightsWon(Integer fightsWon) {
		this.fightsWon = fightsWon;
	}

	public Integer getFightsLost() {
		return fightsLost;
	}

	public void setFightsLost(Integer fightsLost) {
		this.fightsLost = fightsLost;
	}

	public Double getAvgMoneyGain() {
		return avgMoneyGain;
	}

	public void setAvgMoneyGain(Double avgMoneyGain) {
		this.avgMoneyGain = avgMoneyGain;
	}

	public Long getTotalMoneyGain() {
		return totalMoneyGain;
	}

	public void setTotalMoneyGain(Long totalMoneyGain) {
		this.totalMoneyGain = totalMoneyGain;
	}

	public boolean isRentable() {
		if (fightsLost >= (fightsWon + 2)) {
			return false;
		}
		if (avgMoneyGain >= RunnableAll.MINIMO_RENTABILIDAD) {
			return true;
		} else {
			return false;
		}
	}

	public void actualizarStats(FightResult fightResult) {
		switch (fightResult.getResult()) {
		case WON:
			moneyWon.add(fightResult.getMoney());
			fightsWon++;
			totalMoneyGain+=fightResult.getMoney();
			avgMoneyGain = calcularAvg();
			break;
		case LOST:
			fightsLost++;
			break;
		case FORCES_RETRITMENT:
			break;
		}
	}

	private Double calcularAvg() {
		Double aux = 0D;
		for (Long money : moneyWon) {
			aux += money.doubleValue();
		}
		aux = aux/moneyWon.size();
		return aux;
	}

}

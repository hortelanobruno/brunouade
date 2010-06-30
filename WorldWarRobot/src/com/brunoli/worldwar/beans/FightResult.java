package com.brunoli.worldwar.beans;

import com.brunoli.worldwar.util.FightResultType;

public class FightResult {

	private FightResultType result;
	private Long money;
	
	public FightResult() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getMoney() {
		return money;
	}
	
	public FightResultType getResult() {
		return result;
	}
	
	public void setMoney(Long money) {
		this.money = money;
	}
	
	public void setResult(FightResultType result) {
		this.result = result;
	}
	
}

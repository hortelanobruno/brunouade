package com.brunoli.worldwar.test;

import com.brunoli.worldwar.event.EventManager;

public class TestLog {

	public static void main(String[] args){
		new TestLog();
	}
	
	public TestLog() {
		EventManager.getInstance().info("Anda.");
	}
}

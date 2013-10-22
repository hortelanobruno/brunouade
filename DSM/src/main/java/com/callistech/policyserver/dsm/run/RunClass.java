package com.callistech.policyserver.dsm.run;

import com.callistech.policyserver.dsm.core.DSMCore;

public class RunClass {

	public static void main(String args[]) {
		DSMCore core = new DSMCore();
		core.init();
		core.start();
	}
}

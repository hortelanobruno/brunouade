package com.brunoli.worldwar.db.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.MissionEntity;

public class MissionEntityControllerTest {

	public MissionEntityControllerTest() {
		solve();
	}

	public static void main(String[] args) {
		new MissionEntityControllerTest();
	}

	private void solve() {
		MissionEntityController controller = new MissionEntityController();
		List<MissionEntity> missions = new ArrayList<MissionEntity>();
		MissionEntity mission = null;
		// ////////////////////////////
		try {
			controller.destroyAll();
			Scanner scan = new Scanner(new File("./files/data/missions.txt"));
			//Leo archivo
			StringBuilder page = new StringBuilder();
			while (scan.hasNext()) {
				page.append(scan.nextLine()+"/n");
			}
			Integer tab = null;
			int i=0;
			int j=0;
			for(String a : page.toString().split("MISSIONS - TAB")){
				if(i>0){
					tab = Integer.parseInt(a.split("#")[1].split("/n")[0].replaceAll(" ", ""));
					for(String b : a.split("/n")){
						if(j>4){
							if(!b.isEmpty()){
								if(b.contains("Level:")){
									mission.setLevelRequiered(Integer.parseInt(b.replaceAll("Level:", "").replaceAll(" ", "").replaceAll("/n", "")));
								} else
								if(b.contains("Allies:")){
									mission.setAlianzeSizeRequiered(Integer.parseInt(b.replaceAll("Allies:", "").replaceAll(" ", "").replaceAll("/n", "")));
								} else
								if(b.contains("Energy:")){
									mission.setEnergyRequiered(Integer.parseInt(b.replaceAll("Energy:", "").replaceAll(" ", "").replaceAll("/n", "")));
								} else
								if(b.contains("Units:")){
									if(!b.replaceAll("Units:", "").replaceAll(" ", "").replaceAll("/n", "").equalsIgnoreCase("None")){
										if(b.contains("(X)")){
											//ESTE ES UN CASO ESPECIAL
											mission.getUnitsRequiered().put("Astute Class Submarine", 50);
											mission.getUnitsRequiered().put("CG(X) Guided Missile Cruiser", 50);
											mission.getUnitsRequiered().put("Stealth Infiltrator", 1);
										}else{
											for(String aa : b.replaceAll("Units:", "").replaceAll(" ", "").replaceAll("/n", "").split("\\(")){
												if(!aa.isEmpty()){
													Integer cant = Integer.parseInt(aa.split("\\)")[0]);
													String name = aa.split("\\)")[1].trim();
													mission.getUnitsRequiered().put(name, cant);
												}
											}
										}
									}
								} else
								if(b.contains("Possible Money Gained:")){
									mission.setMinMoneyGained(Integer.parseInt(b.replaceAll("Possible Money Gained:", "").split("-")[0].replaceAll("\\$", "").trim().replaceAll(",", "").replaceAll("//.", "")));
									mission.setMaxMoneyGained(Integer.parseInt(b.replaceAll("Possible Money Gained:", "").split("-")[1].replaceAll("\\$", "").trim().replaceAll(",", "").replaceAll("//.", "")));
								} else
								if(b.contains("Possible Loot Gained:")){
									mission.setPosibleLoot(b.replaceAll("Possible Loot Gained:", "").trim());
								} else
								if(b.contains("Experience Gained:")){
									controller.create(mission);
								} else{
									//NAME
									mission = new MissionEntity();
									mission.setMissionName(b.replaceAll("/n", ""));
									mission.setTabIndex(tab);

								}
							}
						}
						j++;
					}
				}
				i++;
			}
			
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error. " + e.getMessage());
		} catch (NonexistentEntityException e) {
			System.out.println("Error2. " + e.getMessage());
		}

	}
}

package com.brunoli.worldwar.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Enemy;
import com.brunoli.worldwar.beans.EnemyProfile;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.event.EventManager;
import com.brunoli.worldwar.parser.ObtainFight;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class CommentManager {

	private ObtainFight obtainFight;
	private List<String> enemysNames = new ArrayList<String>();
	
	
	public CommentManager() {
		obtainFight = new ObtainFight();
	}
	
	public void promoteAllianzeCode(String code, HttpGetUrl httpGet, Profile profile){
		try{
			if(enemysNames.size()>150){
				enemysNames.clear();
			}
			for(int i=0;i<10;i++){
				StringBuilder pageFight = httpGet.getUrl(profile.getMenuUrls().get(Menus.BATTLE));
				List<Enemy> enemys = obtainFight.leerEnemyList(pageFight);
				Enemy enemy = getEnemyParaComentar(enemys);
				if(enemy!=null){
					comentarEnemy(code,enemy,httpGet);
				}
			}
		}catch(Exception ex){
			EventManager.getInstance().error("Error al promoteAllianzeCode.", ex);
		}
	}

	private void comentarEnemy(String code, Enemy enemy, HttpGetUrl httpGet) {
		try{
			StringBuilder pageProfile = httpGet.getUrl(enemy.getProfileUrl());
			EnemyProfile profile = obtainFight.leerEnemyProfile(pageProfile);
			pageProfile = httpGet.getUrl(profile.getCommentTab());
			if(!pageProfile.toString().contains(code)){
				String url = obtainFight.getUrlPostComment(pageProfile);
				Map<String, String> params = new HashMap<String, String>();
				params.put("commentText", code);
				params.put("action", "Post Comment");
				pageProfile = httpGet.postUrl(url, params);
				if(pageProfile.toString().contains(code)){
					EventManager.getInstance().info("Comentando alizance code to "+enemy.getName()+".");
				}else{
					EventManager.getInstance().error("No comente bien a "+enemy.getName()+".", null);
				}
			}
		}catch(Exception ex){
			EventManager.getInstance().error("Error al intentar comentar alianze code.", ex);
		}
	}

	private Enemy getEnemyParaComentar(List<Enemy> enemys) {
		for(Enemy en : enemys){
			if(!enemysNames.contains(en.getName())){
				enemysNames.add(en.getName());
				return en;
			}
		}
		return null;
	}
}

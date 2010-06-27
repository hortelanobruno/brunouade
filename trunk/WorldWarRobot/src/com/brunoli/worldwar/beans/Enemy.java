package com.brunoli.worldwar.beans;

public class Enemy {

	private String name;
	private Integer level;
	private Integer alianceSize;
	private String profileUrl;
	private EnemyProfile profile;
	
	public Enemy() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: "+name+"\n");
		sb.append("Level: "+level+"\n");
		sb.append("Aliance Size: "+alianceSize+"\n");
		sb.append("URL: "+profileUrl+"\n");
		return sb.toString();
	}
	
	public String toStringWithProfile() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: "+name+"\n");
		sb.append("Level: "+level+"\n");
		sb.append("Aliance Size: "+alianceSize+"\n");
		sb.append("URL: "+profileUrl+"\n");
		sb.append("Profile: "+profile.toString()+"\n");
		return sb.toString();
	}
	
	public EnemyProfile getProfile() {
		return profile;
	}
	
	public void setProfile(EnemyProfile profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getAlianceSize() {
		return alianceSize;
	}

	public void setAlianceSize(Integer alianceSize) {
		this.alianceSize = alianceSize;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	
}

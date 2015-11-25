package com.scatterlogic.apps.bbaccelerate;
import java.util.*;

public class Team
{
	String teamName;
	String race;
	String headCoach;
	
	long gold;
	long teamValue;
	
	int rerolls;
	int fanFactor;
	int cheerLeaders;
	int assistantCoaches;
	int apothecaries;
	
	ArrayList <Player> teamMembers;
	String saveString;
	
	static final String TEAM_DELIMITER = "/''/";
	
	public Team(){
		teamName = "";
		race = "";
		headCoach = "";
		gold = 0;
		teamValue = 0;
		
		rerolls=0;
		fanFactor=0;
		cheerLeaders=0;
		assistantCoaches=0;
		apothecaries=0;
		
		teamMembers = new ArrayList <Player>();
		
		for (int i=0; i<=15; i++){
			teamMembers.add(new Player());
			teamMembers.get(i).setNumber(i+1);
		}
		saveString = "";
	}

	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}

	public String getTeamName()
	{
		return teamName;
	}

	public void setRace(String race)
	{
		this.race = race;
	}

	public String getRace()
	{
		return race;
	}

	public void setHeadCoach(String headCoach)
	{
		this.headCoach = headCoach;
	}

	public String getHeadCoach()
	{
		return headCoach;
	}

	public void setGold(long gold)
	{
		this.gold = gold;
	}

	public long getGold()
	{
		return gold;
	}

	public void setTeamValue(long teamValue)
	{
		this.teamValue = teamValue;
	}

	public long getTeamValue()
	{
		return teamValue;
	}

	public void setRerolls(int rerolls)
	{
		this.rerolls = rerolls;
	}

	public int getRerolls()
	{
		return rerolls;
	}

	public void setFanFactor(int fanFactor)
	{
		this.fanFactor = fanFactor;
	}

	public int getFanFactor()
	{
		return fanFactor;
	}

	public void setCheerLeaders(int cheerLeaders)
	{
		this.cheerLeaders = cheerLeaders;
	}

	public int getCheerLeaders()
	{
		return cheerLeaders;
	}

	public void setAssistantCoaches(int assistantCoaches)
	{
		this.assistantCoaches = assistantCoaches;
	}

	public int getAssistantCoaches()
	{
		return assistantCoaches;
	}

	public void setApothecaries(int apothecaries)
	{
		this.apothecaries = apothecaries;
	}

	public int getApothecaries()
	{
		return apothecaries;
	}

	public ArrayList<Player> getTeamMembers()
	{
		return teamMembers;
	}

	public void setSaveString(String saveString)
	{
		this.saveString = saveString;
	}

	public String getSaveString(){
		makeSaveString();
		return saveString;
	}
	private void makeSaveString(){
		saveString = "";
		addItemToSave(teamName);
		addItemToSave(race);
		addItemToSave(headCoach);
		
		addItemToSave(""+gold);
		addItemToSave(""+teamValue);
		addItemToSave(""+rerolls);
		addItemToSave(""+fanFactor);
		addItemToSave(""+cheerLeaders);
		addItemToSave(""+assistantCoaches);
		addItemToSave(""+apothecaries);
		
		for (int i = 0; i<=15; i++){
			addItemToSave(teamMembers.get(i).getSaveString());
		}

		ArrayList <Player> teamMembers;
		
	}
	private void addItemToSave(String thingToAdd){
		saveString = saveString + thingToAdd + TEAM_DELIMITER;
	}
}

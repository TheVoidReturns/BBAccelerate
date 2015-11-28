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
	public Team(String saveString){
		this();
		FromSaveString(saveString);
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
	public Player getPlayer(int playerNo){
		return teamMembers.get(playerNo-1);
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
		
	}
	private void addItemToSave(String thingToAdd){
		saveString = saveString + thingToAdd + TEAM_DELIMITER;
	}
	private void FromSaveString(String inString){
		String [] teamElements = inString.split(TEAM_DELIMITER);
		teamName = teamElements[0];
		race = teamElements[1];
		headCoach= teamElements[2];
		gold = Long.parseLong(teamElements[3]);
		teamValue = Long.parseLong(teamElements[4]);
		rerolls = Integer.parseInt(teamElements[5]);
		fanFactor = Integer.parseInt(teamElements[6]);
		cheerLeaders = Integer.parseInt(teamElements[7]);
		assistantCoaches = Integer.parseInt(teamElements[8]);
		apothecaries= Integer.parseInt(teamElements[9]);

		for (int i = 10; i<=25; i++){
			teamMembers.add(new Player(teamElements[i]));
		}
		this.saveString = saveString;
	}
}

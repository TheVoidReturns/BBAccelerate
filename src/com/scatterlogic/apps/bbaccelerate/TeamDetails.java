package com.scatterlogic.apps.bbaccelerate;

public class TeamDetails
{
	String teamOneName,teamTwoName;
	int teamOneFans,teamTwoFans,teamOneFame,teamTwoFame;
	boolean isTeamOneFirstToMove;
	
	public TeamDetails(String teamNameOne,String teamNameTwo){
		teamOneName = teamNameOne;
		teamTwoName = teamNameTwo;
	}

	public void setIsTeamOneFirstToMove(boolean isTeamOneFirstToMove)
	{
		this.isTeamOneFirstToMove = isTeamOneFirstToMove;
	}

	public boolean isTeamOneFirstToMove()
	{
		return isTeamOneFirstToMove;
	}

	public void setTeamOneFans(int teamOneFans)
	{
		this.teamOneFans = teamOneFans;
	}

	public int getTeamOneFans()
	{
		return teamOneFans;
	}

	public void setTeamTwoFans(int teamTwoFans)
	{
		this.teamTwoFans = teamTwoFans;
	}

	public int getTeamTwoFans()
	{
		return teamTwoFans;
	}

	public void setTeamOneFame(int teamOneFame)
	{
		this.teamOneFame = teamOneFame;
	}

	public int getTeamOneFame()
	{
		return teamOneFame;
	}

	public void setTeamTwoFame(int teamTwoFame)
	{
		this.teamTwoFame = teamTwoFame;
	}

	public int getTeamTwoFame()
	{
		return teamTwoFame;
	}

	public void setTeamOneName(String teamOneName)
	{
		this.teamOneName = teamOneName;
	}

	public String getTeamOneName()
	{
		return teamOneName;
	}

	public void setTeamTwoName(String teamTwoName)
	{
		this.teamTwoName = teamTwoName;
	}

	public String getTeamTwoName()
	{
		return teamTwoName;
	}
	
}

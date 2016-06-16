package com.scatterlogic.apps.bbaccelerate;

import android.util.Log;

public class TeamDetails
{
	String teamOneName,teamTwoName;
	int teamOneFans,teamTwoFans,teamOneFame,teamTwoFame,teamOneReRolls,teamTwoRerolls,teamOneAssCoach,teamTwoAssCoach;
	boolean isTeamOneFirstToMove;
	boolean isTeamOnesTurn;
	int turnNumber;
	int weatherScore;

	public TeamDetails(String teamNameOne,String teamNameTwo){
		Log.d("TeamDetails", "Making now...");
		teamOneName = teamNameOne;
		teamTwoName = teamNameTwo;
		turnNumber = 0;
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

	public void setTeamOneReRolls(int numberToSetTo)
	{
		this.teamOneReRolls = numberToSetTo;
	}

	public int getTeamOneReRolls()
	{
		return teamOneReRolls;
	}

	public void setTeamTwoReRolls(int numberToSetTo)
{
	this.teamOneReRolls = numberToSetTo;
}

	public int getTeamTwoReRolls()
	{
		return teamTwoRerolls;
	}
	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

}

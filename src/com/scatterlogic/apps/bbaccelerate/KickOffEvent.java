package com.scatterlogic.apps.bbaccelerate;
import android.content.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.util.*;

public class KickOffEvent
{
	LinearLayout actionLogPanel,buttonPanel;
	Context c;
	ScrollView s;
	gameEventPanel currentPanel;
	Thread backgroundThread;
	int nextNumberToAction;
	List <gameEventPanel> allPanels;
	Button[] csqButtons;
	int i;
	TeamDetails teamDetails;

	public KickOffEvent(Context context, LinearLayout alPanel, LinearLayout bPanel, ScrollView scrollView,TeamDetails tDetails) {
        actionLogPanel = alPanel;
        buttonPanel = bPanel;
        c = context;
        s = scrollView;
        tidyUp();
		teamDetails = tDetails;
		doKickOffTable();
	}
	public void doKickOffTable(){
		{
			int teamOneScore;
			int teamTwoScore;
			int twoDiceSum;
			Dice dThree;
			String output = "";
			int kickOffType = 0;
			Dice dSix = new Dice(6,c);
			dSix.roll();
			kickOffType = kickOffType + dSix.getValue();
			output = output + "Rolled a " + dSix.getValue() + " and a ";
			dSix.roll();
			kickOffType = kickOffType + dSix.getValue();
			output = output + dSix.getValue() + ", which makes the Kick Off:\n";
			switch (kickOffType){
				case 2:
					output = output + "Get The Ref: Than fans exact gruesome revenge on the referee for some of the dubious decisions he has made, either during this match or in the past. Each team Receive 1 bribe to use during the game. ";
					break;
				case 3:
					output = output + "Riot: The trash talk between two opposing players explodes and rapidly degenerates, involving the rest of the players. If either teams turnmarker is on turn 8, both teams move their turn marker back one space, If either teams turn marker is on turn 1 move both teams turn marker forward 1 space. Otherwise roll a D6 on a 1-3 both teams  turn markers move forward one space, 4-6 both teams turnmarkers move back one space\n";
					dSix.roll();
					if (teamDetails.getTurnNumber() <= 1) {
						teamDetails.setTurnNumber(2);
						output = output + "Turn sliders moved to " + teamDetails.getTurnNumber();
					}
					else if (teamDetails.getTurnNumber() == 8) {
						teamDetails.setTurnNumber(7);
						output = output + "Turn sliders moved to " + teamDetails.getTurnNumber();
					}
					else if (dSix.getValue()>=4){
						teamDetails.setTurnNumber(teamDetails.getTurnNumber()+1);
						output = output + "Turn sliders moved to " + teamDetails.getTurnNumber();
					}
					else {
						teamDetails.setTurnNumber(teamDetails.getTurnNumber()-1);
						output = output + "Turn sliders moved to " + teamDetails.getTurnNumber();
					}
					break;
				case 4:
					output = output +"Perfect Defence; The kickings teams coach may reorganise his players - in other words he can set them up again into another legal defence. The receiving team must remain the same.";
					break;
				case 5:
					output = output +"High Kick: The ball is kicked very high, allowing a player on the receiving team time to move into the perfect position to catch it. Any one player on the receiving team who is not in an opposing players tackle zone may be moved into the square where the ball will land regardless of there movement, as long as the square is unoccupied.";
					break;
				case 6:
					output = output +"Cheering Fans: Each coach rolls a D3 and adds their teams FAME (see page 18)and the number of cheerleaders on their team to the score. The team with the highest score is inspired by their fans cheering and gets an extra re-roll this half. If both teams have the same score then both gain a re-reroll for the half.\n";
					dThree = new Dice(3,c);
					dThree.roll();
					teamOneScore = dThree.getValue() + teamDetails.getTeamOneFame();
					output = output + teamDetails.teamOneName + " Score " + dThree.getValue() +"+"+ teamDetails.getTeamOneFame()+"="+ teamOneScore + "\n";
					dThree.roll();
					teamTwoScore = dThree.getValue() + teamDetails.getTeamTwoFame();
					output = output + teamDetails.teamTwoName + " Score " + dThree.getValue() +"+"+ teamDetails.getTeamTwoFame()+"="+ teamTwoScore + "\n";
					if (teamOneScore > teamTwoScore){
						teamDetails.setTeamOneReRolls(teamDetails.getTeamOneReRolls() +1);
						output = output + teamDetails.getTeamOneName() + " get an extra reroll.";
					}
					else if (teamOneScore < teamTwoScore){
						teamDetails. setTeamTwoReRolls(teamDetails.getTeamTwoReRolls() +1);
						output = output + teamDetails.getTeamTwoName() + " get an extra reroll.";
					}
					else{
						teamDetails.setTeamOneReRolls(teamDetails.getTeamOneReRolls() +1);
						teamDetails.setTeamTwoReRolls(teamDetails.getTeamTwoReRolls() +1);
						output = output + "Both teams get an extra reroll.";
					}
					break;
				case 7:
					output = output +"Changing Weather: Make a new roll on the Weather table (Page 20). Apply the new weather roll. If the new weather was a `Nice' result then a gentle gust of wind makes the ball scatter one extra square in a random direction before landing. \n" +
							"Result\n ";
					teamDetails.weatherScore = 0;
					Dice weatherDice = new Dice(6,c);
					weatherDice.roll();
					teamDetails.weatherScore = teamDetails.weatherScore + weatherDice.getValue();
					output = output + weatherDice.getValue() + " and a ";

					weatherDice.roll();
					teamDetails.weatherScore = teamDetails.weatherScore + weatherDice.getValue();
					output = output + weatherDice.getValue() + " which makes the weather:\n";
					switch (teamDetails.weatherScore){
						case 2:
							output = output + "Sweltering Heat:\nIts so hot and humid that some players collapse from heat exhaustion. Roll a D6 FOR each player on the pitch at the end of the drive. On a roll of 1 the player collapses and may not be set up for the next kick off \n";
							break;
						case 3:
							output = output + "Very Sunny:\nA glorious day, but the blinding sunshine causes a -1 modifier on all passing rolls";
							break;
						case 11:
							output = output +"Pouring Rain:\nIts raining, making the ball slippery and difficult to hold. A -1 modifier applies to all catch, intercept, or pick-up rolls. ";
							break;
						case 12:
							output = output +"Blizzard:\nIts cold and snowing! The ice on the pitch means that any player attempting to move an extra square (GFI) will slip and be Knocked Down on a roll of 12, while the snow means that only quick or short passes can be attempted.";
							break;
						default:
							output = output +"Nice: Perfect Blood Bowl weather.";
					}
					break;
				case 8:
					output = output + "Brilliant Coaching: Each coach rolls a D3 and adds their FAME(Page 18) and their number of assistant coaches on their team to the score. The team with the highest total gets an extra team re-roll this half thanks to the brilliant instruction provided by the coaching staff. In the case of a tie both teams gain the re-roll.\n ";
					dThree = new Dice(3,c);
					dThree.roll();
					teamOneScore = dThree.getValue() + teamDetails.getTeamOneFame() + teamDetails.teamOneAssCoach;
					output = output + teamDetails.teamOneName + " Score " + dThree.getValue() +"+"+ teamDetails.getTeamOneFame() +"+"+ teamDetails.teamOneAssCoach + "=" +teamOneScore+"\n";
					dThree.roll();
					teamTwoScore = dThree.getValue() + teamDetails.getTeamTwoFame() + teamDetails.teamTwoAssCoach;
					output = output + teamDetails.teamTwoName + " Score " + dThree.getValue() +"+"+ teamDetails.getTeamTwoFame() +"+"+ teamDetails.teamTwoAssCoach + "=" +teamTwoScore+"\n";
					if (teamOneScore > teamTwoScore){
						teamDetails.setTeamOneReRolls(teamDetails.getTeamOneReRolls() +1);
						output = output + teamDetails.getTeamOneName() + " get an extra reroll.";
					}
					else if (teamOneScore < teamTwoScore){
						teamDetails. setTeamTwoReRolls(teamDetails.getTeamTwoReRolls() +1);
						output = output + teamDetails.getTeamTwoName() + " get an extra reroll.";
					}
					else{
						teamDetails.setTeamOneReRolls(teamDetails.getTeamOneReRolls() +1);
						teamDetails.setTeamTwoReRolls(teamDetails.getTeamTwoReRolls() +1);
						output = output + "Both teams get an extra reroll.";
					}
					break;
				case 9:
					output = output +"Quick Snap! The offence start the drive a fraction before the defence is ready, catching the kicking team flat-footed. All of the players on the receiving are allowed to move one square. This is a free move and may be made into any adjacent empty square, ignoring tackle zones. It may be used to enter the opposing half of the pitch.";
					break;
				case 10:
					output = output +"Blitz: The defence start their drive a fraction before the offence is ready, catching the receiving team flat-footed. The Kicking team receives a free bonus turn: however, players that are in an enemy tackle zone at the beginning of this free turn may not perform an action. The kicking team may use team rerolls during a Blitz. If any player suffers a turnover then the bonus ends immediately.";
					break;
				case 11:
					output = output +"Throw a Rock: An enrage fan hurls a large rock at the opposing team. Each coach rolls a D6 and adds their FAME (Page 18) to the roll. The fans of the team that scores the higher are the ones that threw the rock. In the case of a tie a rock is thrown at each team. Decide randomly which player on the pitch is hit by the rock, and make an injury roll for that player. No armour roll is required.\n";
					dSix = new Dice(6,c);
					dSix.roll();
					teamOneScore = dSix.getValue() + teamDetails.getTeamOneFame();
					output = output + teamDetails.teamOneName + " Score " + dSix.getValue() +"+"+ teamDetails.getTeamOneFame()+"="+ teamOneScore + "\n";
					dSix.roll();
					teamTwoScore = dSix.getValue() + teamDetails.getTeamTwoFame();
					output = output + teamDetails.teamTwoName + " Score " + dSix.getValue() +"+"+ teamDetails.getTeamTwoFame()+"="+ teamTwoScore + "\n";

					if (teamOneScore > teamTwoScore){
						teamDetails.setTeamOneReRolls(teamDetails.getTeamOneReRolls() +1);
						output = output + teamDetails.getTeamOneName() + " throw a rock at the opposition. Randomly select a player.\n";
						twoDiceSum = 0;
						dSix.roll();
						Log.d("Throw a rock", "Got " + dSix.getValue());
						twoDiceSum = twoDiceSum + dSix.getValue();
						dSix.roll();
						Log.d("Throw a rock", "Got " + dSix.getValue());
						twoDiceSum = twoDiceSum + dSix.getValue();
						output = output + "That player is hit with an injury roll of: " + twoDiceSum;

					}
					else if (teamOneScore < teamTwoScore){
						teamDetails. setTeamTwoReRolls(teamDetails.getTeamTwoReRolls() +1);
						output = output + teamDetails.getTeamTwoName() + " throw a rock at the opposition. Randomly select a player.\n";
						twoDiceSum = 0;
						dSix.roll();
						Log.d("Throw a rock", "Got " + dSix.getValue());
						twoDiceSum = twoDiceSum + dSix.getValue();
						dSix.roll();
						Log.d("Throw a rock", "Got " + dSix.getValue());
						twoDiceSum = twoDiceSum + dSix.getValue();
						output = output + "That player is hit with an injury roll of: " + twoDiceSum;
					}
					else{
						teamDetails.setTeamOneReRolls(teamDetails.getTeamOneReRolls() +1);
						teamDetails.setTeamTwoReRolls(teamDetails.getTeamTwoReRolls() +1);
						output = output + "Both teams throw a rock.\n";
						output = output + teamDetails.getTeamOneName() + " throw a rock at the opposition. Randomly select a player.\n";
						twoDiceSum = 0;
						dSix.roll();
						Log.d("Throw a rock", "Got " + dSix.getValue());
						twoDiceSum = twoDiceSum + dSix.getValue();
						dSix.roll();
						Log.d("Throw a rock", "Got " + dSix.getValue());
						twoDiceSum = twoDiceSum + dSix.getValue();
						output = output + "That player is hit with an injury roll of: " + twoDiceSum + "\n";
						output = output + teamDetails.getTeamTwoName() + " throw a rock at the opposition. Randomly select a player.\n";
						twoDiceSum = 0;
						dSix.roll();
						Log.d("Throw a rock", "Got " + dSix.getValue());
						twoDiceSum = twoDiceSum + dSix.getValue();
						dSix.roll();
						Log.d("Throw a rock", "Got " + dSix.getValue());
						twoDiceSum = twoDiceSum + dSix.getValue();
						output = output + "That player is hit with an injury roll of: " + twoDiceSum;
					}
					break;
				case 12:
					output = output +"Pitch Invasion: Both coaches roll a D6 for each opposing player on the pitch and adds their fame (See page 18) to the roll. If a roll is 6 or more after modification then the player is stunned (Players with the ball & chain skill are KO`D). A roll of 1 before adding FAME will always have no effect.";
					output = output + "First, " + teamDetails.getTeamOneName() + ".\nIf they exist on the pitch, the following players are stunned: ";
					String tempString = "";
					dSix = new Dice(6,c);
					for (int i = 1; i < 17; i++){
						dSix.roll();
						if (dSix.getValue() + teamDetails.getTeamTwoFame() >=6){
							if (tempString.length() == 0) tempString = tempString + i;
							else tempString = tempString + ", " + i;
						}
					}
					output = output + tempString;
					output = output + "\nNext, " + teamDetails.getTeamTwoName() + "\n.  If they exist on the pitch, the following players are stunned: ";
					tempString = "";
					dSix = new Dice(6,c);
					for (int i = 1; i < 17; i++){
						dSix.roll();
						if (dSix.getValue() + teamDetails.getTeamTwoFame() >=6){
							if (tempString.length() == 0) tempString = tempString + i;
							else tempString = tempString + ", " + i;
						}
					}
					output = output + tempString;
					break;
				default:
					output = output +"Bloody hell, something's gone wrong with the kickoff table at value " + kickOffType + ".";
			}
			tidyUp();
			currentPanel = new gameEventPanel("Kick Off!", output, "OK", c,
											  R.color.deepblue, R.color.white, R.color.bluegrey, R.color.black);
			csqButtons = currentPanel.getButtons();
			for (i = 0; i < csqButtons.length; i++) {
				buttonPanel.addView(csqButtons[i]);

				csqButtons[i].setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							Button castIn = (Button) v;
							buttonPanel.removeAllViews();
							tidyUp();
							//TransferGoldFromTreasuryToPettyCash();
						}
					});
			}
			actionLogPanel.addView(currentPanel.getPanel());
			tidyUp();
		}
	}
	public void tidyUp(){
		s.post(new Runnable() {
			@Override
			public void run() {
				// This method works but animates the scrolling
				// which looks weird on first load
				// scroll_view.fullScroll(View.FOCUS_DOWN);

				// This method works even better because there are no animations.
				s.scrollTo(0, s.getBottom());
			}
		});
	}
}


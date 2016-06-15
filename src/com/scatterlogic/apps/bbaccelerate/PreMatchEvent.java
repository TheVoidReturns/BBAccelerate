package com.scatterlogic.apps.bbaccelerate;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;

import java.util.List;
import android.widget.*;

public class PreMatchEvent {

    LinearLayout actionLogPanel,buttonPanel;
    Context c;
    ScrollView s;
    gameEventPanel currentPanel;
    Button[] csqButtons;
    int i;
    TeamDetails teamDetails;
    public PreMatchEvent(Context context, LinearLayout alPanel, LinearLayout bPanel, ScrollView scrollView, TeamDetails TDetails) {
        actionLogPanel = alPanel;
        buttonPanel = bPanel;
        c = context;
        s = scrollView;
        tidyUp();
		teamDetails = TDetails;
        RollOnWeatherTable();
    }

    public void RollOnWeatherTable(){
        Dice weatherDice = new Dice(6,c);
        String output = "Rolled a ";
        teamDetails.weatherScore = 0;

        weatherDice.roll();
        teamDetails.weatherScore = teamDetails.weatherScore + weatherDice.getValue();
        output = output + weatherDice.getValue() + " and a ";

        weatherDice.roll();
        teamDetails.weatherScore = teamDetails.weatherScore + weatherDice.getValue();
        output = output + weatherDice.getValue() + " which makes the weather:\n";
        switch (teamDetails.weatherScore){
            case 2:
                output = output + "Sweltering Heat:\nIts so hot and humid that some players collapse from heat exhaustion. Roll a D6 FOR each player on the pitch at the end of the drive. On a roll of 1 the player collapses and may not be set up for the next kick off ";
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

        currentPanel = new gameEventPanel("Weather Roll", output, "OK", c,
                R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();
        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button castIn = (Button) v;
                    buttonPanel.removeAllViews();
                    tidyUp();
                    TransferGoldFromTreasuryToPettyCash();
                }
            });
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }
    public void TransferGoldFromTreasuryToPettyCash(){

        currentPanel = new gameEventPanel("Transfer Gold","Transfer Gold From Treasury To Petty Cash." , "OK", c,
                R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();
        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button castIn = (Button) v;
                    buttonPanel.removeAllViews();
                    tidyUp();
                    TakeInducements();
                }
            });
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }
    public void TakeInducements(){

        currentPanel = new gameEventPanel("Take Inducements","Spend available money on inducements." , "OK", c,
                R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();
        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button castIn = (Button) v;
                    buttonPanel.removeAllViews();
                    tidyUp();
                    WorkOutNumberOfFansAndFame();
                }
            });
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }
    public void WorkOutNumberOfFansAndFame(){
        String outputString;
		String teamOne = teamDetails.teamOneName;
		String teamTwo =teamDetails.teamTwoName;
		int teamOneFans = teamDetails.teamOneFans;
		int teamTwoFans = teamDetails.teamTwoFans;
        outputString = teamOne + " have " + teamOneFans + " fans and rolled a ";
        Dice dSix = new Dice(6,c);
        Log.d("Fan Counter","Team one " + teamOneFans);
        dSix.roll();
        teamOneFans += dSix.getValue();
        Log.d("Fan Counter","Team one " + teamOneFans);
        outputString = outputString + dSix.getValue() + " and a ";

        dSix.roll();
        teamOneFans += dSix.getValue();Log.d("Fan Counter","Team one " + teamOneFans);
        outputString = outputString + dSix.getValue() + ", making " + teamOneFans + ",000 fans.\n";

        outputString = outputString  + teamTwo + " have " + teamTwoFans + " fans and rolled a ";

        dSix.roll();
        teamTwoFans += dSix.getValue();
        outputString = outputString + dSix.getValue() + " and a ";

        dSix.roll();
        teamTwoFans += dSix.getValue();
        outputString = outputString + dSix.getValue() + ", making " + teamTwoFans + ",000 fans.\n Therefore ";

        if (teamOneFans==teamTwoFans){
            outputString = outputString + " both team have the same number of fans. <FAME: 0>";
        }
        else if (teamOneFans > teamTwoFans*2){
            teamDetails.teamOneFame = 2;
            teamDetails.teamTwoFame = 0;
            outputString = outputString + teamOne + " has many more fans than " + teamTwo + ". <FAME: 2>";
        } else if (teamTwoFans > teamOneFans*2){
            teamDetails.teamOneFame = 0;
            teamDetails.teamTwoFame = 2;
            outputString = outputString + teamTwo + " has many more fans than " + teamOne + ". <FAME: 2>";
        } else if (teamOneFans > teamTwoFans){
            teamDetails.teamOneFame = 1;
            teamDetails.teamTwoFame = 0;
            outputString = outputString + teamOne + " has more fans than " + teamTwo + ". <FAME: 1>";
        } else if (teamTwoFans > teamOneFans){
            teamDetails.teamOneFame = 0;
            teamDetails.teamTwoFame = 1;
            outputString = outputString + teamTwo + " has more fans than " + teamOne + ". <FAME: 1>";
        }
		teamDetails.teamOneFans = teamOneFans;
		teamDetails.teamTwoFans = teamTwoFans;
        currentPanel = new gameEventPanel("Fans and Fame",outputString, "OK", c,
                R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();
        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button castIn = (Button) v;
                    buttonPanel.removeAllViews();
                    tidyUp();
					KickOffEvent KickOffEvent = new KickOffEvent(c, actionLogPanel, buttonPanel,s,teamDetails); 
                }
            });
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
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
    public int getFans(int teamNumber){
        if (teamNumber == 1){
            return teamDetails.teamOneFans;
        } else {
            return teamDetails.teamTwoFans;
        }
    }
    public int getFame(int teamNumber){
        if (teamNumber == 1){
            return teamDetails.teamOneFame;
        } else {
            return teamDetails.teamTwoFame;
        }
    }
}

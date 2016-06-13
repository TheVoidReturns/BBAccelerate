package com.scatterlogic.apps.bbaccelerate;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;

import java.util.List;

public class PreMatchEvent {

    LinearLayout actionLogPanel,buttonPanel;
    Context c;
    ScrollView s;
    gameEventPanel currentPanel;
    Button[] csqButtons;
    int i;
    int weatherNumber;
    int teamOneFans,teamTwoFans,teamOneFame,teamTwoFame;
    String teamOne,teamTwo;
    public PreMatchEvent(Context context, LinearLayout alPanel, LinearLayout bPanel, ScrollView scrollView,int teamOneFF,int teamTwoFF,String teamOneName,String teamTwoName) {
        actionLogPanel = alPanel;
        buttonPanel = bPanel;
        c = context;
        s = scrollView;
        tidyUp();
        teamOne = teamOneName;
        teamTwo = teamTwoName;
        teamOneFans = teamOneFF;
        teamTwoFans = teamTwoFF;
        RollOnWeatherTable();
    }

    public void RollOnWeatherTable(){
        Dice weatherDice = new Dice(6,c);
        String output = "Rolled a ";
        weatherNumber = 0;

        weatherDice.roll();
        weatherNumber = weatherNumber + weatherDice.getValue();
        output = output + weatherDice.getValue() + " and a ";

        weatherDice.roll();
        weatherNumber = weatherNumber + weatherDice.getValue();
        output = output + weatherDice.getValue() + " which makes the weather:\n";
        switch (weatherNumber){
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
        outputString = outputString + dSix.getValue() + ", making \" + teamTwoFans + \",000 fans.\n Therefore ";

        if (teamOneFans > teamTwoFans*2){
            teamOneFame = 2;
            teamTwoFame = 0;
            outputString = outputString + teamOne + " has many more fans than " + teamTwo + ". <FAME: 2>";
        } else if (teamTwoFans > teamOneFans*2){
            teamOneFame = 0;
            teamTwoFame = 2;
            outputString = outputString + teamTwo + " has many more fans than " + teamOne + ". <FAME: 2>";
        } else if (teamOneFans > teamTwoFans){
            teamOneFame = 2;
            teamTwoFame = 0;
            outputString = outputString + teamOne + " has more fans than " + teamTwo + ". <FAME: 1>";
        } else if (teamTwoFans > teamOneFans){
            teamOneFame = 0;
            teamTwoFame = 2;
            outputString = outputString + teamTwo + " has more fans than " + teamOne + ". <FAME: 1>";
        } else {
            outputString = outputString + " both team have the same number of fans. <FAME: 0>";
        }

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
            return teamOneFans;
        } else {
            return teamTwoFans;
        }
    }
    public int getFame(int teamNumber){
        if (teamNumber == 1){
            return teamOneFame;
        } else {
            return teamTwoFame;
        }
    }
}
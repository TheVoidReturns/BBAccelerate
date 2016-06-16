package com.scatterlogic.apps.bbaccelerate;

import android.content.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.util.*;


/**
 * Created by Robin on 16/06/2016.
 */
public class BlockEvent {
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

    public BlockEvent(Context context, LinearLayout alPanel, LinearLayout bPanel, ScrollView scrollView,TeamDetails tDetails) {
        actionLogPanel = alPanel;
        buttonPanel = bPanel;
        c = context;
        s = scrollView;
        tidyUp();
        teamDetails = tDetails;
        chooseBlockDice();
    }
    private void chooseBlockDice(){
        final Dice blockDice = new Dice("Block",c);
        blockDice.roll();

        currentPanel = new gameEventPanel("Block!","How many dice to roll?", "One,Two,Three", c,
                R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();


        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String outputString = "";
                    Button castIn = (Button) v;
                    buttonPanel.removeAllViews();

                    switch(castIn.getText().toString()){
                        case "Three":
                            blockDice.roll();
                            outputString = outputString + blockDice.getValueAsString() + ",";
                        case "Two":
                            blockDice.roll();
                            outputString = outputString + blockDice.getValueAsString() + ",";
                        case "One":
                            blockDice.roll();
                            outputString = outputString + blockDice.getValueAsString();
                            break;

                    }
                    tidyUp();
                    resultBlockDice(outputString);
                }
            });
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }
    private void resultBlockDice(String rolls){

        currentPanel = new gameEventPanel("Block Result!","You got " + rolls + "What do you want to do?", "Re-Roll,Roll Armour,Finish", c,
                R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();


        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String outputString = "";
                    Button castIn = (Button) v;
                    buttonPanel.removeAllViews();

                    switch(castIn.getText().toString()){
                        case "Re-Roll":
                            chooseBlockDiceTwo();
                            break;
                        case "Roll Armour":
                            rollArmour();
                            break;
                        case "Finish":
                            break;

                    }
                    currentPanel.AddText("You Rolled " + outputString);
                    tidyUp();
                }
            });
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }
    private void chooseBlockDiceTwo(){
        final Dice blockDice = new Dice("Block",c);
        blockDice.roll();

        currentPanel = new gameEventPanel("Second Block!","How many dice to roll?", "One,Two,Three", c,
                R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();


        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String outputString = "";
                    Button castIn = (Button) v;
                    currentPanel.AddText(castIn.getText().toString() + "\n");
                    buttonPanel.removeAllViews();
                    tidyUp();
                    switch(castIn.getText().toString()){
                        case "Three":
                            blockDice.roll();
                            outputString = outputString + blockDice.getValueAsString() + ",";
                        case "Two":
                            blockDice.roll();
                            outputString = outputString + blockDice.getValueAsString() + ",";
                        case "One":
                            blockDice.roll();
                            outputString = outputString + blockDice.getValueAsString();
                            break;

                    }
                    resultBlockDiceTwo(outputString);
                    tidyUp();
                }
            });
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }
    private void resultBlockDiceTwo(String rolls){

        currentPanel = new gameEventPanel("Second Block Result!","You got " + rolls + "\nWhat do you want to do?", "Roll Armour,Finish", c,
                R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();


        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String outputString = "";
                    Button castIn = (Button) v;
                    buttonPanel.removeAllViews();
                    tidyUp();
                    switch(castIn.getText().toString()){
                        case "Roll Armour":
                            rollArmour();
                        case "Finish":
                            break;

                    }
                    currentPanel.AddText("You Rolled " + outputString);
                    tidyUp();
                }
            });
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }
    private void rollArmour(){
        Dice dSix = new Dice(6,c);
        int sumD = 0;
        dSix.roll();
        String outputString = "You rolled a " + dSix.getValue();
        sumD = sumD + dSix.getValue();
        dSix.roll();
        outputString = outputString  + " and a " + dSix.getValue();
        sumD = sumD + dSix.getValue();
        outputString = outputString + "(" + sumD + ")";

        currentPanel = new gameEventPanel("Armour Roll!",outputString, "Another Armour Roll,Pile On,Finish", c,
                R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();


        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String outputString = "";
                    Button castIn = (Button) v;
                    buttonPanel.removeAllViews();

                    switch(castIn.getText().toString()){
                        case "Another Armour Roll":
                            currentPanel.AddText("And another armour roll...");
                            tidyUp();
                            rollArmour();
                            break;
                        case "Pile On":
                            currentPanel.AddText("Piling On!");
                            tidyUp();
                            rollArmour();
                        case "Finish":
                            break;
                    }
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
}

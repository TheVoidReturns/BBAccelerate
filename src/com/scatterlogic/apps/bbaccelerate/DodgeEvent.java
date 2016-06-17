package com.scatterlogic.apps.bbaccelerate;
import android.content.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.util.*;


public class DodgeEvent
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

	public DodgeEvent(LinearLayout actionLogPanel, LinearLayout buttonPanel, Context c, ScrollView s, gameEventPanel currentPanel, Thread backgroundThread, int nextNumberToAction, List<gameEventPanel> allPanels, Button[] csqButtons, int i, TeamDetails teamDetails)
	{
		this.actionLogPanel = actionLogPanel;
		this.buttonPanel = buttonPanel;
		this.c = c;
		this.s = s;
		this.currentPanel = currentPanel;
		this.backgroundThread = backgroundThread;
		this.nextNumberToAction = nextNumberToAction;
		this.allPanels = allPanels;
		this.csqButtons = csqButtons;
		this.i = i;
		this.teamDetails = teamDetails;
	}

	public DodgeEvent(Context context, LinearLayout alPanel, LinearLayout bPanel, ScrollView scrollView,TeamDetails tDetails) {
        actionLogPanel = alPanel;
        buttonPanel = bPanel;
        c = context;
        s = scrollView;
        tidyUp();
		teamDetails = tDetails;
		doDodge();
	}
	private void doDodge(){
		Dice dSix = new Dice(6,c);
		dSix.roll();
        currentPanel = new gameEventPanel("Dodge Event","Attempting a dodge: +1. \nYou rolled " + dSix.getValue() +". Did you succeed?", "Yes,No,Dodge,Reroll", c,
										  R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
        csqButtons = currentPanel.getButtons();
        for (i = 0; i < csqButtons.length; i++) {
            buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Button castIn = (Button) v;
						buttonPanel.removeAllViews();
						tidyUp();
						switch(castIn.getText().toString()){
							case "Yes":
								currentPanel.AddText("\nSuccess!");
								break;
							case "No":
								currentPanel.AddText("\nFailure!");
								rollArmour();
								break;
							case "Dodge":
								doSecondDodge();
								break;
							case "Reroll":
								doSecondDodge();
								break;
						}
					}
				});
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }

	private void doSecondDodge(){
		Dice dSix = new Dice(6,c);
		dSix.roll();
        currentPanel = new gameEventPanel("Second Dodge Event","Attempting a Dodge: +1.\n You rolled " + dSix.getValue() +". Did you succeed?", "Yes,No", c,
										  R.color.deepgreen, R.color.white, R.color.bluegrey, R.color.black);
		csqButtons = currentPanel.getButtons();
        for (i = 0; i < csqButtons.length; i++) {
			buttonPanel.addView(csqButtons[i]);

            csqButtons[i].setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Button castIn = (Button) v;
						buttonPanel.removeAllViews();
						tidyUp();
						switch(castIn.getText().toString()){
							case "Yes":
								currentPanel.AddText("\nSuccess!");
								break;
							case "No":
								currentPanel.AddText("\nFailure!");
								rollArmour();
						}
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

        currentPanel = new gameEventPanel("Armour Roll!",outputString, "Injury Roll,Finish", c,
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
							case "Injury Roll":
								currentPanel.AddText("Injury...");
								tidyUp();
								rollInjury();
								break;
							case "Finish":
								break;
						}
						tidyUp();
					}
				});
        }
        actionLogPanel.addView(currentPanel.getPanel());
	}    
	private void rollInjury(){
        Dice dSix = new Dice(6,c);
        int sumD = 0;
        dSix.roll();
        String outputString = "You rolled a " + dSix.getValue();
        sumD = sumD + dSix.getValue();
        dSix.roll();
        outputString = outputString  + " and a " + dSix.getValue();
        sumD = sumD + dSix.getValue();
        outputString = outputString + "(" + sumD + ")";
		switch (sumD){
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				outputString = outputString + "\nProbably 'Stunned'";
				break;
			case 8:
			case 9:
				outputString = outputString + "\nProbably 'KO'";
				break;
			case 10:
			case 11:
			case 12:
				outputString = outputString + "\nInjury";
		}
        currentPanel = new gameEventPanel("Injury Roll!",outputString, "Another Armour Roll,Casualty Roll,Finish", c,
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
							case "Casualty Roll":
								currentPanel.AddText("This is going to hurt...");
								tidyUp();
								rollCasualty();
								break;
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
	private void rollCasualty(){

        Dice dSix = new Dice(6,c);
        Dice dEight = new Dice(8,c);
		int sumD = 0;
        dSix.roll();
        String outputString = "You rolled a " + dSix.getValue();
        sumD = sumD + 10*dSix.getValue();
        dEight.roll();
        outputString = outputString  + " and a " + dEight.getValue();
        sumD = sumD + dEight.getValue();
        outputString = outputString + "(" + sumD + ")\n";
		if(sumD <=38){
			outputString = outputString + "11-38 Badly Hurt No long term effect";
		} 	
		else if(sumD == 41){outputString = outputString + "41 Broken Ribs Miss next game";}
		else if(sumD == 42){outputString = outputString + "42 Groin Strain Miss next game";}
		else if(sumD == 43){outputString = outputString + "43 Gouged Eye Miss next game";}
		else if(sumD == 44){outputString = outputString + "44 Broken Jaw Miss next game";}
		else if(sumD == 45){outputString = outputString + "45 Fractured Arm Miss next game";}
		else if(sumD == 46){outputString = outputString + "46 Fractured Leg Miss next game";}
		else if(sumD == 47){outputString = outputString + "47 Smashed Hand Miss next game";}
		else if(sumD == 48){outputString = outputString + "48 Pinched Nerve Miss next game";}
		else if(sumD == 51){outputString = outputString + "51 Damaged Back Niggling Injury";}
		else if(sumD == 52){outputString = outputString + "52 Smashed Knee Niggling Injury";}
		else if(sumD == 53){outputString = outputString + "53 Smashed Hip -1 MA";}
		else if(sumD == 54){outputString = outputString + "54 Smashed Ankle -1 MA";}
		else if(sumD == 55){outputString = outputString + "55 Serious Concussion -1 AV";}
		else if(sumD == 56){outputString = outputString + "56 Fractured Skull -1 AV";}
		else if(sumD == 57){outputString = outputString + "57 Broken Neck -1 AG";}
		else if(sumD == 58){outputString = outputString + "58 Smashed Collar Bone -1 ST";}
		else {outputString = outputString + "61-68 DEAD Dead!";}

        currentPanel = new gameEventPanel("Casualty Roll!",outputString, "Another Armour Roll,Finish", c,
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

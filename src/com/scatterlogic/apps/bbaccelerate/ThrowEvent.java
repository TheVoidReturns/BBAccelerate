package com.scatterlogic.apps.bbaccelerate;
import android.content.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.util.*;


public class ThrowEvent
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

	public ThrowEvent(LinearLayout actionLogPanel, LinearLayout buttonPanel, Context c, ScrollView s, gameEventPanel currentPanel, Thread backgroundThread, int nextNumberToAction, List<gameEventPanel> allPanels, Button[] csqButtons, int i, TeamDetails teamDetails)
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

	public ThrowEvent(Context context, LinearLayout alPanel, LinearLayout bPanel, ScrollView scrollView,TeamDetails tDetails) {
        actionLogPanel = alPanel;
        buttonPanel = bPanel;
        c = context;
        s = scrollView;
        tidyUp();
		teamDetails = tDetails;
		doThrow();
	}
	private void doThrow(){
		Dice dSix = new Dice(6,c);
		dSix.roll();
        currentPanel = new gameEventPanel("Throw Event","You rolled " + dSix.getValue() +". Did you succeed?", "Yes,No,Fumble,Pass Skill,Reroll", c,
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
								doCatchSequence();
								break;
							case "No":
								currentPanel.AddText("\nFailure!");
								doScatter();
								break;
							case "Pass Skill":
								doSecondThrow();
								break;
							case "Reroll":
								doSecondThrow();
								break;
						}
					}
				});
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }

	private void doSecondThrow(){
		Dice dSix = new Dice(6,c);
		dSix.roll();
        currentPanel = new gameEventPanel("Second Throw Event","You rolled " + dSix.getValue() +". Did you succeed?", "Yes,No", c,
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
								doCatchSequence();
								break;
							case "No":
								currentPanel.AddText("\nFailure!");
								doScatter();
								break;
						}
					}
				});
		}
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
	}
	private void doCatchSequence(){
		Dice dSix = new Dice(6,c);
		dSix.roll();
        currentPanel = new gameEventPanel("Catch Event","You rolled " + dSix.getValue() +". Did you succeed?", "Yes,No,Catch Skill,Reroll", c,
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
								doScatter();
								break;
							case "Catch Skill":
								currentPanel.AddText("\nFailure!");
								doSecondCatchSequence();
								break;
							case "Reroll":
								currentPanel.AddText("\nFailure!");
								doSecondCatchSequence();
								break;
						}
					}
				});
		}
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
	}
	private void doSecondCatchSequence(){
		Dice dSix = new Dice(6,c);
		dSix.roll();
        currentPanel = new gameEventPanel("Catch Event","You rolled " + dSix.getValue() +". Did you succeed?", "Yes,No", c,
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
								doScatter();
								break;
						}
					}
				});
		}
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
	}
	private void doScatter(){
		Dice dEight = new Dice(8,c);
		dEight.roll();
        currentPanel = new gameEventPanel("Scatter Event","Ball Scatters To " + dEight.getValue() +".\n Do you need to roll for catch?", "Yes,No", c,
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
								doCatchSequence();
								break;
							case "No":
								currentPanel.AddText("\nBall Comes To Rest.");
								doScatter();
								break;
						}
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

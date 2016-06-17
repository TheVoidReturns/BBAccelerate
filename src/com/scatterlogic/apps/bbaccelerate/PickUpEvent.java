package com.scatterlogic.apps.bbaccelerate;
import android.content.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.util.*;


public class PickUpEvent
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

	public PickUpEvent(LinearLayout actionLogPanel, LinearLayout buttonPanel, Context c, ScrollView s, gameEventPanel currentPanel, Thread backgroundThread, int nextNumberToAction, List<gameEventPanel> allPanels, Button[] csqButtons, int i, TeamDetails teamDetails)
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

	public PickUpEvent(Context context, LinearLayout alPanel, LinearLayout bPanel, ScrollView scrollView,TeamDetails tDetails) {
        actionLogPanel = alPanel;
        buttonPanel = bPanel;
        c = context;
        s = scrollView;
        tidyUp();
		teamDetails = tDetails;
		doPickUp();
	}
	private void doPickUp(){
		Dice dSix = new Dice(6,c);
		dSix.roll();
        currentPanel = new gameEventPanel("Pick Up Event","Attempting a pick up: +1. You rolled " + dSix.getValue() +". Did you succeed?", "Yes,No,Sure Hands,Reroll", c,
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
								break;
							case "Sure Hands":
								doSecondPickUp();
								break;
							case "Reroll":
								doSecondPickUp();
								break;
						}
					}
				});
        }
        actionLogPanel.addView(currentPanel.getPanel());
        tidyUp();
    }

	private void doSecondPickUp(){
	Dice dSix = new Dice(6,c);
		dSix.roll();
        currentPanel = new gameEventPanel("Second Pick Up Event","Attempting a pick up: +1. You rolled " + dSix.getValue() +". Did you succeed?", "Yes,No", c,
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

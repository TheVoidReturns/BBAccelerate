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
		startKickOff();
	}
	public void doKickOffTable(){
		{
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
					output = output + "Sweltering Heat:\nIts so hot and humid that some players collapse from heat exhaustion. Roll a D6 FOR each player on the pitch at the end of the drive. On a roll of 1 the player collapses and may not be set up for the next kick off ";
					break;
				case 3:
					output = output + "Very Sunny:\nA glorious day, but the blinding sunshine causes a -1 modifier on all passing rolls";
					break;
				case 4:
					
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


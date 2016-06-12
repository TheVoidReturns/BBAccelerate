package com.scatterlogic.apps.bbaccelerate;
import android.content.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.os.*;
import java.util.Observable;
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

	public KickOffEvent(Context context, LinearLayout alPanel, LinearLayout bPanel, ScrollView scrollView) {

		actionLogPanel = alPanel;
		buttonPanel = bPanel;
		c = context;
		s = scrollView;
		tidyUp();
		startKickOff();
	}
	public void startKickOff(){
		currentPanel = new gameEventPanel("Kick Off", "Get the Ref!", "Kill Ref,Spare Ref", c,
				R.color.deepblue, R.color.white, R.color.bluegrey, R.color.black);
		csqButtons = currentPanel.getButtons();
		Log.d("KickOffEvent", "Got " + csqButtons.length + " buttons.");
		for (i = 0; i < csqButtons.length; i++) {
			Log.d("KickOffLoop", "Adding button " + i);
			buttonPanel.addView(csqButtons[i]);

			csqButtons[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Button castIn = (Button) v;
					Log.d("Feedback", "Feeding back " + castIn.getText());
					currentPanel.AddText("\nClicked " + castIn.getText());
					buttonPanel.removeAllViews();
					tidyUp();
				}
			});
		}
		actionLogPanel.addView(currentPanel.getPanel());
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


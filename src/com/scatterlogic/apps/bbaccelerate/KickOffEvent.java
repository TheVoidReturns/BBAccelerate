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
	gameEventPanel currentPanel;
	Thread backgroundThread;
	int nextNumberToAction;
	List <gameEventPanel> allPanels;
	Button[] csqButtons;
	int i;

	public KickOffEvent(Context context, LinearLayout alPanel, LinearLayout bPanel) {

		actionLogPanel = alPanel;
		buttonPanel = bPanel;
		currentPanel = new gameEventPanel("Kick Off", "Get the Ref!", "Kill Ref,Spare Ref", context,
				R.color.deepblue, R.color.white, R.color.bluegrey, R.color.black);
		csqButtons = currentPanel.getButtons();
		Log.d("KickOffEvent", "Got " + csqButtons.length + " buttons.");
		for (i = 0; i < csqButtons.length; i++) {
			Log.d("KickOffLoop", "Adding button " + i);
			buttonPanel.addView(csqButtons[i]);

			csqButtons[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Log.d("Feedback", "Feeding back " + v.toString());
					currentPanel.AddText("\nClicked " + csqButtons[i].getText());
					buttonPanel.removeAllViews();
				}
			});
		}
		actionLogPanel.addView(currentPanel.getPanel());
	}
}


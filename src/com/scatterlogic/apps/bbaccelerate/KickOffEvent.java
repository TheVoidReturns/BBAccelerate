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
	LinearLayout actionLogPanel;
	gameEventPanel currentPanel;
	Thread backgroundThread;
	int nextNumberToAction;
	List <gameEventPanel> allPanels;
	Button[] csqButtons;

	public KickOffEvent(Context context, LinearLayout actionLogPanel, LinearLayout buttonPanel) {

		this.actionLogPanel = actionLogPanel;
		currentPanel = new gameEventPanel("Kick Off", "Get the Ref!", "Kill Ref,Spare Ref", context,
				R.color.deepblue, R.color.white, R.color.bluegrey, R.color.black);
		csqButtons = currentPanel.getButtons();
		Log.d("KickOffEvent", "Got " + csqButtons.length + " buttons.");
		for (int i = 0; i < csqButtons.length; i++) {
			Log.d("KickOFfLoop","Adding button " + i);
			buttonPanel.addView(csqButtons[i]);
		}
		/*csqButtons[i].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				infoPanel.setText(infoPanel.getText() +"\nClicked " + buttonName);
				feedback = buttonName;
				EventPanel.removeView(buttonPanel);
				resolved = true;
				Log.d("Feedback", "Feeding back " + feedback);
			}
		});*/
		actionLogPanel.addView(currentPanel.getPanel());
	}
}


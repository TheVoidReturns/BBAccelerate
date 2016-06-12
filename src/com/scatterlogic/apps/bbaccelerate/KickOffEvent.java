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
	ScrollView scroller;
	gameEventPanel currentPanel;
	Thread backgroundThread;
	int nextNumberToAction;
	List <gameEventPanel> allPanels;
	Button[] csqButtons;
	int i;

	public KickOffEvent(Context context, LinearLayout alPanel, LinearLayout bPanel,ScrollView s) {

		actionLogPanel = alPanel;
		buttonPanel = bPanel;
		scroller = s;
		c = context;
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
					scroller.fullScroll(ScrollView.FOCUS_DOWN);
					buttonPanel.removeAllViews();
				}
			});
		}
		actionLogPanel.addView(currentPanel.getPanel());
	}
}


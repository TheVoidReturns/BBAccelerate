package com.scatterlogic.apps.bbaccelerate;
import android.content.*;
import android.util.Log;
import android.widget.*;
import android.os.*;
import java.util.Observable;
import java.util.*;

public class KickOffEvent implements Observer
{
	LinearLayout actionLogPanel;
	gameEventPanel currentPanel;

	public KickOffEvent(Context context, LinearLayout actionLogPanel) {
		currentPanel = new gameEventPanel("Kick Off", "Get the Ref!", "Kill Ref,Spare Ref", context,
				R.color.deepblue, R.color.white, R.color.bluegrey, R.color.black);
		this.actionLogPanel = actionLogPanel;
		actionLogPanel.addView(currentPanel.getPanel());
	}
	public LinearLayout getALogPanel()
	{
		return actionLogPanel;
	}

	@Override
	public void update(Observable p1, Object p2)
	{
		Log.d("KickOffEvent", "Got state change " + p2);
	}

}


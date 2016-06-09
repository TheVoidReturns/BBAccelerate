package com.scatterlogic.apps.bbaccelerate;
import android.content.*;
import android.util.Log;
import android.widget.*;
import android.os.*;

public class KickOffEvent
{
	LinearLayout actionLogPanel;

	public KickOffEvent(Context context, LinearLayout actionLogPanel) {
		gameEventPanel test = new gameEventPanel("Kick Off", "Get the Ref!", "Kill Ref,Spare Ref", context,
				R.color.deepblue, R.color.white, R.color.bluegrey, R.color.black);
		this.actionLogPanel = actionLogPanel;
		actionLogPanel.addView(test.getPanel());
		Log.d("KickOffEvent", "I can see " + test.getFeedback());
	}
	public LinearLayout getALogPanel()
	{
		return actionLogPanel;
	}

}

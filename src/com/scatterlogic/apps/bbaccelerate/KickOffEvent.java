package com.scatterlogic.apps.bbaccelerate;
import android.content.*;
import android.widget.*;
import android.os.*;

public class KickOffEvent
{
	final LinearLayout aLogPanel;
	final Context cont;
	final gameEventPanel test;
	public KickOffEvent(Context context, LinearLayout actionLogPanel){
		test = new gameEventPanel("Kick Off", "Get the Ref!", "Kill Ref,Spare Ref", context, 
												 R.color.deepblue, R.color.white, R.color.bluegrey, R.color.black);
		this.aLogPanel = actionLogPanel;
		cont = context;
		actionLogPanel.addView(test.getPanel());
		new Thread(new Runnable(){
			@Override
			public void run(){
				gameEventPanel testx = new gameEventPanel("Kick Off 2", "Get the Ref!", "Kill Ref,Spare Ref", cont, 
												  R.color.deepblue, R.color.white, R.color.bluegrey, R.color.black);
				aLogPanel.addView(testx.getPanel());
			}
		}).run();
	}

	public LinearLayout getALogPanel()
	{
		return aLogPanel;
	}
}

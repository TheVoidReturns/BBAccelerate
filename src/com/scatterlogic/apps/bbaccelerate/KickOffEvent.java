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
import java.util.Observable;
		import static java.lang.System.out;

class MyApp {
	public static void main(String[] args) {
		out.println("Enter Text >");
		EventSource eventSource = new EventSource();

		eventSource.addObserver( (Observable obj, Object arg) -> {
			out.println("\nReceived response: " + arg);
		});

		new Thread(eventSource).start();
	}
}
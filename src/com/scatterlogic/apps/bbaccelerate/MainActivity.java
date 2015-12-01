package com.scatterlogic.apps.bbaccelerate;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playercard);
		TextView mainT = new TextView(this);
        ImageView player = (ImageView) findViewById(R.id.playerpic);
		player.setImageResource(R.drawable.dopey);
        /*
        mainT.setText("one one");
		RelativeLayout BBCanvas = (RelativeLayout) findViewById(R.id.rlayout);
		BBCanvas.addView(mainT);
		Team demo = new Team();
        Dice d8 = new Dice(8);
        for (int i = 0; i < 100; i++) d8.roll();

		demo.getPlayer(1).setName("Robin");

        EventSequence test = new EventSequence("Kick Off");

		mainT.setText(test.toString());
		*/
    }
}

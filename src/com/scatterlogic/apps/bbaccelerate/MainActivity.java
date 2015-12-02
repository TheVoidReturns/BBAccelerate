package com.scatterlogic.apps.bbaccelerate;

import android.app.*;
import android.graphics.Typeface;
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
		TextView mainT = (TextView) findViewById(R.id.playername);
        ImageView player = (ImageView) findViewById(R.id.playerpic);
        Typeface testTypeface = Typeface.createFromAsset(getAssets(),"fonts/avqest.ttf");
        mainT.setTypeface(testTypeface);

        TextView stat1,stat2,stat3,stat4,lvl;
        stat1 = (TextView) findViewById(R.id.ama);
        stat2 = (TextView) findViewById(R.id.ast);
        stat3 = (TextView) findViewById(R.id.aag);
        stat4 = (TextView) findViewById(R.id.aav);
        lvl = (TextView) findViewById(R.id.level);
		
		Player demo = new Player();
		demo.addMvp("Test");
		demo.addMvp("Test");
		
		lvl.setText("Lvl: " + demo.getLevel());

        stat1.setTypeface(testTypeface);
        stat2.setTypeface(testTypeface);
        stat3.setTypeface(testTypeface);
        stat4.setTypeface(testTypeface);
        lvl.setTypeface(testTypeface);

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

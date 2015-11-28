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
		TextView mainT = (TextView) findViewById(R.id.playername);
		Team demo = new Team();

		demo.getPlayer(1).setName("Robin");
		mainT.setText(demo.getPlayer(1).getName());
		
    }
}

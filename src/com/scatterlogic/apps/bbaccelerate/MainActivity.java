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
        setContentView(R.layout.main);
		TextView mainT = (TextView) findViewById(R.id.maint);
		Player demo = new Player();
		mainT.setText(demo.getSaveString());
		
		Player demo2 = new Player(demo.getSaveString());
		demo2.addCasualty("Test");
		mainT.setText(demo2.undoLast());
		
    }
}

package com.scatterlogic.apps.bbaccelerate;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class TeamDisplayActivity extends Activity
{
	public void OnCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.teamcard);
		LinearLayout[] teamLines = new LinearLayout[4];
		
		teamLines[0] = (LinearLayout) findViewById(R.id.line1);
		teamLines[1] = (LinearLayout) findViewById(R.id.line2);
		teamLines[2] = (LinearLayout) findViewById(R.id.line3);
		teamLines[3] = (LinearLayout) findViewById(R.id.line4);
		
	}
	public void OnResume(){
		
	}
	public void OnDestroy(){
		
	}
}

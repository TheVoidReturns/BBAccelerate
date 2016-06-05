package com.scatterlogic.apps.bbaccelerate;
import android.widget.*;
import android.text.*;
import android.app.*;
import android.content.*;
import android.widget.TableLayout.*;
import android.graphics.*;
import android.view.*;


public class gameEventPanel
{
	LinearLayout EventPanel;
	String feedback;
	boolean resolved;
	
	
	public gameEventPanel(String title, String info, String csQuestions, Context context, 
			int titleBG, int titleText, int infoBG, int infoText){

		LayoutParams widthOfView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1f);
		
		EventPanel = new LinearLayout(context);		
		EventPanel.setOrientation(LinearLayout.VERTICAL);
		EventPanel.setLayoutParams(widthOfView);
		
		TextView titleBar = new TextView(context);
		titleBar.setBackgroundResource(titleBG);
		titleBar.setTextColor(context.getResources().getColor(titleText));
		titleBar.setTypeface(null,Typeface.BOLD);
		titleBar.setGravity(Gravity.CENTER);
		
		TextView infoPanel = new TextView(context);
		infoPanel.setBackgroundResource(infoBG);
		infoPanel.setTextColor(context.getResources().getColor(infoText));
		
		
		resolved = false;
		
		titleBar.setText(title);
		infoPanel.setText(info);
		feedback = "Info only";
		
		EventPanel.addView(titleBar);
		EventPanel.addView(infoPanel);
		//split buttons to array
		//loop array until buttons established
		//add listeners to buttons
		//set outputText when pressed and replace all buttons with text view of selection.
		
		
	}
	public String getFeedback(){
		return feedback;
	}
}

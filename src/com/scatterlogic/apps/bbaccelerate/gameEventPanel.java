package com.scatterlogic.apps.bbaccelerate;
import android.util.Log;
import android.widget.*;
import android.text.*;
import android.app.*;
import android.content.*;
import android.widget.TableLayout.*;
import android.graphics.*;
import android.view.*;
import java.util.Observable;
import java.util.Scanner;


public class gameEventPanel
{
	LinearLayout EventPanel,buttonPanel;
	String feedback;
	boolean resolved;
	TextView infoPanel, titleBar;
	String buttons[];
	Button[] csqButtons;
	int i;

	public gameEventPanel(String title, String info, String csQuestions, Context context, 
			int titleBG, int titleText, int infoBG, int infoText){

		LayoutParams widthOfView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1f);

		EventPanel = new LinearLayout(context);
		EventPanel.setOrientation(LinearLayout.VERTICAL);
		EventPanel.setLayoutParams(widthOfView);

		titleBar = new TextView(context);
		titleBar.setBackgroundResource(titleBG);
		titleBar.setTextColor(context.getResources().getColor(titleText));
		titleBar.setTypeface(null, Typeface.BOLD);
		titleBar.setGravity(Gravity.CENTER);

		infoPanel = new TextView(context);
		infoPanel.setBackgroundResource(infoBG);
		infoPanel.setTextColor(context.getResources().getColor(infoText));


		resolved = false;

		titleBar.setText(title);
		infoPanel.setText(info);
		if (csQuestions.length() <= 0){
			feedback = "Info only";
		} else {
			feedback = "";
		}

		EventPanel.addView(titleBar);
		EventPanel.addView(infoPanel);
		if (csQuestions.length() == 0){
			csQuestions="OK";
		}
		if (csQuestions.length() >= 1){
			//split buttons to array
			buttons = csQuestions.split(",");
			Log.d("Pre Loop","We got " + buttons.length + " buttons passed");
			csqButtons = new Button[buttons.length];
			//loop array until buttons established
			for (i = 0; i < buttons.length; i++) {
				Log.d("In Loop", "Adding button " + buttons[i]);
				csqButtons[i] = new Button(context);
				csqButtons[i].setText(buttons[i]);
				csqButtons[i].setLayoutParams(widthOfView);
			}
		}
		
	}
	public String getFeedback(){
		Log.d("getFeedback","Feedback requested");
		return feedback;
	}
	public Button[] getButtons(){
		Log.d("getButtons","sending array of " + csqButtons.length);
		return csqButtons;
	}
	public void AddText(String additionalText){
		infoPanel.setText(infoPanel.getText() + additionalText);
	}
	public LinearLayout getPanel(){
		return EventPanel;
	}
}

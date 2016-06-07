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
	TextView infoPanel, titleBar;
	String buttons[];
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
		titleBar.setTypeface(null,Typeface.BOLD);
		titleBar.setGravity(Gravity.CENTER);
		
		infoPanel = new TextView(context);
		infoPanel.setBackgroundResource(infoBG);
		infoPanel.setTextColor(context.getResources().getColor(infoText));
		
		
		resolved = false;
		
		titleBar.setText(title);
		infoPanel.setText(info);
		feedback = "Info only";
		
		EventPanel.addView(titleBar);
		EventPanel.addView(infoPanel);
		if (csQuestions.length() >= 1){
			//split buttons to array
			buttons = csQuestions.split(",");
			Button[] csqButtons = new Button[csQuestions.length()];
			//loop array until buttons established
			LinearLayout buttonPanel = new LinearLayout(context);		
			buttonPanel.setOrientation(LinearLayout.HORIZONTAL);
			buttonPanel.setLayoutParams(widthOfView);
			
			for (i = 0; i < buttons.length; i++){
				csqButtons[i] = new Button(context);
				csqButtons[i].setText(buttons[i]);
				csqButtons[i].setLayoutParams(widthOfView);
				buttonPanel.addView(csqButtons[i]);
				final String buttonName = buttons[i];
  				csqButtons[i].setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
					  infoPanel.setText("Clicked " + buttonName);
		  			}
	  			});
			}
			
			//set outputText when pressed and replace all buttons with text view of selection.
			EventPanel.addView(buttonPanel);
		}
		
	}
	public String getFeedback(){
		return feedback;
	}
}

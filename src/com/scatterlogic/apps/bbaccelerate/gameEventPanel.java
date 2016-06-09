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


public class gameEventPanel extends Observable implements Runnable
{
	LinearLayout EventPanel,buttonPanel;
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
		if (csQuestions.length() <= 0){
			feedback = "Info only";
		} else {
			feedback = "";
		}
		
		EventPanel.addView(titleBar);
		EventPanel.addView(infoPanel);
		if (csQuestions.length() >= 1){
			//split buttons to array
			buttons = csQuestions.split(",");
			Button[] csqButtons = new Button[csQuestions.length()];
			//loop array until buttons established
			buttonPanel = new LinearLayout(context);		
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
					  infoPanel.setText(infoPanel.getText() +"\nClicked " + buttonName);
					  feedback = buttonName;
					  EventPanel.removeView(buttonPanel);
					  resolved = true;
		  			}
	  			});
			}
			//set outputText when pressed and replace all buttons with text view of selection.
			EventPanel.addView(buttonPanel);
		}
		
	}
	public String getFeedback(){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				//while (feedback.equalsIgnoreCase(""));
			}
		});

		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Log.d("Feedback", "Feeding back " + feedback);
		return feedback;
	}
	public LinearLayout getPanel(){
		return EventPanel;
	}

    @Override
    public void run() {
        while(true){
            String response = feedback;
            resolved = true;
            notifyObservers(response);
        }
    }
}

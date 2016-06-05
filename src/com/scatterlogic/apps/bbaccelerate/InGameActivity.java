package com.scatterlogic.apps.bbaccelerate;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.view.*;
import android.util.Log;

public class InGameActivity extends Activity
{
	Button[] allButtons = new Button[8];
	String[] teamNames;
	Button kickOffButton, pickUpButton, blockButton, throwInButton,passButton,
	interceptButton, dodgeButton,endTurnButton;
	//ImageView dieOne,dieTwo,dieThree;
	TextView teamWhoseTurnItIsTV, turnNumberTV, reRollsTV, TimerTV;
	LinearLayout actionLogPanel;
	Dice dice;
	Integer turnNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ingame);
		assignXMLComponentsToVariables();
		tidyUpLayout();
		
		teamNames = new String[] {"Team 1", "Team 2"};
		turnNumber = 0;
		
		teamWhoseTurnItIsTV.setText(teamNames[0]);
		turnNumberTV.setText("Turn\n"+turnNumber);
		/*
		rollOne = (Button) findViewById(R.id.roll1);
		rollOne.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					feedback.setText("");
					dice.roll();
					feedback.setText(dice.getValueAsString());
					dieTwo.setImageDrawable(dice.getPictureOfDie());
					dieOne.setImageDrawable(null);
					dieThree.setImageDrawable(null);
				}
			});
		*/
		
		for(int i = 1; i<10;i++){
			actionLogPanel.addView(new gameEventPanel("Title","Info","",this,R.color.deepgreen,R.color.white,R.color.green,R.color.black).EventPanel);
			actionLogPanel.addView(new gameEventPanel("Title2","Info","",this,R.color.deepblue,R.color.white,R.color.blue,R.color.white).EventPanel);
			actionLogPanel.addView(new gameEventPanel("Title3","Info","",this,R.color.deepred,R.color.white,R.color.red,R.color.white).EventPanel);
			actionLogPanel.addView(new gameEventPanel("Title4","Info","",this,R.color.purple,R.color.white,R.color.bluegrey,R.color.black).EventPanel);
			actionLogPanel.addView(new gameEventPanel("Title5","Info","",this,R.color.yellow,R.color.white,R.color.bluegrey,R.color.black).EventPanel);
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		
	}
	
	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
	}
	
	private void assignXMLComponentsToVariables(){
		teamWhoseTurnItIsTV= (TextView) findViewById(R.id.teamturnname);
		turnNumberTV= (TextView) findViewById(R.id.turnnumber);
		reRollsTV= (TextView) findViewById(R.id.rerolls);
		TimerTV= (TextView) findViewById(R.id.timer);

		allButtons[0] = kickOffButton = (Button) findViewById(R.id.kickoff);
		allButtons[1] = pickUpButton = (Button) findViewById(R.id.pickup);
		allButtons[2] = blockButton = (Button) findViewById(R.id.block);
		allButtons[3] = throwInButton = (Button) findViewById(R.id.throwin);
		allButtons[4] = passButton = (Button) findViewById(R.id.pass);
		allButtons[5] = interceptButton = (Button) findViewById(R.id.intercept);
		allButtons[6] = dodgeButton = (Button) findViewById(R.id.dodge);
		allButtons[7] = endTurnButton = (Button) findViewById(R.id.endturn);
		

		actionLogPanel = (LinearLayout) findViewById(R.id.gamelogpanel);
	}
	private void tidyUpLayout(){
		endTurnButton.post(new Runnable() {
				@Override
				public void run() {
					int universalPanelWidth = 0;

					for (int i = 0; i < 8; i++){
						Log.d("Panel Resizer","Universal Width: " + universalPanelWidth + " Current Button: " + allButtons[i].getWidth());
						if (allButtons[i].getWidth() > universalPanelWidth){
							universalPanelWidth = allButtons[i].getWidth() ;	
						}
					}
					for (int i = 0; i < 8; i++){
						Log.d("Panel Resizer","Setting Button To: " + universalPanelWidth);

						allButtons[i].setWidth(universalPanelWidth);
					}
				}
			});
	}

}

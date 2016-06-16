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
	LinearLayout actionLogPanel,buttonPanel;
	ScrollView scroller;
	Dice dice;
	Integer turnNumber;
	TeamDetails teamDetails;
	String currentTeam;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ingame);
		teamDetails = new TeamDetails("Innocent Guns","Ridley Trott's Glade Runners");
		assignXMLComponentsToVariables();
		tidyUpLayout();

		PreMatchEvent preMatchEvent = new PreMatchEvent(getApplicationContext(),actionLogPanel,buttonPanel,scroller,teamDetails);

		teamWhoseTurnItIsTV.setText("Team Name");
		turnNumberTV.setText("Turn\n" + teamDetails.getTurnNumber());
		reRollsTV.setText("Re-Rolls\nN/A");
		TimerTV.setText("Timer:\nN/A");
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
		buttonPanel = (LinearLayout) findViewById(R.id.buttonpanel);
		scroller = (ScrollView) findViewById(R.id.scroller);

		kickOffButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					KickOffEvent koe = new KickOffEvent(getApplicationContext(),actionLogPanel,buttonPanel,scroller,teamDetails);
				}
			});
		pickUpButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					PickUpEvent koe = new PickUpEvent(getApplicationContext(),actionLogPanel,buttonPanel,scroller,teamDetails);
				}
			});
		blockButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				BlockEvent koe = new BlockEvent(getApplicationContext(),actionLogPanel,buttonPanel,scroller,teamDetails);
			}
		});
		endTurnButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				tidyUpLayout();
				//change the teamName at the top
				if (teamDetails.isTeamOneFirstToMove) {
					if (teamWhoseTurnItIsTV.getText().toString().equalsIgnoreCase(teamDetails.teamOneName))
						teamWhoseTurnItIsTV.setText(teamDetails.teamTwoName);
					else{
						teamWhoseTurnItIsTV.setText(teamDetails.teamOneName);
						teamDetails.setTurnNumber(teamDetails.getTurnNumber()+1);
						turnNumberTV.setText("Turn\n" + teamDetails.getTurnNumber());
					}
				} else{
					if (teamWhoseTurnItIsTV.getText().toString().equalsIgnoreCase(teamDetails.teamTwoName))
						teamWhoseTurnItIsTV.setText(teamDetails.teamOneName);
					else{
						teamWhoseTurnItIsTV.setText(teamDetails.teamTwoName);
						teamDetails.setTurnNumber(teamDetails.getTurnNumber()+1);
						turnNumberTV.setText("Turn\n" + teamDetails.getTurnNumber());
					}
				}
			}
		});

		turnNumberTV.setText("Turn\n"+teamDetails.getTurnNumber());
	}
	private void tidyUpLayout(){
		endTurnButton.post(new Runnable() {
				@Override
				public void run() {
					int universalPanelWidth = 0;
					for (int i = 0; i < 8; i++){
						if (allButtons[i].getWidth() > universalPanelWidth){
							universalPanelWidth = allButtons[i].getWidth() ;	
						}
					}
					for (int i = 0; i < 8; i++){
						allButtons[i].setWidth(universalPanelWidth);
					}
					turnNumberTV.setWidth(universalPanelWidth);
				}
			});
		scroller.post(new Runnable() {
			@Override
			public void run() {
				// This method works but animates the scrolling
				// which looks weird on first load
				// scroll_view.fullScroll(View.FOCUS_DOWN);

				// This method works even better because there are no animations.
				scroller.scrollTo(0, scroller.getBottom());
			}
		});
	}

}

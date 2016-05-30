package com.scatterlogic.apps.bbaccelerate;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.view.*;

public class InGameActivity extends Activity implements OnItemSelectedListener
{
	Button kickOffButton, pickUpButton, blockButton, throwInButton,passButton,
	interceptButton, dodgeButton,endTurnButton;
	//ImageView dieOne,dieTwo,dieThree;
	TextView teamWhoseTurnItIsTV, turnNumberTV, reRollsTV, TimerTV;
	Dice dice;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ingame);

		teamWhoseTurnItIsTV= (TextView) findViewById(R.id.teamturnname);
		turnNumberTV= (TextView) findViewById(R.id.turnnumber);
		reRollsTV= (TextView) findViewById(R.id.rerolls);
		TimerTV= (TextView) findViewById(R.id.timer);
		
		
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

    @Override
	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
				dice = new Dice(6,this);
                break;
            case 1:
                dice = new Dice("Block",this);
                break;
            case 2:
                dice = new Dice(8,this);
                break;
			case 3:
                dice = new Dice("Injury",this);
                break;
			case 4:
                dice = new Dice("Serious Injury",this);
                break;
        }
    }
	@Override
	public void onNothingSelected(AdapterView<?> p1)
	{
		// TODO: Implement this method
	}

}

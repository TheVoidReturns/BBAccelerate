package com.scatterlogic.apps.bbaccelerate;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.view.*;

public class DiceRollActivity extends Activity implements OnItemSelectedListener
{

	static String[] diceTypes = {"d6","Block","d8","Injury","Serious Injury Table"};
	Button rollOne, rollTwo, rollThree;
	ImageView dieOne,dieTwo,dieThree;
	TextView feedback;
	Dice dice;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diceroller);
		Spinner dropDown = (Spinner) findViewById(R.id.dicerollerSpinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, diceTypes);
		dropDown.setAdapter(adapter);
		dropDown.setOnItemSelectedListener(this);
		
		
		dieOne = (ImageView) findViewById(R.id.die1);
		dieTwo = (ImageView) findViewById(R.id.die2);
		dieThree = (ImageView) findViewById(R.id.die3);
		

		feedback = (TextView) findViewById(R.id.feedbackline);
		
		

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
		rollTwo = (Button) findViewById(R.id.roll2);
		rollTwo.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					feedback.setText("");
					dice.roll();
					feedback.setText(dice.getValueAsString()+ ", ");
					dieOne.setImageDrawable(dice.getPictureOfDie());
					dice.roll();
					feedback.setText(feedback.getText() + dice.getValueAsString());
					dieTwo.setImageDrawable(dice.getPictureOfDie());
					dieThree.setImageDrawable(null);
				}
			});
		rollThree = (Button) findViewById(R.id.roll3);
		rollThree.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					feedback.setText("");
					dice.roll();
					feedback.setText(dice.getValueAsString()+ ", ");
					dieOne.setImageDrawable(dice.getPictureOfDie());
					dice.roll();
					feedback.setText(feedback.getText() + dice.getValueAsString()+ ", ");
					dieTwo.setImageDrawable(dice.getPictureOfDie());
					dice.roll();
					feedback.setText(feedback.getText() + dice.getValueAsString());
					dieThree.setImageDrawable(dice.getPictureOfDie());
				}
			});
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

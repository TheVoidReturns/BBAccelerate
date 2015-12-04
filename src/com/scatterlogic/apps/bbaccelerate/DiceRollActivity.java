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
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diceroller);
		Spinner dropDown = (Spinner) findViewById(R.id.dicerollerSpinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, diceTypes);
		dropDown.setAdapter(adapter);
		dropDown.setOnItemSelectedListener(this);
		rollOne = (Button) findViewById(R.id.button1);
		rollTwo = (Button) findViewById(R.id.button2);
		rollThree = (Button) findViewById(R.id.button3);
		
		dieOne = (ImageView) findViewById(R.id.button1);
		dieTwo = (ImageView) findViewById(R.id.button2);
		dieThree = (ImageView) findViewById(R.id.button3);
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
				dice = new Dice(6);
                break;
            case 1:
                dice = new Dice("Block");
                break;
            case 2:
                dice = new Dice(8);
                break;
			case 3:
                dice = new Dice("Injury");
                break;
			case 4:
                dice = new Dice("Serious Injury");
                break;
        }
    }
	@Override
	public void onNothingSelected(AdapterView<?> p1)
	{
		// TODO: Implement this method
	}
	
	
}

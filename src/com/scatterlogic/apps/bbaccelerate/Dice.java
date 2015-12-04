package com.scatterlogic.apps.bbaccelerate;
import java.util.*;
import android.util.*;

public class Dice
{
	Random random = new Random();
	int sides;
	String specialType;
	int diceValue;
	
	public Dice(int sides){
		this.sides = sides;
		Log.d("Dice","New die made with " + sides + " sides.");
		specialType = "";
		diceValue = 0;
	}
	public Dice(String diceType){
		specialType = diceType;
		sides = 0;
	}
	public void roll(){
		if (sides > 0){
			diceValue = random.nextInt(sides)+1;		
		} else if (specialType.equals("Block")){
			diceValue = (random.nextInt(6)+random.nextInt(6)+2);
		} else if (specialType.equals("Injury")){
			diceValue = (random.nextInt(6)+random.nextInt(6)+2);
		} else if (specialType.equals("Serious Injury")){
			diceValue = (random.nextInt(6)+1)*10;
			diceValue += (random.nextInt(8)+1);
		}
		
		Log.d("Dice","Just rolled " + diceValue + " on a " + sides + " sided die.");
	}
	public int getValue(){
		if (diceValue == 0){
			Log.e("Dice","Value requested, but not rolled!");
		}
		return diceValue;
	}
}

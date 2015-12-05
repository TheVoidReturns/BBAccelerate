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
	public String getValueAsString(){
		if (diceValue == 0){
			return "Value requested, but not rolled!";
		}
		
		if (sides > 0){
			diceValue = random.nextInt(sides)+1;		
			
			return diceValue + " on a " + sides + " sided " + specialType + " die.";
			
		} else if (specialType.equals("Block")){
			diceValue = random.nextInt(6)+1;		
			switch (diceValue){
				case 1:
					return "Attacker Down";
				case 2:
					return "Both Down";
				case 3: case 4:
					return "Push Back";
				case 5:
					return "Defender Stumbles";
				case 6:
					return "Defender Down";
			}
		} else if (specialType.equals("Injury")){
			diceValue = (random.nextInt(6)+random.nextInt(6)+2);
			switch (diceValue){
				case 2: case 3: case 4: case 5:
				case 6: case 7:
					return "Stunned " + diceValue;
				case 8:
					return "Knocked Out, unless Thick Skull (" + diceValue + ")";
				case 9:
					return "Knocked Out. (" + diceValue + ")";
				case 10:
					return "Badly Hurt (" + diceValue + ")";
				case 11:
					return "Seriously Injured (" + diceValue + ")";
				case 12:
					return "Dead";
				}
		} else if (specialType.equals("Serious Injury")){
			diceValue = (random.nextInt(6)+1)*10;
			diceValue += (random.nextInt(8)+1);
			return ""+ diceValue;
		}

		Log.d("Dice","Just rolled " + diceValue + " on a " + sides + " sided die.");
		return "Hideous Dice Error In Program with " + diceValue + " on a " + sides + " sided " + specialType + " die.";
	}
	
}

package com.scatterlogic.apps.bbaccelerate;
import java.util.*;
import android.util.*;

public class Dice
{
	Random random = new Random();
	int sides;
	public Dice(int sides){
		this.sides = sides;
		Log.d("Dice","New die made with " + sides + " sides.");
	}
	public int roll(){
		int roll = random.nextInt(sides+1);
		Log.d("Dice","Just rolled " + roll + " on a " + sides + " sided die.");
		return roll;
	}
}

package com.scatterlogic.apps.bbaccelerate;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.*;
import android.util.*;
import com.scatterlogic.apps.bbaccelerate.*;
import java.util.*;
import android.app.*;
import android.content.res.*;

public class Dice extends Application
{
	Random random = new Random();
	int sides;
	String specialType;
	int diceValue;
	Resources res;
	
	static Drawable[] sixSidedPics;
	static Drawable[] blockDie;
	
	public Dice(int sides, Context context){
		this.sides = sides;
		Log.d("Dice","New die made with " + sides + " sides.");
		specialType = "";
		diceValue = 0; 
		res = context.getResources();
	}
	public Dice(String diceType, Context context){
		specialType = diceType;
		sides = 0;
		res = context.getResources();
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
	public Drawable getPictureOfDie(){
		if (this.sides ==6){
			if (diceValue == 1){
				return res.getDrawable (R.drawable.d6_1);
			}
			if (diceValue == 2){
				return res.getDrawable  (R.drawable.d6_2);
			}
			if (diceValue == 3){
				return res.getDrawable  (R.drawable.d6_3);
			}
			if (diceValue == 4){
				return res.getDrawable  (R.drawable.d6_4);
			}
			if (diceValue == 5){
				return res.getDrawable (R.drawable.d6_5);
			}
			if (diceValue == 6){
				return res.getDrawable  (R.drawable.d6_6);
			}
		} else if (specialType.equals("Block")){	
			switch (diceValue){
				case 1:
					return res.getDrawable  (R.drawable.dblock_adown);
				case 2:
					return res.getDrawable  (R.drawable.dblock_bdown);
				case 3: case 4:
					return res.getDrawable  (R.drawable.dblock_push);
				case 5:
					return res.getDrawable  (R.drawable.dblock_dstumbles);
				case 6:
					return res.getDrawable  (R.drawable.dblock_ddown);
			}
		} else if (this.sides ==8){
			if (diceValue == 1){
				return res.getDrawable (R.drawable.arrow_east);
			}
			if (diceValue == 2){
				return res.getDrawable  (R.drawable.arrow_southeast);
			}
			if (diceValue == 3){
				return imageRotate(R.drawable.arrow_east,90);
			}
			if (diceValue == 4){
				return imageRotate(R.drawable.arrow_southeast,90);
			}
			if (diceValue == 5){
				return imageRotate(R.drawable.arrow_east,180);
			}
			if (diceValue == 6){
				return imageRotate(R.drawable.arrow_southeast,180);
			}
			if (diceValue == 7){
				return imageRotate(R.drawable.arrow_east,270);
			}
			if (diceValue == 8){
				return imageRotate(R.drawable.arrow_southeast,270);
			}
		}
		return res.getDrawable (R.drawable.dopey);
	}
	Drawable imageRotate(int submitted, int rotation){
			Bitmap bmpOriginal = BitmapFactory.decodeResource(res, submitted);
			Bitmap bmResult = Bitmap.createBitmap(bmpOriginal.getWidth(), bmpOriginal.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas tempCanvas = new Canvas(bmResult);
			tempCanvas.rotate(rotation, bmpOriginal.getWidth() / 2, bmpOriginal.getHeight() / 2);
			tempCanvas.drawBitmap(bmpOriginal, 0, 0, null);

			return new BitmapDrawable(res, bmResult);
	}
}

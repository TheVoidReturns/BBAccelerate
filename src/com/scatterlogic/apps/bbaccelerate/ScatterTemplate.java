package com.scatterlogic.apps.bbaccelerate;
import android.util.Log;

import com.scatterlogic.apps.bbaccelerate.Dice;

/**
 * Created by Robin on 28/11/2015.
 */
public class ScatterTemplate {
    static char[] ScatterShow;

    public ScatterTemplate(){
        ScatterShow = new char[8];
        Dice d8 = new Dice(8);
        for (int i = 0; i < 8; i++){
            ScatterShow[i] = '0';
        }
		d8.roll();
        ScatterShow[d8.getValue()-1] = 'X';
    }
    public String toString(){
        String output = "";
        for (int i = 0; i < 3; i++){

            output += ScatterShow[i];
        }
        output +="\n";
        output += ScatterShow[3];
        output += '+';
        output += ScatterShow[4];
        output +="\n";
        for (int i = 5; i < 8; i++){
            output +=ScatterShow[i];
        }
        Log.d("ScatterTemplate",output);
        return output;
    }
}

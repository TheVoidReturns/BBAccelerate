package com.scatterlogic.apps.bbaccelerate;
import java.util.*;
import android.util.Log;

public class Player
{
	final static String NEXT_ITEM = "/,;/";
	final static String NEXT_LINE = "/,;,/";
	
	private int playerNumber;
	private String playerName;
	private String playerType;
	private boolean isStarPlayer;
	
	private short MA;
	private short ST;
	private short AG;
	private short AV;
	
	//some sort of list?
	private ArrayList <String> skills;
	
	private boolean willMissNextGame;
	private boolean hasNigglingInjury;
	
	private ArrayList <String> history;
	private int intercepts, completions, touchdowns, casualties, kills, mvps;
	private int starPlayerPoints;
	private long value;
	
	private String saveString;
	
	public Player(){
		playerName = "Name not set";
		playerType = "Type not selected";
		isStarPlayer = false;
		MA = 0;
		ST = 0;
		AG = 0;
		AV = 0;
		
		skills = new ArrayList();
		willMissNextGame = false;
		hasNigglingInjury = false;
		history = new ArrayList<String>();
		
		intercepts = 0;
		completions = 0;
		touchdowns = 0;
		casualties = 0;
		kills = 0;
		mvps = 0; 
		starPlayerPoints = 0;
		value = 0;
		
		saveString = "";
		Log.d("Player()", "New player made: " + starPlayerPoints + " SPPs.");
	}
	public Player(String inSaveString){
		this();
		FromSaveString(inSaveString);
		saveString = inSaveString;
	}

	public void addIntercept(String detail)
	{
		intercepts++;
		addNote("Interception",detail);
		starPlayerPoints += 2;
	}

	public int getIntercepts() {return intercepts;}

	public void addCompletion(String detail)
	{
		completions++;
		addNote("Completion",detail);
		starPlayerPoints += 1;
	}

	public int getCompletions(){return completions;}

	public void addTouchdowns(String detail)
	{
		touchdowns++;
		addNote("Touchdown", detail);
		starPlayerPoints += 3;
	}

	public int getTouchdowns(){return touchdowns;}

	public void addCasualty(String detail)
	{
		casualties++;
		addNote("Casualty",detail);
		starPlayerPoints += 2;
		Log.d("Player()", "Casualty: " + starPlayerPoints + " SPPs.");
	}

	public int getCasualties()
	{return casualties;}

	public void addKill(String detail)
	{
		kills++;
		addNote("Kill", detail);
		starPlayerPoints += 2;
	}

	public int getKills(){return kills;}

	public void addMvp(String detail)
	{
		mvps++;
		addNote("MVP",detail);
		starPlayerPoints += 5;
	}

	public int getMvps(){return mvps;}
	
	public String getSaveString(){
		makeSaveString();
		return saveString;
	}
	public void addNote(String Title, String Content){
		history.add(Title); 
		history.add(Content);
		history.add("" + System.currentTimeMillis());

		Log.d("Player()", "In addNote (" + Title + "): " + history.size() + " sized history. " + starPlayerPoints + "SPPs");
	}
	private void makeSaveString(){
		saveString = "";
		addItemToSave("" + playerNumber);
		addItemToSave(playerName);
		addItemToSave(playerType);
		addItemToSave(new Boolean(isStarPlayer).toString());
		addItemToSave("" + MA);
		addItemToSave("" + ST);
		addItemToSave("" + AG);
		addItemToSave("" + AV);
		addItemToSave(makeIntoListToAdd(skills));
		
		addItemToSave("" + willMissNextGame);
		addItemToSave("" + hasNigglingInjury);
		addItemToSave(makeIntoListToAdd(history));
		addItemToSave("" + intercepts);
		addItemToSave("" + completions);
		addItemToSave("" + touchdowns);
		addItemToSave("" + casualties);
		addItemToSave("" + kills);
		addItemToSave("" + mvps);

		addItemToSave("" + starPlayerPoints);
		addItemToSave("" + value);
	}
	private String splitReader(String inString){
		String outString = "";
		String[] elementOfString = inString.split(NEXT_LINE);
		for(int i=0; i<elementOfString.length; i++){
			outString =  outString +  "\n" + "i: " + i + "   " + elementOfString[i];
		}
		return outString;
	}
	private void FromSaveString(String inString){
		String[] elementOfString = inString.split(NEXT_LINE);
		playerNumber = Integer.parseInt(elementOfString[0]);
		playerName = elementOfString[1];
		playerType = elementOfString[2];
		isStarPlayer = Boolean.parseBoolean(elementOfString[3]);

		MA = Short.parseShort(elementOfString[4]);
		ST  = Short.parseShort(elementOfString[5]);
		AG  = Short.parseShort(elementOfString[6]);
		AV = Short.parseShort(elementOfString[7]);

		//some sort of list?
		skills = new ArrayList<String> (Arrays.asList(elementOfString[8].split(NEXT_ITEM)));

		willMissNextGame = Boolean.parseBoolean(elementOfString[9]);
		hasNigglingInjury = Boolean.parseBoolean(elementOfString[10]);


		history = new ArrayList<String> (Arrays.asList(elementOfString[11].split(NEXT_ITEM)));
		intercepts = Integer.parseInt(elementOfString[12]); 
		completions = Integer.parseInt(elementOfString[13]);
		touchdowns = Integer.parseInt(elementOfString[14]);
		casualties = Integer.parseInt(elementOfString[15]);
		kills = Integer.parseInt(elementOfString[16]);
		mvps = Integer.parseInt(elementOfString[17]);
		starPlayerPoints = Integer.parseInt(elementOfString[18]);
		value = Long.parseLong(elementOfString[19]);
	}
	private void addItemToSave(String thingToAdd){
		saveString = saveString + thingToAdd + NEXT_LINE;
	}
	private static String makeIntoListToAdd(ArrayList<String> inList){
		String output = "";
		for (String nextItem:inList){
			output= output + NEXT_ITEM + nextItem;
		}
		return output;
	}
	public String undoLast(){

		Log.d("Player()", "In undo: " + history.size() + " sized history. " + starPlayerPoints + "SPPs");
		if (history.size() >=3){
			String lastTime = history.get(history.size()-1);
			history.remove(history.size()-1);
			
			String lastContent = history.get(history.size()-1);
			history.remove(history.size()-1);
	
			String lastTitle = history.get(history.size()-1);
			history.remove(history.size()-1);
			Log.d("Player()", "In undo. Just removed " + lastTitle + ", " + lastContent + ".");
			switch (lastTitle){
				case "Interception":
					starPlayerPoints -=2;
					break;
				case "Completion":
					starPlayerPoints -=1;
					break;
				case "Casualty":		
					starPlayerPoints -=2;
					break;
				case "Kill":
					starPlayerPoints -=2;
					break;
				case "Touchdown":
					starPlayerPoints -=3;
					break;
				case "MVP":
					starPlayerPoints -=5;
					break;
				default:
					Log.d("Undo in Player","No SPPs removed for " +lastTitle+ ": " + lastContent);
			}
			return lastTitle + " " + starPlayerPoints + "SPPs";
		}else{
			Log.d("Undo in Player", "Nothing to undo! History size is " + history.size());
			return "Nothing to undo!";
		}
	}
	public String getName(){
		return playerName;
	}
	public void setName(String name){
		playerName = name;
	}
	public int getNumber(){
		return playerNumber;
	}
	public void setNumber(int pNumber){
		playerNumber = pNumber;
	}
}

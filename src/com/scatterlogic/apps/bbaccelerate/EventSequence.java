package com.scatterlogic.apps.bbaccelerate;


//A class to define a sequence of events, returning the next pertinent event, rolling, and seeking input where appropriate

import java.util.ArrayList;

public class EventSequence{
        private ArrayList<BBEvent> events;
		
		Team team1,team2;

        String tempTitle, tempContent, tempNote;

        public EventSequence(String EventType){
            events = new ArrayList<BBEvent>();
            switch (EventType){
                case "Kick Off":
                    goThroughKickOff();
                    break;
                case "Match Start":
                    break;
             }
            tempTitle = "";
            tempContent = "";
            tempNote = "";
        }
	public BBEvent getNextEvent;

        private void goThroughKickOff(){
            events.add(new BBEvent("Place ball","Place the ball anywhere on the pitch","It's really pretty simple"));

            //now the ball will scatter on a d8 and d6
            Dice d6 = new Dice(6);
			d6.roll();
            int roll = d6.getValue();
            int roll2;
			
            ScatterTemplate scatter = new ScatterTemplate();
            events.add(new BBEvent("Scatter Ball", "Scatters " + roll + " squares like this:\n" + scatter.toString(),"No extra detail"));

            tempTitle = "Kick Off!";
			
			d6.roll();
			roll = d6.getValue();
            d6.roll();
			roll2 = d6.getValue();
			

            tempContent = "";
            tempContent += "Roll of " + roll + " and " + roll2 + ": ";
            tempNote = "";
            switch (roll+roll2){
                case 2:
                    tempContent += "Get The Ref!";
                    break;
                case 3:
                    tempContent += "Riot!";
                    break;
                case 4:
                    tempContent += "Perfect Defence";
                    break;
                case 5:
                    tempContent += "High Kick";
                    break;
                case 6:
                    tempContent += "Cheering Fans";
                    break;
                case 7:
                    tempContent += "Changing Weather";
                    break;
                case 8:
                    tempContent += "Brilliant Coaching";
                    break;
                case 9:
                    tempContent += "Quick Snap";
                    break;
                case 10:
                    tempContent += "Blitz!";
                    break;
                case 11:
                    tempContent += "Throw a Rock";
                    break;
                case 12:
                    tempContent += "Pitch Invasion";
                    break;
            }
            events.add(new BBEvent(tempTitle,tempContent,tempNote));

            scatter = new ScatterTemplate();
            events.add(new BBEvent("Ball Bounces", "Scatters like this:\n" + scatter.toString(),"No extra detail"));

        }

    public String toString(){
        String output = "";

        for (int i = 0; i < events.size(); i++){
            output += "||" + events.get(i).getTitle() + "||\n" + events.get(i).getContent() +"\n\n";
        }

        return output;
    }
}

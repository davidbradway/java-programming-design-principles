/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>(); 

        int index = -2;
        int pos = 0;
        while (pos < myText.length()-key.length()){
            index = myText.indexOf(key, pos);
            if (index == -1 || index >= myText.length()-key.length()){
                return follows;
            }
            follows.add(myText.substring(index+key.length(),index+key.length()+1));

            // I think "+1" is better, but want to match what they did
            //pos = index + 1;
            pos = index + key.length();
        }
        return follows;
    }
 
    // these are implemented differently in child classes
    abstract public String getRandomText(int numChars);
    abstract public String toString();
}


/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
import edu.duke.*;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key){
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
    
    public String getRandomText(int numChars){
        if (myText == null){
            System.out.println("Need to setTtraining(st) first");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        //do not allow the last 4 chars, since nothing follows it
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index,index+4);
        sb.append(key);
        
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
}

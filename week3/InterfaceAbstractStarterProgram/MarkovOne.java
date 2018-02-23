/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
import edu.duke.*;

public class MarkovOne extends AbstractMarkovModel {
        
    public String getRandomText(int numChars){
        if (myText == null){
            System.out.println("Need to setTraining(st) first");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        //do not allow the last char, since nothing follows it
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index,index+1);
        sb.append(key);
        
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 1.";
    }

}

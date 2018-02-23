
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
import edu.duke.*;

public class MarkovModel extends AbstractMarkovModel {
    private int N;
    
    public MarkovModel(int NArg) {
        N = NArg;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            System.out.println("Need to setTraining(st) first");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        //do not allow the last N chars, since nothing follows it
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index,index+N);
        sb.append(key);
        
        for(int k=0; k < numChars-N; k++){
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

    public String toString() {
        return "MarkovModel of order " + N + ".";
    }

}

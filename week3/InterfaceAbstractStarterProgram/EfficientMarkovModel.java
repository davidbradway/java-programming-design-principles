/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;
    private HashMap<String,ArrayList<String>> map;
    
    public EfficientMarkovModel(int NArg) {
        N = NArg;
    }
    
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
    }
    
    private void buildMap(){
        map = new HashMap<String,ArrayList<String>>();

        for(int k=0; k <= myText.length()-N; k++){
            // for each N-character String key int myText,
            String key = myText.substring(k,k+N);

            ArrayList<String> follows;
            // check if map.containsKey(key)
            if (!map.containsKey(key)) {
                // If key is not in HashMap find all the follows characters in an ArrayList
                follows = new ArrayList<String>();
            }
            else {
                // else key has already been added, get follows from HashMap
                follows = map.get(key);
            } 

            if (k+N < myText.length()){
                follows.add(myText.substring(k+N,k+N+1));
            }
            // get it and save it
            map.put(key,follows);
        }
        printHashMapInfo();
    }

    private void printHashMapInfo(){
        if (map.size() < 50){
            for (String s : map.keySet()) {
                // process each key in turn
                System.out.println(s+" : "+map.get(s));
            }
        }
        else {
            System.out.println("map is too big to print");
        }
        System.out.println("Number of keys in the map: "+map.size());

        int maxSizeFollows = 0;
        for (String s : map.keySet()) {
            // process each key in turn
            if (map.get(s).size() > maxSizeFollows){
                maxSizeFollows = map.get(s).size();
            }
        }
        System.out.println("Largest ArrayList size in the HashMap: "+maxSizeFollows);
        
        for (String s : map.keySet()) {
            // process each key in turn
            if (map.get(s).size() == maxSizeFollows){
                System.out.println(s);
            }
        }
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

        // put the first key into the output string
        sb.append(key);
        
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows;
            // get it from map
            follows = map.get(key);
            
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
        return "EfficientMarkovModel of order " + N + ".";
    }
}

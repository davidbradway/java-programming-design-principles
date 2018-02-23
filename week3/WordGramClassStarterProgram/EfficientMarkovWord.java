/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovWord implements IMarkovModel{
    
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;
    
    public EfficientMarkovWord(int N) {
        myRandom = new Random();
        myOrder = N;
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
    }

    private void buildMap(){
        map = new HashMap<WordGram,ArrayList<String>>();

        if (myText == null){
            System.out.println("Need to setTraining(st) first");
            return;
        }
        for(int k=0; k <= myText.length-myOrder; k++){
            // for each N-character String key int myText,
            WordGram kGram = new WordGram(myText, k, myOrder);

            // check if map.containsKey(key)
            if (!map.containsKey(kGram)) {
                // If key is not in HashMap find all the follows words in an ArrayList
                ArrayList<String> follows = getFollows(kGram);
                // get it and save it
                map.put(kGram,follows);
            }
            else {
                // else key has already been added to the map
            } 
        }
        //printHashMapInfo();
    }
    
    private void printHashMapInfo(){
        if (map.size() < 50){
            for (WordGram wg : map.keySet()) {
                // process each key in turn
                System.out.println(wg+" : "+map.get(wg));
            }
        }
        else {
            System.out.println("map is too big to print");
        }
        System.out.println("Number of keys in the map: "+map.size());

        int maxSizeFollows = 0;
        for (WordGram wg : map.keySet()) {
            // process each key in turn
            if (map.get(wg).size() > maxSizeFollows){
                maxSizeFollows = map.get(wg).size();
            }
        }
        System.out.println("Largest ArrayList size in the HashMap: "+maxSizeFollows);
        
        for (WordGram wg : map.keySet()) {
            // process each key in turn
            if (map.get(wg).size() == maxSizeFollows){
                System.out.println(wg);
            }
        }
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public String getRandomText(int numWords){
        if (myText == null){
            System.out.println("Need to setTraining(st) first");
            return "";
        }
    
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText,index,myOrder);
        for (int i=0; i<kGram.length(); i++){
            sb.append(kGram.wordAt(i));
            sb.append(" ");            
        }
        
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows;
            // get it from map
            follows = map.get(kGram);
            //System.out.println(key+" : "+follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    private int indexOf(String[] words, WordGram target, int start){
        for(int k=start; k<words.length-target.length(); k++){
            // create a WordGram to compare with target
            WordGram wg = new WordGram(words,k,target.length());
            if (wg.equals(target)){
                return k;
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        
        while (start < myText.length-kGram.length()){
            int index = indexOf(myText, kGram, start);
            if (index == -1 || index >= myText.length-kGram.length()){
                return follows;
            }
            follows.add(myText[index+kGram.length()]);
            start = index + kGram.length();
        }
        return follows;
    }

    public String toString() {
        return "EfficientMarkovWord of order "+myOrder+".";
    }
}

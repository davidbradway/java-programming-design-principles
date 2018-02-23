/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        // random word to start with
        int index = myRandom.nextInt(myText.length-2);
        String key1 = myText[index];
        sb.append(key1);
        sb.append(" ");
        // get second key from myText
        String key2 = myText[index+1];
        sb.append(key2);
        sb.append(" ");
        
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            //System.out.println(key1+" "+key2+" : "+follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target1, String target2, int start){
        for(int k=start; k<words.length-1; k++){
            if (words[k].equals(target1) && words[k+1].equals(target2)){
                return k;
            }
        }
        return -1;
    }

    public void testIndexOf(){
        String[] words = {"this","is","just","a","test","yes","this","is","a","simple","test"};
        System.out.println(indexOf(words, "this", "is", 0));
        System.out.println(indexOf(words, "this", "is", 3));
        System.out.println(indexOf(words, "frog", "is", 0));
        System.out.println(indexOf(words, "frog", "is", 5));
        System.out.println(indexOf(words, "simple", "test", 2));
        System.out.println(indexOf(words, "a", "test", 4));
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        for (int k=0; k<myText.length-2; k++){
            int index = indexOf(myText, key1, key2, start);
            if (index == -1 || index >= myText.length-2) {
                return follows;
            }
            // index is where key1 was found, so look past key2 to new word
            follows.add(myText[index+2]);
            
            //start = index + 2;
            start = index + 1;
        }
        return follows;
    }

    public String toString() {
        return "MarkovWordTwo.";
    }
}

/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;

public class MarkovWord implements IMarkovModel{
    
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int N) {
        myRandom = new Random();
        myOrder = N;
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public String getRandomText(int numWords){

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText,index,myOrder);
        for (int i=0; i<kGram.length(); i++){
            sb.append(kGram.wordAt(i));
            sb.append(" ");            
        }
        
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
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
        for (int k=0; k<myText.length-kGram.length(); k++){
            int index = indexOf(myText, kGram, start);
            if (index == -1 || index >= myText.length-kGram.length()) {
                return follows;
            }
            follows.add(myText[index+kGram.length()]);
            start = index + kGram.length();
        }
        return follows;
    }

    public String toString() {
        return "MarkovWord model of order "+myOrder+".";
    }
}

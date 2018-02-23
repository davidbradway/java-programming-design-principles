/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import edu.duke.*; 
import java.lang.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        markov.setRandom(seed);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 42;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        long before = System.nanoTime();
        MarkovModel mm = new MarkovModel(2);
        runModel(mm, st, size, seed);
        System.out.print("time of MarkovModel in ns = ");
        System.out.println(System.nanoTime() - before);
        
        before = System.nanoTime();
        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        runModel(emm, st, size, seed);
        System.out.print("time of EfficientMarkovModel in ns = ");
        System.out.println(System.nanoTime() - before);
    }

    public void testHashMap(){
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        int seed = 42;
        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        emm.setTraining(st);
    
        FileResource fr = new FileResource();
        st = fr.asString();
        st = st.replace('\n', ' ');
        size = 10;
        seed = 531;
        emm = new EfficientMarkovModel(5);
        runModel(emm, st, size, seed);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}

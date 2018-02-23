
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        MarkovWord markovWord = new MarkovWord(3); 
        runModel(markovWord, st, 200, 643);
    } 

    public void testHashMap(){
        String st = "this is a test yes this is really a test";
        int size = 50;
        int seed = 42;
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        emw.setTraining(st);
        runModel(emw, st, size, seed);
    
        st = "this is a test yes this is really a test yes a test this is wow";
        emw.setTraining(st);
        runModel(emw, st, size, seed);
        
        /*FileResource fr = new FileResource();
        st = fr.asString();
        st = st.replace('\n', ' ');
        size = 10;
        seed = 615;
        emm = new EfficientMarkovModel(5);*/
    }

    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 100;
        int seed = 42;
        
        long before = System.nanoTime();
        MarkovWord mw = new MarkovWord(2);
        runModel(mw, st, size, seed);
        System.out.print("time of MarkovWord in ns = ");
        System.out.println(System.nanoTime() - before);
        
        before = System.nanoTime();
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        runModel(emw, st, size, seed);
        System.out.print("time of EfficientMarkovWord in ns = ");
        System.out.println(System.nanoTime() - before);
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

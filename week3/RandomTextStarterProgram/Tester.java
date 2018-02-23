
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");

        ArrayList<String> follows = markov.getFollows("t");
        System.out.println("Should return “h”, “e”, “ “, “h”, “e”, “.”");
        System.out.println(follows);
        System.out.println(follows.size());
        
        follows = markov.getFollows("e");
        System.out.println("Should return “s”, “s”, “s”");
        System.out.println(follows);
        System.out.println(follows.size());
        
        follows = markov.getFollows("es");
        System.out.println("Should return “t”, “ “, “t”");
        System.out.println(follows);
        System.out.println(follows.size());
        
        follows = markov.getFollows(".");
        System.out.println(follows);
        System.out.println(follows.size());
        
        follows = markov.getFollows("t.");
        System.out.println(follows);
        System.out.println(follows.size());
    }

    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println("You should get 11548.");
        System.out.println(follows.size());
        
        follows = markov.getFollows("o");
        System.out.println(follows.size());   

        follows = markov.getFollows("he");
        System.out.println(follows.size());   
    }
}

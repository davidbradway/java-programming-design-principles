/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String s1 = q1.getInfo();
        String s2 = q2.getInfo();
        int strCmp = s1.substring(s1.lastIndexOf(" ")).compareTo(s2.substring(s2.lastIndexOf(" ")));
        if (strCmp == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return strCmp;
    }
}

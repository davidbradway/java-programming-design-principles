/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        // This method makes one pass of bubble sort on the ArrayList. It should take
        // advantage of the fact that the last numSorted elements are already in sorted order.
        for (int i=0; i<quakeData.size() - numSorted -1; i++){
            if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()) {
                // Swap them
                QuakeEntry temp = quakeData.get(i+1);
                quakeData.set(i+1,quakeData.get(i));
                quakeData.set(i,temp);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for (int i=0; i<quakes.size() - 1; i++){
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       int i;
       for (i=0; !checkInSortedOrder(in); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
       }
       System.out.println("Took this many passes: "+i);
        
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
       
       //for (int i=0; i< in.size(); i++) {
       //for (int i=0; i< 70; i++) {
       for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
       }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        /* If the ArrayList in has N elements in it, this method should call
         * onePassBubbleSort N – 1 times to sort the elements in in. */
        /*System.out.println("Before pass 0");
        for (QuakeEntry qe: in) {
            System.out.println(qe);
        }*/
        
        for (int i=0; i<in.size()-1; i++){
            onePassBubbleSort(in,i);
            /*System.out.println("After pass "+i);
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
            }*/
        }
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        /*System.out.println("Before pass 0");
        for (QuakeEntry qe: in) {
            System.out.println(qe);
        }*/
        int i; 
        for (i=0; !checkInSortedOrder(in); i++){
            onePassBubbleSort(in,i);
            /*System.out.println("After pass "+i);
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
            }*/
        }
        System.out.println("Took this many passes: "+i);
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";

        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}

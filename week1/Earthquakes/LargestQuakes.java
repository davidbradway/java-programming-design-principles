
/**
 * Find N-largest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;
public class LargestQuakes {
    private int indexOfLargest(ArrayList<QuakeEntry> data){
        int index = -1;        
        double bestYet = -1;
        for(int k=0; k < data.size(); k++){
            QuakeEntry entry = data.get(k);
            double magnitude = entry.getMagnitude();
            if (index == -1 || magnitude > bestYet) {
                bestYet = magnitude;
                index = k;
            }
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for (int j=0; j < howMany; j++){
            // each time through this loop re-initialize to find the next closest...
            int bestIndex = indexOfLargest(copy);
            ret.add(copy.get(bestIndex));
            copy.remove(bestIndex);
        }
        return ret;
    }

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*for(QuakeEntry qe : list){
            System.out.println(qe.toString());
        }*/
        System.out.println("read data for "+list.size());

        /*int index = indexOfLargest(list);
        System.out.println(index);
        System.out.println(list.get(index).toString());*/
        ArrayList<QuakeEntry> largest = getLargest (list, 5);
        for(QuakeEntry qe : largest){
            System.out.println(qe.toString());
        }
        
        /* nov20quakedata.atom (a file with information on 1518 quakes) for the three
         * earthquakes with the largest magnitude.*/
        source = "data/nov20quakedata.atom";
        list  = parser.read(source);
        System.out.println("read data for "+list.size());
        largest = getLargest (list, 3);
        for(QuakeEntry qe : largest){
            System.out.println(qe.toString());
        }
        
        largest = getLargest (list, 5);
        for(QuakeEntry qe : largest){
            System.out.println(qe.toString());
        }

        largest = getLargest (list, 20);
        for(QuakeEntry qe : largest){
            System.out.println(qe.toString());
        }

        largest = getLargest (list, 50);
        for(QuakeEntry qe : largest){
            System.out.println(qe.toString());
        }
    }
}

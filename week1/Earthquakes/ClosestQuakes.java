
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData,
                                            Location current,
                                            int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        /* find the closest number of howMany earthquakes to the current Location and 
         * return them in an ArrayList of type QuakeEntry. The earthquakes should be in the
         * ArrayList in order with the closest earthquake in index position 0. If there are
         * fewer then howMany earthquakes in quakeData, then the ArrayList returned would
         * be the same size as quakeData.*/
        for (int j=0; j < howMany; j++){
            // each time through this loop re-initialize to find the next closest...
            double bestYet = -1;
            int bestIndex = -1;
            for(int k=0; k < copy.size(); k++){
                QuakeEntry entry = copy.get(k);
                double distanceInMeters = current.distanceTo(entry.getLocation());
                if (bestIndex == -1 || distanceInMeters < bestYet) {
                    bestYet = distanceInMeters;
                    bestIndex = k;
                }
            }
            ret.add(copy.get(bestIndex));
            copy.remove(bestIndex);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}

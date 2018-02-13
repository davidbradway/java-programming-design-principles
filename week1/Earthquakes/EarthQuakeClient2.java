import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter f = new MinMagFilter(4.0, "minmag");
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        /*for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }
        f = new MagnitudeFilter(4.0, 5.0); 
        m7 = filter(list, f);
        f = new DepthFilter(-35000.0, -12000.0); 
        m7 = filter(m7, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+m7.size()+" quakes"); */

        Location japan = new Location(35.42, 139.43);
        //System.out.println(japan);
        f = new DistanceFilter(10000000.0, japan, "dist");
        m7 = filter(list, f);
        f = new PhraseFilter("end", "Japan", "phrase");
        m7 = filter(m7, f);
        for (QuakeEntry qe: m7) {
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+m7.size()+" quakes");
        
        /* magnitude between 4.0 and 5.0 inclusive and depth between -35,000.0 and -12,000.0
         * inclusive */ 
        f = new MagnitudeFilter(4.0, 5.0, "Magnitude");
        m7 = filter(list, f);
        f = new DepthFilter(-35000.0, -12000.0, "Depth");
        m7 = filter(m7, f);
        for (QuakeEntry qe: m7) {
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+m7.size()+" quakes");
        
        
        Location loc = new Location(39.7392, -104.9903);
        f = new DistanceFilter(1000000.0, loc, "Distance");
        m7 = filter(list, f);
        f = new PhraseFilter("end", "a", "Phrase");
        m7 = filter(m7, f);
        for (QuakeEntry qe: m7) {
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+m7.size()+" quakes");
        
        f = new MagnitudeFilter(3.5, 4.5, "Magnitude");
        m7 = filter(list, f);
        f = new DepthFilter(-55000.0, -20000.0, "Depth");
        m7 = filter(m7, f);
        for (QuakeEntry qe: m7) {
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+m7.size()+" quakes");
    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        Filter f;
        ArrayList<QuakeEntry> filtered;
        
        f = new MagnitudeFilter(0.0, 2.0, "Magnitude");
        maf.addFilter(f);
        /*filtered = filter(list, maf); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+filtered.size()+" quakes");*/

        f = new DepthFilter(-100000.0, -10000.0, "Depth");
        maf.addFilter(f);
        /*filtered = filter(list, maf); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+filtered.size()+" quakes");*/

        f = new PhraseFilter("any", "a", "Phrase");
        maf.addFilter(f);
        filtered = filter(list, maf); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+filtered.size()+" quakes");

        System.out.println("maf.getName() "+maf.getName());
        
        /* .0 inclusive, to test the depth between -180,000.0 and
         * -30,000.0 inclusive, and if the letter “o” is in the title */
        maf = new MatchAllFilter();
        
        f = new MagnitudeFilter(1.0, 4.0, "Magnitude");
        maf.addFilter(f);
        f = new DepthFilter(-180000.0, -30000.0, "Depth");
        maf.addFilter(f);
        f = new PhraseFilter("any", "o", "Phrase");
        maf.addFilter(f);
        filtered = filter(list, maf); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+filtered.size()+" quakes");
        System.out.println("maf.getName() "+maf.getName());
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        Location loc = new Location(36.1314, -95.9372);
        Filter f = new DistanceFilter(10000000, loc, "dist1");
        maf.addFilter(f);
        ArrayList<QuakeEntry> filtered;
        /*filtered = filter(list, maf); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+filtered.size()+" quakes");*/

        f = new PhraseFilter("any", "Ca", "phrase2");
        maf.addFilter(f);
        /*filtered = filter(list, maf); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+filtered.size()+" quakes");*/

        f = new MagnitudeFilter(0.0, 3.0, "mag2");
        maf.addFilter(f);
        filtered = filter(list, maf); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+filtered.size()+" quakes");
        
        maf = new MatchAllFilter();
        f = new MagnitudeFilter(0.0, 5.0, "Magnitude");
        maf.addFilter(f);
        loc = new Location(55.7308, 9.1153);
        f = new DistanceFilter(3000000, loc, "Distance");
        maf.addFilter(f);
        f = new PhraseFilter("any", "e", "Phrase");
        maf.addFilter(f);
        filtered = filter(list, maf); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        }
        System.out.println("Met criteria for "+filtered.size()+" quakes");
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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

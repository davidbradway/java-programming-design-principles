import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
                                                   double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
                                                      double distMax,
                                                      Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // find all the earthquakes from quakeData that are less than
        // distMax (in kilometers) from the location 'from'. 
        for (QuakeEntry qe : quakeData) {
            double dist = qe.getLocation().distanceTo(from); //returns something in Meters
            //System.out.println(dist);
            if (dist/1000. < distMax) {
                answer.add(qe);
                
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                               double minDepth,
                                               double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            //System.out.println(depth);
            if (depth > minDepth && depth < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
                                                String where,
                                                String phrase) {
        /* “start” means the phrase must start the title,
         * “end” means the phrase must end the title
         * “any” means the phrase is a substring anywhere in the title.*/
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            String info = qe.getInfo();

            if (where.equals("start")){
                if (info.startsWith(phrase)){
                    answer.add(qe);
                }
            }
            else if (where.equals("end")) {
                if (info.endsWith(phrase)){
                    answer.add(qe);
                }
            }
            else if (where.equals("any")) {
                if (info.indexOf(phrase) != -1){
                    answer.add(qe);
                }
            }
            else {
                return answer;
            }
        }
        return answer;
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> big = filterByMagnitude(list, 5.0);
        
        for (QuakeEntry qe : big) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+big.size()+" quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000.0, city);
        //System.out.println(close);
        
        /* print the distance from the earthquake to the specified city, 
         * followed by the information about the city (use getInfo()). */
        for (QuakeEntry qe : close) {
            System.out.println(qe.getLocation().distanceTo(city)/1000.+" "+qe.getInfo());
        }
        System.out.println("Found "+close.size()+" quakes that match that criteria");

    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> filtered = filterByDepth (list, -10000.0, -5000.0);
        
        /* print the distance from the earthquake to the specified city, 
         * followed by the information about the city (use getInfo()). */
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");
        
        /* nov20quakedata.atom (a file with information on 1518 quakes) for quakes with
         * depth between -8000.0 and -5000.0, exclusive*/
        source = "data/nov20quakedata.atom";
        list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        filtered = filterByDepth (list, -8000.0, -5000.0);
        
        /* print the distance from the earthquake to the specified city, 
         * followed by the information about the city (use getInfo()). */
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");
        
        filtered = filterByDepth (list, -12000.0, -10000.0);
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");

        filtered = filterByDepth (list, -4000.0, -2000.0);
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");
    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        //ArrayList<QuakeEntry> filtered = filterByPhrase(list, "end", "California");
        //ArrayList<QuakeEntry> filtered = filterByPhrase(list, "any", "Can");
        ArrayList<QuakeEntry> filtered = filterByPhrase(list, "start", "Explosion");
        
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");
    
        /* nov20quakedata.atom (a file with information on 1518 quakes) for quakes with the
         * phrase “Explosion” at the start of the earthquake’s title.*/
        source = "data/nov20quakedata.atom";
        list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        filtered = filterByPhrase(list, "start", "Explosion");
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");

        /* nov20quakedata.atom (a file with information on 1518 quakes) for quakes with the
         * phrase “California” at the end of the earthquake’s title.*/
        filtered = filterByPhrase(list, "end", "California");
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");

        /* nov20quakedata.atom (a file with information on 1518 quakes) for quakes with the
         * phrase “Creek” as a substring in the earthquake’s title.*/    
        filtered = filterByPhrase(list, "any", "Creek");
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");

        filtered = filterByPhrase(list, "start", "Quarry Blast");
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");

        filtered = filterByPhrase(list, "end", "Alaska");
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");
        
        filtered = filterByPhrase(list, "any", "Can");
        for (QuakeEntry qe : filtered) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+filtered.size()+" quakes that match that criteria");
}
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
}

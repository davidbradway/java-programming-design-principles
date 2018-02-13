
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter
{
    private double maximum;
    private Location loc;
    private String name;
    
    public DistanceFilter(double max, Location locArg, String nameArg) {
        maximum = max;
        loc = new Location(locArg);
        name = nameArg;
        //System.out.println("construct "+loc);
    }

    public boolean satisfies(QuakeEntry qe) {
        //System.out.println("maximum: "+maximum);
        //System.out.println("loc: "+loc);
        //System.out.println("qe: "+qe);
        //Location qeLoc = qe.getLocation();
        //System.out.println("distance2 "+loc.distanceTo(qeLoc));
        //System.out.println("distance: "+qeLoc.distanceTo(loc));
        float distance = qe.getLocation().distanceTo(loc);
        //System.out.println("distance3 "+distance);
        return (distance < maximum);
    }
    
    public String getName(){
        return name;
    }
}


/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter
{
    private double minimum;
    private double maximum;
    private String name;

    public MagnitudeFilter(double min, double max, String nameArg) {
        minimum = min;
        maximum = max;
        name = nameArg;
    }

    public boolean satisfies(QuakeEntry qe) {
        return (qe.getMagnitude() >= minimum && qe.getMagnitude() <= maximum);
    }

    public String getName(){
        return name;
    }    
}

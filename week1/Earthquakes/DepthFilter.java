
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter
{
    private double minimum;
    private double maximum;
    private String name;

    public DepthFilter(double min, double max, String nameArg) {
        minimum = min;
        maximum = max;
        name = nameArg;
    }

    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() >= minimum && qe.getDepth() <= maximum);
    }

    public String getName(){
        return name;
    }    
}

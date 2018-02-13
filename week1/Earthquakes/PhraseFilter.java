
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter
{
    String where;
    String phrase;
    private String name;
    
    public PhraseFilter(String whereArg, String phraseArg, String nameArg) {
        where = whereArg;
        phrase = phraseArg;
        name = nameArg;
    }

    public boolean satisfies(QuakeEntry qe) {
        String info = qe.getInfo();
        if (where.equals("start") && info.startsWith(phrase))  return true;
        if (where.equals("end")   && info.endsWith(phrase))    return true;
        if (where.equals("any")   && info.indexOf(phrase)!=-1) return true;
        return false;
    }
    
    public String getName(){
        return name;
    }    
}

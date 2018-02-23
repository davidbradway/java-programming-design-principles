
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public boolean equals(Object o) {
        // Cast the object to a WordGram
        WordGram other = (WordGram) o;

        if (myWords.length != other.length()){
            return false;
        }
        for(int k=0; k<this.length(); k++){
            if (!myWords[k].equals(other.wordAt(k))){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        
        for(int k=0; k < this.length()-1; k++){
            out.myWords[k] = myWords[k+1];
        }
        // how does the next line work? I thought myWords was Private? no setter?
        out.myWords[myWords.length-1] = word;
        return out;
    }

    public int hashCode(){
        myHash = toString().hashCode();
        return myHash;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < this.length(); k++){
            sb.append(this.wordAt(k));
            sb.append(" ");
        }
        String ret = sb.toString();
        return ret.trim();
    }
}

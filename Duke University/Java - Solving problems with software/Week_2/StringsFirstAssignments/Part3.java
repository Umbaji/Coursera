
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences (String stringa, String stringb){
        int index1= stringb.indexOf(stringa);
        if (stringb.indexOf(stringa, index1)!= -1){
            return true;
    }
        else {
            return false;
        }
    }
    public void testing (){
        String word_1 = "banana";
        String word_2 = "forest"; 
        System.out.println(word_1 + " " + twoOccurences ("a", word_1)); 
        System.out.println(word_2 + " " + twoOccurences ("a", word_2));
         System.out.println(word_1 + " " +lastPart("a", word_1)); 
        System.out.println(word_2 + " " + lastPart("a", word_2));
        
    }
    public String lastPart(String stringa, String stringb){
        int index1= stringb.indexOf(stringa);
        if (stringb.indexOf(stringa, index1)!= -1){
            return stringb.substring(index1);
        }
        else {
            return stringb; 
        }
    }
}

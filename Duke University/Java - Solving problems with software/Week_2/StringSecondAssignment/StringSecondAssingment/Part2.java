
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public int howMany(String stringa, String stringb){
        int count = 0;
        int Index = 0;
    
        while (Index != -1){
            count +=1;
            Index = stringb.indexOf(stringa, Index+stringa.length());
        }
        return count;
    }
    public void testHowMany(){
        String test = "aabaaaa";
        int result = howMany("aa", test);
        System.out.println(result);
    }
}

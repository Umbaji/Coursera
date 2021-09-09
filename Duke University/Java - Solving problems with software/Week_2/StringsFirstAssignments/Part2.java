
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
public String findSimpleGene ( String dna, String startCodon, String stopCodon) {
         int startIndex = dna.indexOf(startCodon);
         
         if (startIndex == -1){
             return "";
            }
         int stopIndex = dna.indexOf(stopCodon, startIndex+3);
          if (((stopIndex - startIndex)%3) == 0){
            return dna.substring(startIndex, stopIndex+3);
        }
        else{
             return "";
        }
    }

  public void testSimpleGene(){
       String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findSimpleGene(ap,"atg", "tag");
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
              System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
       
}
    }  

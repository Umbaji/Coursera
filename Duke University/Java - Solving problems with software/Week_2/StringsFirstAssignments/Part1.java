
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
     public String findSimpleGene ( String dna) {
         int startIndex = dna.indexOf("ATG"); 
         if (startIndex == -1){
             return "";
            }
         int stopIndex = dna.indexOf("TAA", startIndex+3);
         
            if (stopIndex == -1){
             return "";
            }
          String Gene = dna.substring(startIndex, stopIndex+3);
          if (Gene.length()%3 == 0){
            return dna.substring(startIndex, stopIndex+3);
        }
        return "";
    }
    public void testSimpleGene(){
         String dna = "ATGCGGIATGCTAAATC";
       System.out.println("DNA = " + findSimpleGene(dna));
    }
}
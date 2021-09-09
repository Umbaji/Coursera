
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part2 {
       public int findStopCodon ( String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.toLowerCase().indexOf(stopCodon.toLowerCase(), startIndex+3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 ==0 ) {
                return currIndex;
            }
            else {
                currIndex = dnaStr.indexOf(stopCodon.toLowerCase(), currIndex+1);
            }
        }
        return dnaStr.length();
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.toUpperCase().indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna.toUpperCase(),startIndex,"TAA");
        int tagIndex = findStopCodon(dna.toUpperCase(),startIndex,"TAG");
        int tgaIndex = findStopCodon(dna.toUpperCase(),startIndex, "TGA");
        int minIndex = Math.min(Math.min(taaIndex,tagIndex), tgaIndex);
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
     public int findStopIndex(String dna, int where) {
        int startIndex = dna.toUpperCase().indexOf("ATG", where);
        if (startIndex == -1) {
            return -1;
        }
        int taaIndex = findStopCodon(dna.toUpperCase(),startIndex,"TAA");
        int tagIndex = findStopCodon(dna.toUpperCase(),startIndex,"TAG");
        int tgaIndex = findStopCodon(dna.toUpperCase(),startIndex,"TGA");
        int minIndex = Math.min(Math.min(taaIndex,tagIndex), tgaIndex);
        if(minIndex == dna.length()){
            return -1;
        }
        return minIndex;
    }
    public void printAllGenes(String dna){
        int startIndex = 0;
        while (true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();
        }
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0 ;
        while (true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            count +=1;
            startIndex = dna.indexOf(currentGene, startIndex)+ currentGene.length();
        
        }
        return count ; 
    }
    public void testCountGenes (){
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println(countGenes(dna));
    }
       public int findElem(String input, String element) {
        int count = 0;
        String norm_Elem = element.toUpperCase();
        String norm_Input = input.toUpperCase();
       int index = norm_Input.indexOf(norm_Elem);
       int len =element.length();
       while (true){
           
           if (index == -1 || index >= (input.length() - len)){
               break;
           }
           count+=1;
           index = norm_Input.indexOf(norm_Elem,index+len);
       }
       return count;
   }
 
    public double cgRatio (String dna){
        int cg_Count = findElem(dna,"c")+findElem(dna,"g");
        System.out.println(cg_Count);
        System.out.println(dna.length());
        double result = (((double)cg_Count)/ dna.length());
        return result;
    }
    public int countCTG(String dna){
        int ctg_Count = findElem(dna,"ctg");
        return ctg_Count;
    }
    public void processGenes(StorageResource sr){
        int count_Len = 0; 
        int count_cg_Ratio = 0;
        int max_Len = 0;
        for (String element : sr.data()){
            int current_Len = element.length();
            if ( current_Len > 60){
                System.out.println(element);
                count_Len+=1;
            }
            if (cgRatio(element) > 0.35){
            System.out.println(element);
            count_cg_Ratio +=1;
            }
            if (current_Len >max_Len){
                max_Len=current_Len; 
            }
        }
        System.out.println("There are" + count_Len + "strings longer than 60 characters");
        System.out.println("There are" + count_cg_Ratio+ "strings whose C-G- ratio is higher than 0.35");
        System.out.println("The longest gene has" + max_Len+ "characters");
    }
        public StorageResource store_Dna_In_Resource (String dna) {
        StorageResource store = new StorageResource();
        
        String dnaLower = dna.toLowerCase();
        int startIndex = 0;
        while (true){
        String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            store.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
                         
        }
        return store;
    }

    void testPart2(){
        String dna = "ATGCCATAGATGCTGTGA";
        double ratio_cg = cgRatio(dna);
        float countCTG = countCTG(dna);
        System.out.println(ratio_cg);
        System.out.println(countCTG);
        
    }
    
    void testprocessGenes (){
        // String case_1 = "ATGAACAGCTGCGTCTAA"; 
        // String case_2 = "ATGTACTAG";
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
  
        StorageResource test_Storage = store_Dna_In_Resource(dna);
        // for (String word : fr.words()){
            // test_Storage.add(word);
        // }
       processGenes(test_Storage);
       System.out.println(dna);
       int num_Genes = countGenes(dna);
       
      System.out.println("The number of dna in the string is " + num_Genes );
      System.out.println("The number of dna in the string is " + test_Storage.size());
      System.out.println("The CTG codon appears " + countCTG(dna) + "times");
    }
}

    
 

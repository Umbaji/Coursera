
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*; 
import org.apache.commons.csv.*; 
import java.io.*; 
public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null ; 
        for (CSVRecord currentRow : parser){
            getLargestOfTwo(currentRow, largestSoFar);
        }
        
        return largestSoFar;

}
public void testHottestInDay (){
    FileResource fr = new FileResource ("data/2015/weather-2015-01-01.csv"); 
    CSVRecord largest = hottestHourInFile(fr.getCSVParser());
    System.out.println ( "hottest temperature was " + largest.get("Temperature") + "at " + largest.get("TimeEST"));
}
public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar){

 if (largestSoFar == null){
                largestSoFar = currentRow; 
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("Temperature"));
                double largestTemp = Double.parseDouble(largestSoFar.get("Temperature"));
                
                if (currentTemp > largestTemp){
                    largestSoFar = currentRow;
                }
            } 
            return largestSoFar;
        }
        
public CSVRecord hottestHourInManyDays(){
    CSVRecord largestSoFar = null ; 
    DirectoryResource dr = new DirectoryResource (); 
    
    for (File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f); 
        CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
        getLargestOfTwo(currentRow, largestSoFar);
    }
    return largestSoFar;
}
    
}


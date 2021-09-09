
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*; 
import org.apache.commons.csv.*; 
import java.io.*; 

public class CSVMin {
       public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestSoFar = null ; 
        for (CSVRecord currentRow : parser){
            getLowestOfTwo(currentRow, lowestSoFar);
        }
        
        return lowestSoFar;

}
       
public CSVRecord getLowestOfTwo (CSVRecord currentRow, CSVRecord lowestSoFar){

 if (lowestSoFar == null){
                lowestSoFar = currentRow; 
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                
                if
                (currentTemp < largestTemp && currentTemp != -9999){
                    lowestSoFar = currentRow;
                }
            } 
            return lowestSoFar;
        }
 

public String fileWithColdestTemperature (){
    String file_Name =""; 
    CSVRecord lowestSoFar = null ;
    DirectoryResource dr = new DirectoryResource (); 
        for (File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f); 
        CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
        getLowestOfTwo(currentRow, lowestSoFar);
        if (lowestSoFar == currentRow){
            file_Name = f.getName();
        }
    }
    return file_Name;
}

public void testFileWithColdestTemperature() {
        String coldestname = fileWithColdestTemperature();
        CSVRecord coldest = null;
        System.out.print("Coldest day was in file ");
        System.out.println(coldestname);
        FileResource fr = new FileResource(coldestname);
        coldest = coldestHourInFile(fr.getCSVParser());
        System.out.print("The coldest temperature on that way was ");
        System.out.println(coldest);
        System.out.println("All the temperatures on the coldest day were");
        for (CSVRecord record:fr.getCSVParser()) {
            System.out.print(record.get("DateUTC"));
            System.out.print(" ");
            System.out.println(record.get("TemperatureF"));
        }
    }
}



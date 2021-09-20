/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class BabyBirths {
                    
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }
    
     public CSVParser getFileParser(int year) {
        // If in testing, use below
        //FileResource fr = new FileResource(String.format("data/us_babynames_test/yob%sshort.csv", year));
        //return fr.getCSVParser(false);
        
        // If in production, use below 
        FileResource fr = new FileResource(String.format("data/yob%s.csv", year));
        return fr.getCSVParser(false);
    }
    
    public int[] totalBirthsStats (CSVParser parser ) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : parser) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        int[] totalBirthsStats; 
        totalBirthsStats = new int[3];
        totalBirthsStats[0] = totalBirths; 
        totalBirthsStats[1] = totalBoys; 
        totalBirthsStats[2] = totalGirls; 
        
        return totalBirthsStats;
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob1900.csv");
        totalBirths(fr);
        FileResource f = new FileResource("data/yob1905.csv");
        totalBirths(f);
    }
    // The function determines if a given is in the file.
    
    
    int lookingForRank (CSVParser parser, String gender, String name){
        // If name is in the file 
      
        int rank = 1;
        for (CSVRecord rec : parser) {
            // Increment rank if gender matches param
            if (rec.get(1).equals(gender)) {
                // Return rank if name matches param
                if (rec.get(0).equals(name)) {
                    return rank;
                }
                rank++;
            }
        }
        return -1;
    
    }
    
     public int getRank (int year, String name, String gender){
        int rank = 0 ;
        CSVParser parser = getFileParser(year);
        rank = lookingForRank (parser, gender, name);
        return rank;
    }
    
    public void testGetRank (){
        int rank =0;
        // Gets the rank of Mason as a male 
        rank = getRank (1971, "Frank", "M"); 
        System.out.println(rank);
        // Gets the rank of Mason as a female
        rank = getRank (1960, "Emily", "F"); 
        System.out.println(rank);
    }
    
    public String getName (int year , int rank , String gender){  
    int [] totals; 
    String name=""; 
    int currRank=0;
    totals = new int [3];
    int totalBoys = totals [1]; 
    int totalGirls = totals [2];
    CSVParser parser = getFileParser(year); 
    for (CSVRecord rec : parser){
        if (rec.get(1).equals(gender)){
            currRank++;
            if (gender == "M"){
                if (totalBoys < rank){
                    name = "NO NAME";
                    }
                if (currRank == rank){
                    name = rec.get(0);
                    break;
                    }
            }
            if (gender == "F"){
                if (totalGirls < rank){
                    name = "NO NAME";
                    }
                if (currRank == rank){
                    name = rec.get(0);
                    break;
                    }
            }
    }
    
}
return name;
}
public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        // Get number of births for given name and gender
        int numOfBirths = 0;
        for (CSVRecord rec : getFileParser(year)) {
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                numOfBirths = Integer.parseInt(rec.get(2));
            }
        }
        
        // Add up number of births greater than that for given name and gender
        int totalBirths = 0;
        for (CSVRecord rec : getFileParser(year)) {
            String currentGender = rec.get(1);
            // If name is not given name AND current gender matches param 
            // AND current num of births is higher than for given name, 
            if (!rec.get(0).equals(name) && currentGender.equals(gender) && 
                Integer.parseInt(rec.get(2)) >= numOfBirths) {
                // Add number of births to total
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        return totalBirths;
    }
    
    public double getAverageRank(String name, String gender) {
        // Allow user to select a range of files
        DirectoryResource dir = new DirectoryResource();
        double totalRank = 0.0;
        int count = 0;
        for (File f : dir.selectedFiles()) {
            // Extract current year from file name
            int currentYear = Integer.parseInt(f.getName().substring(3,7));
            // Determine rank of name in current year
            int currentRank = getRank(currentYear, name, gender);
            // Add rank to total and increment count
            totalRank += currentRank;
            count++;
        }
        // Return calculated average rank
        if (totalRank == 0) {
            return -1;
        }
        double average = totalRank/count;
        return average;
    }
    
    public void testGetAverageRank() {
        String name = "Robert";
        String gender = "M";
        double avg = getAverageRank(name, gender);
        System.out.println("Average rank of " + name + ", " + gender + ": " + avg);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        // Allow user to select a range of files
        DirectoryResource dir = new DirectoryResource();
        int year = 0;
        int rank = 0;
        // For every file the user selected
        for (File f : dir.selectedFiles()) {
            // Extract current year from file name
            int currentYear = Integer.parseInt(f.getName().substring(3,7));
            // Determine rank of name in current year
            int currentRank = getRank(currentYear, name, gender);
            System.out.println("Rank in year " + currentYear + ": " + currentRank);
            // If current rank isn't invalid
            if (currentRank != -1) {
                // If on first file or if current rank is higher than saved rank
                if (rank == 0 || currentRank < rank) {
                    // Update tracker variables
                    rank = currentRank;
                    year = currentYear;
                } 
            }
        }
        
        if (year == 0) {
            return -1; 
        }
        return year;
    } 
    
    
   
    
   public  void whatIsNameInYear(String name, int year, int newYear, String gender){
        // Gets the rank for the frist year
        int yearRank = getRank (year, name, gender); 
        // Finds the corresponding name in the new year
        String nameInNewYear = getName(newYear, yearRank, gender);
        
        System.out.println(name + " born " + " in " + year + " would be " + nameInNewYear + " if she was born in " + newYear);
    }
      
    public void testWhatIsNameInYear(){
        whatIsNameInYear( "Isabella", 2012, 2014, "F");
    }  
    
    public void testGetHighestRank(){
        int highestRankYear = yearOfHighestRank ("Mason" , "M");
        
        
        System.out.println (" The year where Mason was the most popular Masculin Birthname is : " + highestRankYear);
    }
    

    public void testGetTotalBirthsRankedHigher (){
        int total  = getTotalBirthsRankedHigher (2012, "Ethan", "M");
        
        System.out.println (" There are " + total +  " Better ranked than  Ethan for the year 2012 ");
 
    }
            
}

    //Separate words from String which has gigits
        // public String drawDigitsFromString(String strValue){
            // String str = strValue.trim();
            // String digits="";
            // for (int i = 0; i < str.length(); i++) {
                // char chrs = str.charAt(i);              
                // if (Character.isDigit(chrs))
                    // digits = digits+chrs;
            // }
            // return digits;
        // }
    
package PerimeterAssignmentRunner;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int num_Points = 0;
        for (Point Pt : s.getPoints()){
            num_Points +=1 ; 
        }
       
        return num_Points;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double av_Length = (getPerimeter(s)/getNumPoints(s));
        return av_Length;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double side = 0; 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
             if(currDist > side){
                 side = currDist;
                }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer 
        return side;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largest_X = 0 ; 
         for (Point currPt : s.getPoints()) {
            double currX= currPt.getX(); 
            if (currX > largest_X){
                largest_X = currX;
            }
        }
        return largest_X;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largest_Perim = 0 ;
        double Current_Perim =0;
        DirectoryResource perim_Dir_Ressource = new DirectoryResource ();
        for (File f: perim_Dir_Ressource.selectedFiles()){
            FileResource File = new FileResource (f);
            Shape s = new Shape(File);
             Current_Perim = getPerimeter(s);
            if (largest_Perim < Current_Perim){
               largest_Perim = Current_Perim;
        }
    }
        return largest_Perim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource Dir = new DirectoryResource();
        
        double largest_Perim = getLargestPerimeterMultipleFiles();
        String name_File_With_Largest_Perimeter = "";
        for (File CurrFile : Dir.selectedFiles()) {
            FileResource CurrFileResource = new FileResource(CurrFile);
            Shape s = new Shape(CurrFileResource);
            if (getPerimeter(s) == largest_Perim){
                name_File_With_Largest_Perimeter = CurrFile.getName();
        }
        }
        
        return name_File_With_Largest_Perimeter;
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int total_Points = getNumPoints(s);
        double av_length = getAverageLength(s);
        double largest_Side = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("number of point = " + total_Points);
        System.out.println("perimeter = " + length);
        System.out.println("average length = " + av_length);
        System.out.println("largest side = " + largest_Side);
        System.out.println("largestX = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largest_Perim = getLargestPerimeterMultipleFiles();
        System.out.println("largest Perimeter = " + largest_Perim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String file_With_Largest_Perim = getFileWithLargestPerimeter();
        System.out.println(file_With_Largest_Perim);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}

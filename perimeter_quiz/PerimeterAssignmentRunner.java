import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    //Program to find perimeter of a 2D shape of n sides
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
    // Program to get the number of points in the shape
    public int getNumPoints (Shape s) {
        // Put code here
        int cnt = 0;
        for(Point p : s.getPoints()){
            cnt = cnt+1;
        }
        return cnt;
    }
    //Program to get the average length of sides of a shape
    public double getAverageLength(Shape s) {
        // Put code here
        double avg = getPerimeter(s)/getNumPoints(s);
        return avg;
    }
    // Program to get the length of the largest side
    public double getLargestSide(Shape s) {
        // Put code here
        double largest = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point p : s.getPoints()){
            largest = Math.max(largest, prevPt.distance(p));
            prevPt = p;
        }
        
        return largest;
    }
    // Program to get the largest x-coordinate
    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        for(Point p : s.getPoints()){
            largestX = Math.max(largestX, p.getX());
        }
        return largestX;
    }
    // Program to get the largest perimeter out of multiple shapes
    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largest = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            largest = Math.max(largest, getPerimeter(s));
        }
        return largest;
    }
    // Program to get the file containing the shape largest perimeter
    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largest = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > largest){
                largest = getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numOfPoints = getNumPoints(s);
        System.out.println("Number of Points = " + numOfPoints);
        double avgLength = getAverageLength(s);
        System.out.println("Average Length = " + avgLength);
        double largestSide = getLargestSide(s);
        System.out.println("Largest Side = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String filename = getFileWithLargestPerimeter();
        System.out.println("File name with Largest Perimeter = " + filename);
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

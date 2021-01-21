
/**
 * Write a description of babyNames here.
 * 
 * @author (Anuva) 
 * @version (18-01-2021)
 */

import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class babyNames {
    // Method to print name and other details by referencing column number instead of column name
    
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 200) System.out.println("Name: " + rec.get(0) + " Gender: " + rec.get(1) + " Number of babies born: " + rec.get(2));
        }
    }
    
    // Method to find total number of babies born (males and females), number of girls names, the number of boys names and the total names in the file
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlNames = 0;
        int boyNames = 0;
        int names = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            totalBirths += Integer.parseInt(rec.get(2));
            names++;
            if(rec.get(1).equals("M")){
                totalBoys += Integer.parseInt(rec.get(2));
                boyNames++;
            }
            else{ 
                totalGirls += Integer.parseInt(rec.get(2));
                girlNames++;
            }
        }
        System.out.println("Total births= " + totalBirths);
        System.out.println("Total births (males) = " + totalBoys);
        System.out.println("Total births (females) = " + totalGirls);
        System.out.println("Total number of names (females) = " + girlNames);
        System.out.println("Total number of names (males) = " + boyNames);
        System.out.println("Total number of names = " + names);
    }
    
    // Method to test totalBirths(FileResource fr)
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    // Method to find rank of name in a given year for a given gender
    
    public int getRank(int year, String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        String requiredFileName = "";
        for(File f : dr.selectedFiles()){
            String currFileName = f.getAbsolutePath();
            String reqdYear = Integer.toString(year);
            if(currFileName.contains(reqdYear)){
                requiredFileName = currFileName;
                break;
            }
        }
        FileResource fr = new FileResource(requiredFileName);
        int rank = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                if(rec.get(0).equals(name)) return rank+1;
                rank+=1;
            } else continue;
        }
        return -1;
    }
    
    // Method to test getRank(int year, String name, String gender)
    
    public void testGetRank(){
        int ans = getRank(2012, "Mason", "F");
        System.out.println("Rank is " + ans);
    }
    
    // Mathod to get name from give year with given name and gender
    
    public String getName(int year, int rank, String gender){
        DirectoryResource dr = new DirectoryResource();
        String requiredFileName = "";
        for(File f : dr.selectedFiles()){
            String currFileName = f.getAbsolutePath();
            String reqdYear = Integer.toString(year);
            if(currFileName.contains(reqdYear)){
                requiredFileName = currFileName;
                break;
            }
        }
        FileResource fr = new FileResource(requiredFileName);
        if(gender.equals("F")){
            for(CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(1).equals("M")) return "NO NAME";
                rank -= 1;
                if(rank==0) return rec.get(0);
            }
        } else {
            for(CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(1).equals("F")) continue;
                rank -= 1;
                if(rank==0) return rec.get(0);
            }
            return "NO NAME";
        }
        return "NO NAME";
    }
    
    // Mathod to test getName(int year, int rank, String gender)
    public void testGetName(){
        String ans = getName(2012, 10, "M");
        System.out.println("Name with given rank and gender: " + ans);
    }
    
    // Method for finding a name's counterpart in another year based on popularity
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        if(gender.equals("F")){
            System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
        }else {
            System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear);
        }
    }
    
    // Mathod to test whatIsNameInYear(String name, int year, int newYear, String gender)
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
}

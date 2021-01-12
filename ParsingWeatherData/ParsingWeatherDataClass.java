
/**
 * Write a description of ParsingWeatherDataClass here.
 * Relevant CSV files are available in the same folder.
 * 
 * @author (Anuva) 
 * @version (12-01-2021)
 */

import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingWeatherDataClass {
    
    // Method to get record with greater temperature
    
    public CSVRecord getLargestOfTwo(CSVRecord currRow, CSVRecord largestSoFar){
        if(largestSoFar == null){
            largestSoFar = currRow;
        }else {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currTemp > largestTemp) largestSoFar = currRow;
        }
        return largestSoFar;
    }
    
    // Method to get record with smaller temperature
    
    public CSVRecord getSmallerOfTwo(CSVRecord currRow, CSVRecord smallestSoFar){
        if(smallestSoFar == null){
            smallestSoFar = currRow;
        }else {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if(currTemp < smallestTemp && currTemp!=-9999) smallestSoFar = currRow;
        }
        return smallestSoFar;
    }
    
    // Method to find highest temperature in a given CSV file
    
    public CSVRecord HottestHourInDay(CSVParser parser){
        CSVRecord largestSoFar = null;
        
        for(CSVRecord currRow : parser){
            largestSoFar = getLargestOfTwo(currRow, largestSoFar);
        }
        
        return largestSoFar;
    }
    
    // Method to find lowest temperature in a given CSV file
    
    public CSVRecord ColdestHourInDay(CSVParser parser){
        CSVRecord smallestSoFar = null;
        
        for(CSVRecord currRow : parser){
            smallestSoFar = getSmallerOfTwo(currRow, smallestSoFar);
        }
        
        return smallestSoFar;
    }
    
    // Method to test above functions
    
    public void testInOneDay(){
        FileResource fr = new FileResource();
        CSVRecord largest = HottestHourInDay(fr.getCSVParser());
        CSVRecord smallest = ColdestHourInDay(fr.getCSVParser());
        
        System.out.println("Hottest temperature is " + largest.get("TemperatureF") + " at time " + largest.get("TimeEST"));
        
        
        System.out.println("Coldest temperature is " + smallest.get("TemperatureF") + " at time " + smallest.get("TimeEST"));
    }
    
    // Method to find highest temperature across multiple CSV files
    
    public CSVRecord HottestHourInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currRow = HottestHourInDay(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    // Method to test above function
    
    public void testHottest(){
        CSVRecord largest = HottestHourInManyDays();
        System.out.println("Hottest temperature is " + largest.get("TemperatureF") + " at time " + largest.get("TimeEST") + " on the day " + largest.get("DateUTC"));
    }
}

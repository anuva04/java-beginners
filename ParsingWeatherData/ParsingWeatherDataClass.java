
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
        
        System.out.println("Hottest temperature is " + largest.get("TemperatureF") + " at date " + largest.get("DateUTC"));
        
        
        System.out.println("Coldest temperature is " + smallest.get("TemperatureF") + " at date " + smallest.get("DateUTC"));
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
    
    // Method to get filename with coldest temperature
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        String coldestFile = null;
        CSVRecord smallestSoFar = null;
        
        for(File f : dr.selectedFiles()){
             FileResource fr = new FileResource(f);
             CSVRecord currRow = ColdestHourInDay(fr.getCSVParser());
             if(smallestSoFar == null) smallestSoFar = currRow;
             else{
                 double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                 double coldestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                 if(currTemp < coldestTemp){
                     smallestSoFar = currRow;
                     coldestFile = f.getAbsolutePath();
                 }
             }
        }
        return coldestFile;
    }
    
    // Method to test above function
    
    public void test(){
        CSVRecord largest = HottestHourInManyDays();
        System.out.println("Hottest temperature is " + largest.get("TemperatureF") + " on the day " + largest.get("DateUTC"));
        String coldestFile = fileWithColdestTemperature();
        System.out.println("File with coldest temperature is: " + coldestFile);
        FileResource fr = new FileResource(coldestFile);
        CSVRecord coldestHourInAboveFile = ColdestHourInDay(fr.getCSVParser());
        System.out.println("Coldest temperature is " + coldestHourInAboveFile.get("TemperatureF") + " on date " + coldestHourInAboveFile.get("DateUTC"));
    }
    
    // Method to return CSV record with lowest humidity
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidityRecord = null;
        
        
        for(CSVRecord currRow : parser){
            if(lowestHumidityRecord == null) lowestHumidityRecord = currRow;
            else{
                if(currRow.get("Humidity").equals("N/A")) continue;
                
                int currHumidity = Integer.parseInt(currRow.get("Humidity"));
                int lowestHumidity = Integer.parseInt(lowestHumidityRecord.get("Humidity"));
                
                if(currHumidity < lowestHumidity) lowestHumidityRecord = currRow;
            }
        }
        
        return lowestHumidityRecord;
    }
    
    // Method to test above function
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    // Method to return CSV record with lowest humidity among many files
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityRecord = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser csv = fr.getCSVParser();
            CSVRecord currLowestHumidityRec = lowestHumidityInFile(csv);
            if(lowestHumidityRecord == null) lowestHumidityRecord =  currLowestHumidityRec;
            else {
                int currLowest = Integer.parseInt(currLowestHumidityRec.get("Humidity"));
                int Lowest = Integer.parseInt(lowestHumidityRecord.get("Humidity"));
                
                if(currLowest < Lowest) lowestHumidityRecord = currLowestHumidityRec;
            }
        }
        return lowestHumidityRecord;
    }
    
    // Method to test above function
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    // Method to find average temperature in a file
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0, cnt = 0;
        for(CSVRecord currRow : parser){
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            if(currTemp == -9999) continue;
            sum += currTemp;
            cnt += 1;
        }
        return (sum/cnt);
    }
    
    // Method to test above function
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature is: " + avgTemp);
    }
    
    // Method to find average temperature in a file
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0, cnt = 0;
        for(CSVRecord currRow : parser){
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            if(currTemp == -9999) continue;
            int currHumidity = Integer.parseInt(currRow.get("Humidity"));
            if(currHumidity >= value){
                sum += currTemp;
                cnt += 1;
            }
        }
        return (sum/cnt);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average temperature with given humidity is: " + avgTemp);
    }
}

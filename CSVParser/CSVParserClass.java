
/**
 * Write a description of CSVParserClass here.
 * 
 * @author (Anuva) 
 * @version (09-01-2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVParserClass {
    // Method to get a country's info from a CSV file
    
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String currCountry = record.get("Country");
            if(currCountry.equals(country)){
                String currExports = record.get("Exports");
                String currValue = record.get("Value (dollars)");
                String ans = currCountry + ": " + currExports + ": " + currValue;
                return ans;
            }
        }
        return "NOT FOUND";
    }
    
    // Method to get countries which export mentioned products
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        for(CSVRecord record : parser){
            String currExports = record.get("Exports");
            if(currExports.contains(exportitem1) && currExports.contains(exportitem2)){
                System.out.println(record.get("Country") + " exports both " + exportitem1 + " and " + exportitem2);
            }
        }
    }
    
    // Method to obtain number of countries that export a certain product
    
    public int numberOfExporters(CSVParser parser, String exportitem){
        int cnt = 0;
        for(CSVRecord record : parser){
            String currExports = record.get("Exports");
            if(currExports.contains(exportitem)){
                cnt++;
            }
        }
        return cnt;
    }
    
    // Method to obtain all exporters whose export value exceeds a certain amount (checking only length of string of value tho!)
    
    public void bigExporters(CSVParser parser, String amount){
        System.out.println("Countries with value greater than " + amount + " are: "); 
        for(CSVRecord record : parser){
            String currValue = record.get("Value (dollars)");
            if(currValue.length() > amount.length()){
                System.out.println(record.get("Country") + " " + currValue);
            }
        }
    }
    
    // Method to test above functions
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println("Number of Exporters: " + numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}

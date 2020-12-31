
/**
 * Write a description of Part1 here.
 * 
 * @author (Anuva) 
 * @version (31-12-2020)
 */

import java.util.*;
import edu.duke.*;

public class Part1 {
    
    // Program to find the index of Stop Codon in a given DNA
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff%3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    
    // Method to test above function
    
    public void testFindStopCodon(){
        Scanner sc =  new Scanner(System.in);
        System.out.println("Enter DNA: ");
        String dna = sc.nextLine();
        int stopIndex = findStopCodon(dna, 0, "TAA");
        System.out.println("Stop Codon Index is: " + stopIndex);
    }
    
    // Program to find Gene from a given DNA starting from a given index in the string
    
    public String findGene(String dna, int startIndex){
        int currIndex = dna.indexOf("ATG", startIndex);
        if(currIndex == -1) return "";
        
        int stopIndexTAA = findStopCodon(dna, currIndex, "TAA");
        int stopIndexTAG = findStopCodon(dna, currIndex, "TAG");
        int stopIndexTGA = findStopCodon(dna, currIndex, "TGA");
        
        int stopIndex = Math.min(stopIndexTAA, Math.min(stopIndexTAG, stopIndexTGA));
        
        if(stopIndex == dna.length()) return "";
        return dna.substring(currIndex, stopIndex+3);
    }
    
    // Method to test above function
    
    public void testFindGene(){
        Scanner sc =  new Scanner(System.in);
        System.out.println("Enter DNA: ");
        String dna = sc.nextLine();
        String gene = findGene(dna, 0);
        System.out.println("Gene is: " + gene);
    }
    
    // Program to print all genes in a given DNA
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()) break;
            else{
                System.out.println(currGene);
                startIndex = dna.indexOf(currGene) + currGene.length();
            }
        }
    }
    
    // Method to test above function
    
    public void testPrintAllGenes(){
        Scanner sc =  new Scanner(System.in);
        System.out.println("Enter DNA: ");
        String dna = sc.nextLine();
        printAllGenes(dna);
    }
}

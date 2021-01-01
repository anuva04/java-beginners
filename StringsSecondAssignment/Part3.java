
/**
 * Write a description of Part3 here.
 * 
 * @author (Anuva) 
 * @version (01-01-2021)
 */

import java.util.*;
import edu.duke.*;

public class Part3 {
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
    
    // Program to count all genes in a given DNA
    
    public int countGenes(String dna){
        int startIndex = 0;
        int cnt = 0;
        while(true){
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()) break;
            else{
                cnt += 1;
                startIndex = dna.indexOf(currGene) + currGene.length();
            }
        }
        return cnt;
    }
    
    // Method to test above function
    
    public void testcountGenes(){
        Scanner sc =  new Scanner(System.in);
        System.out.println("Enter DNA: ");
        String dna = sc.nextLine();
        int ans = countGenes(dna);
        System.out.println("Number of genes in given DNA: " + ans);
    }
}

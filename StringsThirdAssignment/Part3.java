
/**
 * Write a description of Part3 here.
 * 
 * @author (Anuva) 
 * @version (05-01-2021)
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
        int currIndex = dna.indexOf("atg", startIndex);
        if(currIndex == -1) return "";
        
        int stopIndexTAA = findStopCodon(dna, currIndex, "taa");
        int stopIndexTAG = findStopCodon(dna, currIndex, "tag");
        int stopIndexTGA = findStopCodon(dna, currIndex, "tga");
        
        int stopIndex = Math.min(stopIndexTAA, Math.min(stopIndexTAG, stopIndexTGA));
        
        if(stopIndex == dna.length()) return "";
        return dna.substring(currIndex, stopIndex+3);
    }
    
    // Program to find the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA
    
    public float cgRatio(String dna){
        int cCount = 0;
        int gCount = 0;
        int dnaLength = dna.length();
        for(int i=0; i<dnaLength; i++){
            if(dna.charAt(i)=='c') cCount++;
            if(dna.charAt(i)=='g') gCount++;
        }
        float ans = (float) cCount/gCount;
        return ans;
    }
    
    // Program to store all genes in a storage resource
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true){
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()) break;
            else{
                geneList.add(currGene);
                startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            }
        }
        return geneList;
    }
    
    // Program to find the number of times the codon CTG appears in dna
    
    public int countCTG(String dna){
        int cnt = 0;
        int startIndex = 0;
        while(true){
            int currIndex = dna.indexOf("ctg", startIndex);
            if(currIndex == -1){
                break;
            }else{
                cnt++;
                startIndex = currIndex + 3;
            }
        }
        return cnt;
    }
    
    // Program to find all relevant information about the genes stored in a storage class
    public void processGenes(StorageResource sr){
        System.out.println("Number of genes: " + sr.size());
        int cnt = 0;
        System.out.println("Genes longer than 60 characters are: ");
        for(String gene : sr.data()){
            if(gene.length()>60){
                 System.out.println(gene);
                 cnt++;
            }
        }
        System.out.println("Number of Genes longer than 60 characters are: " + cnt);
        int cntcg = 0;
        System.out.println("Genes having CG ratio greater than 0.35 are: ");
        for(String gene : sr.data()){
            if(cgRatio(gene)>0.35){
                 System.out.println(gene);
                 cntcg++;
            }
        }
        System.out.println("Number of Genes having CG ratio greater than 0.35 are: " + cntcg);
        
        String longestGene;
        int len=0;
        for(String gene : sr.data()){
            if(gene.length()>len){
                len = gene.length();
                longestGene = gene;
            }
        }
        System.out.println("Length of longest gene: " + len);
    }
    
    
    // Method to test above function
    
    public void testProcessGenes(){
        URLResource fr = new URLResource("https://www.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String dna = fr.asString();
        String dnaLower = dna.toLowerCase();
        StorageResource geneList = getAllGenes(dnaLower);
        processGenes(geneList);
        System.out.println("Number of times CTG appears: " + countCTG(dnaLower));
    }
}

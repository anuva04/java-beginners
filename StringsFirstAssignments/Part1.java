
/**
 * Write a description of Part1 here.
 * 
 * @author (Anuva) 
 * @version (27-12-2020)
 */
// Program to find out all DNA sequences which start with codon "ATG" and end with "TAA" and is a multiple of 3
import edu.duke.*;
import java.io.File;

public class Part1 {
    public String findSimpleGene(String dna){
        int start_index = dna.indexOf("ATG");
        if(start_index == -1) return "";
        int end_index = dna.indexOf("TAA", start_index+3);
        if(end_index == -1) return "";
        if((end_index - start_index)%3 == 0) return dna.substring(start_index, end_index+3);
        return "";
    }
    
    public void testSimpleGene(){
        String seq1 = "ATCGAACTGATAA";
        String seq2 = "ATGCCGTACGATGTAACGATAGA";
        String seq3 = "ATGCCGTAGATG";
        String seq4 = "ATGCTAA";
        String seq5 = "AATHGCTTCATGCAATAATGACGTA";
        
        String dna1 = findSimpleGene(seq1);
        System.out.println(dna1);
        String dna2 = findSimpleGene(seq2);
        System.out.println(dna2);
        String dna3 = findSimpleGene(seq3);
        System.out.println(dna3);
        String dna4 = findSimpleGene(seq4);
        System.out.println(dna4);
        String dna5 = findSimpleGene(seq5);
        System.out.println(dna5);
    }
    public static void main (String[] args) {
        Part1 pr = new Part1();
        pr.testSimpleGene();
    }
}

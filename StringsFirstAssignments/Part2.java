
/**
 * Write a description of Part1 here.
 * 
 * @author (Anuva) 
 * @version (27-12-2020)
 */
// Program to find out all DNA sequences which start with user-defined start-codon and stop-codon and is a multiple of 3
import edu.duke.*;
import java.io.File;

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
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
        
        String dna1 = findSimpleGene(seq1, "ATG", "TAA");
        System.out.println(dna1);
        String dna2 = findSimpleGene(seq2, "ATG", "TAA");
        System.out.println(dna2);
        String dna3 = findSimpleGene(seq3, "ATG", "TAA");
        System.out.println(dna3);
        String dna4 = findSimpleGene(seq4, "ATG", "TAA");
        System.out.println(dna4);
        String dna5 = findSimpleGene(seq5, "ATG", "TAA");
        System.out.println(dna5);
    }
    public static void main (String[] args) {
        Part2 pr = new Part2();
        pr.testSimpleGene();
    }
}

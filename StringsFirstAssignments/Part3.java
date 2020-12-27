
/**
 * Write a description of Part3 here.
 * 
 * @author (Anuva) 
 * @version (27-12-2020)
 */


public class Part3 {
    // Program to find if a sting has 2 distinct occurences in another string
    public Boolean twoOccurences(String stringa, String stringb){
        int first_time = stringb.indexOf(stringa);
        if(first_time == -1) return false;
        int second_time = stringb.indexOf(stringa, first_time+1);
        if(second_time == -1) return false;
        return true;
    }
    public String lastPart(String stringa, String stringb){
        // Program to print the remaining substring after the occurence of stringa in stringb
        int first_occur = stringb.indexOf(stringa);
        if(first_occur == -1) return stringb;
        String rem = stringb.substring(first_occur + stringa.length());
        return rem;
    }
    public void testing(){
        String stringa1 = "by", stringb1 = "A story by Abby Long", stringa2 = "a", stringb2 = "banana", stringa3 = "atg", stringb3 = "ctgtatgta";
        Boolean str1 = twoOccurences(stringa1, stringb1);
        Boolean str2 = twoOccurences(stringa2, stringb2);
        Boolean str3 = twoOccurences(stringa3, stringb3);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(lastPart(stringa1, stringb1));
        System.out.println(lastPart(stringa2, stringb2));
        System.out.println(lastPart(stringa3, stringb3));
    }
    public static void main (String[] args) {
        Part3 pr = new Part3();
        pr.testing();
    }
}

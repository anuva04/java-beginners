
/**
 * Write a description of Part2 here.
 * 
 * @author (Anuva) 
 * @version (01-01-2021)
 */

// Program to find how many times stringa appears in stringb, where each occurrence of stringa must not overlap with another occurrence of it
import java.util.*;
import edu.duke.*;

public class Part2 {
    public int howMany(String stringa, String stringb){
        int startIndex = 0;
        int cnt = 0;
        while(true){
            int currIndex = stringb.indexOf(stringa, startIndex);
            if(currIndex == -1) break;
            else{
                cnt += 1;
                startIndex += currIndex + stringa.length();
            }
        }
        return cnt;
    }
    
    public void testHowMany(){
        Scanner sc =  new Scanner(System.in);
        System.out.println("Enter String A: ");
        String stringa = sc.nextLine();
        System.out.println("Enter String B: ");
        String stringb = sc.nextLine();
        
        int ans = howMany(stringa, stringb);
        System.out.println("String A appears in String B " + ans + " number of times.");
    }
}
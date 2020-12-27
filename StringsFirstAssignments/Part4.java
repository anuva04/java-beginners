
/**
 * Write a description of Part4 here.
 * 
 * @author (Anuva) 
 * @version (27-02-2020)
 */

//Program to select each link given in the website and output those which link to youtube.com
import edu.duke.*; 

public class Part4 { 

    public void links() 

    { 
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html"); 
        for(String s : ur.lines()) 
        { 
           int ind = s.indexOf("\"h"); 
           if(ind!= -1) 
           { 
               String temp = s.substring(ind,s.indexOf("\">")); 
               String temp1 = temp.toLowerCase(); 
               if(temp1.indexOf("youtube") != -1) 
               { 
                   System.out.println(temp); 
               } 
            } 
        } 
    } 
}


/**
 * Write a description of debug here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

// Program to find all occurences of "abc_" where "_" can be any character and then print "bc_"

public class debug {
    public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           System.out.println("index " + index);
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc",index+3);
           System.out.println("index after updating " + index);
       }
   }

   public void test(){
       //findAbc("abcd");
       //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       findAbc("abcabcabcabca");
   }
}

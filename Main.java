import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.Number;
class Main {
  public static void main(String[] args) {
    // put your code here
     Scanner in = new Scanner(new InputStreamReader(System.in));
     Integer n = new Integer(in.nextInt());
     Integer nhalf = new Integer(n/2);
     HashMap<Long,Integer> myMap = new HashMap<Long,Integer>(n);
      
     Long cur;
     Integer count = new Integer(0);
     for( int i = 0; i < n; i++ ) {
         cur = new Long(in.nextLong());
         if(myMap.containsKey(cur) == false)
             myMap.put( cur, count );
         
         count = myMap.get(cur);

         myMap.put( cur, new Integer(count.intValue() + 1) );
         if( new Integer(count.intValue() + 1) > nhalf) {
             System.out.println(1);
             return;
         }

         count = new Integer(0);
     }
     System.out.println(0);
  }
}
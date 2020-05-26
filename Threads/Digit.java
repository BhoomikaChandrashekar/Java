package threads;

import java.util.*;

public class Digit implements Runnable{
    HashMap<Character,String>hm=new HashMap<>();
    @Override
    public void run() {
        Scanner sc=new Scanner(System.in);
        hm.put('1',"one");
        hm.put('2',"two");
        hm.put('3',"three");
        hm.put('4',"four");
        hm.put('5',"five");
        hm.put('6',"six");
        hm.put('7',"seven");
        hm.put('8',"eight");
        hm.put('9',"nine");
        System.out.println("Enter a number greater than 4 digits");
        String s=sc.next();
        for(char a:s.toCharArray())
        {
            System.out.println(a+" "+hm.get(a));
        }
    }
    
}

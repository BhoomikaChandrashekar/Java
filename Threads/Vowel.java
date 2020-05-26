package threads;
import java.util.*;
public class Vowel extends Thread {
    Scanner sc=new Scanner(System.in);
    public void run()
    {
        System.out.println("Enter the string");
        String s=sc.next();
        String vowels="aeiou";
        int x;
        for(char v:vowels.toCharArray())
        {
            x=0;
            for(char a:s.toCharArray())
            {
                if(a==v)
                    x++;
            }
            if(x>0)
            System.out.println(v+" : "+x);
        }
    }
}

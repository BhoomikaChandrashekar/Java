package library;

import java.util.Scanner;

public class MainClass {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("-----------------Welcome to the Library!!-----------------");
        System.out.println("Press \n 1.Customer \n 2.Admin");
        int op=sc.nextInt();
        if(op==1)
        {
            Customer c=new Customer();  
            c.enterDetails();
        }
        else if(op==2)
        {
            Admin a=new Admin();
            a.enterDetails();
        }
        else
        {
            System.out.println("Invalid Option");
        }
    }
}

import java.util.Scanner;
public class MainClass 
{
	public static void main(String[] args) throws CreditException 
	{
		Scanner sc=new Scanner(System.in);
		String type;
		System.out.println("Enter Student Details : \nEnter Name : ");
		String name=sc.next();
		System.out.println("Enter USN : ");
		String usn=sc.next();
		System.out.println("Enter Address : ");
		String addr=sc.next();
		System.out.println("Enter Age : ");
		int age=sc.nextInt();
		System.out.println("Select type of course");
		System.out.println("(a) for Regular\n(b) for Change of branch\n(c) for Diploma");
		String type1=sc.next();
		if(type1.equals("a"))
			type="Regular";
		else if(type1.equals("b"))
			type="Change_Of_Branch";
		else
			type="Diploma";
		System.out.println("Enter type of Graduation (UG or PG)");
		String grad=sc.next();
		System.out.println("Enter number of sems : ");
		int sem=sc.nextInt();
		System.out.println("Enter credits  : ");
		int credits=sc.nextInt();
		if(grad.equalsIgnoreCase("UG"))
		{
			UGStudent ug=new UGStudent(name, usn, addr, type, age, sem, credits);
			ug.display();
			ug.check();
		}
		else
		{
			PGStudent pg=new PGStudent(name, usn, addr, type, age, sem, credits);
			pg.display();
			pg.check();
		}	
	}
}
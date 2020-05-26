import java.util.*;
public class MainClass {

public static void main(String[] args) {
// TODO Auto-generated method stub
	Scanner sc=new Scanner(System.in);
	HashMap<String,String> hm=new HashMap<String,String>();
	hm.put("9876543210", "James");
	hm.put("9768543120","John");
	hm.put("8904171378","Mary");
	hm.put("9036961378","Joseph");
	LinkedList<Missedcall>ar=new LinkedList<Missedcall>();
	int i=0;
	while(true){
		System.out.println("Enter choice\n1.Call\n2.Display and delete on request\n3.Delete Based on the number\n4.Print all the details\n5.exit ");
		int ch=sc.nextInt();
		switch(ch){
			case 1:
				System.out.println("Enter time of call");
				String time=sc.next();
				System.out.println("Enter phone number");
				String number=sc.next();
				String name;
				if(hm.containsKey(number))
				{
					name=hm.get(number);
				}
				else
				{
					name="Private Caller";
				}
				Missedcall m=new Missedcall(name,number,time);
				if(ar.size()==3)
					ar.removeFirst();
				ar.addLast(m);
				break;
				
			case 2:
				 ListIterator<Missedcall> it = ar.listIterator();
				 LinkedList<Missedcall> removeList = new LinkedList<Missedcall>();
	                int j = 0;
	                while (it.hasNext()) {
	                    j++;
	                    System.out.println("Number is ");
	                    Missedcall m1 = it.next();
	                    System.out.println(m1.phno);
	                    System.out.println("Do you want to delete the details related to this number? Indicate by 1 : delete, 2: move next call , 3: display call details \n");
	                       
	                    int cho = sc.nextInt();
	                    if (cho == 1)
	                        removeList.add(m1);
	                    else if (cho == 3) {
	                        System.out.println("User : "+m1.name+"\nPhone number : "+m1.phno+"\nTime : "+m1.time);
	                    }
	                }
	                if (i == 0)
	                    System.out.println("No missed calls");
	                ar.removeAll(removeList);
	                break;
				
			case 3:
				System.out.println("Enter phone number to be deleted from missed call list");
				String num=sc.next();
				Missedcall rem=null;
				for(Missedcall m1 : ar)
				{
					if(m1.phno.equals(num))
					{
						rem=m1;
					}
				}
				ar.remove(rem);
				break;
			case 4:
				for(Missedcall m2 : ar)
				{
					System.out.println(m2);
				}
				break;
			default : System.exit(0);
		}
	}
}

}

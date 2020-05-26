import java.util.*;
class CompareBookPrice implements Comparator <Book>
{
	public int compare(Book b1,Book b2)
	{
		return (int)(b1.price-b2.price);
	}
}

public class Storage
{
	static Scanner sc=new Scanner(System.in);
	public static void main(String args[])
	{
		ArrayList<Book> book=new ArrayList<>();
		HashMap<Integer,Book> hm=new HashMap<>();
		int i=0;
		while(true)
		{
			System.out.println("Enter \n1.Add Books to Collection \n2.Display Book Details \n3.Stop");
			int ch=sc.nextInt();
			if(ch==1)
			{
			System.out.println("Enter Book title");
			String t=sc.next();
			System.out.println("Enter Author");
			String a=sc.next();
			System.out.println("Enter Publisher");
			String p=sc.next();
			System.out.println("Enter Book Price");
			double pr=sc.nextDouble();
			Book b1=new Book(t,a,p,pr);
			book.add(b1);
			hm.put(++i,b1);
			}
			else if(ch==2)
			{
			System.out.println("Book Details");
			for(Book b : book)
			{
			System.out.println(b);
			}
			}
			else
			break;
			}
			Storage st=new Storage();
			st.sortbooks(book);
			st.bookOperations(hm);
		}
		public void sortbooks(ArrayList<Book> book)
		{
			ArrayList<Book> sortedbook;
			sortedbook=(ArrayList<Book>)book.clone();
			Collections.sort(sortedbook,new CompareBookPrice());
			System.out.println("Book Details in Sorted Order : ");
			for(Book b : sortedbook)
			{
				System.out.println(b);
			}
		}	
		public void bookOperations(HashMap<Integer,Book> hm)
		{
			Set<Map.Entry<Integer,Book>> s1=hm.entrySet();
			System.out.println("Enter the author name to obtain respective books");
			String aname=sc.next();
			for(Map.Entry<Integer,Book> setb:s1)
			{
				Book b=setb.getValue();
				if(b.author.equals(aname))
				{
					System.out.println(b);
				}
			}
	
			System.out.println("Specify the lower limit of price to obtain book details");
			double pr=sc.nextDouble();
			ArrayList<Book> bprice=new ArrayList<>();
			for(Map.Entry<Integer,Book> setb:s1)
			{
				Book b=setb.getValue();
				if((b.price)>(pr))
				{
					bprice.add(b);
					System.out.println(b);
				}
			}
	
			System.out.println("Enter title of the book to obtain its details");
			String title1=sc.next();
			for(Map.Entry<Integer,Book> setb:s1)
			{
				Book b=setb.getValue();
				if(b.title.contains(title1))
				{
					System.out.println(b);
				}
			}
	
	
			System.out.println("Enter publisher of the book to obtain its details");
			String publisher1=sc.next();
			for(Map.Entry<Integer,Book> setb:s1)
			{
				Book b=setb.getValue();
				if(b.publisher.contains(publisher1))
				{
					System.out.println(b);
				}
			}
	
			System.out.println("Enter title of the book to modify the publisher details");
			String title2=sc.next();
			for(Map.Entry<Integer,Book> setb:s1)
			{
				Book b=setb.getValue();
				if(b.title.contains(title2))
				{
					System.out.println("Current publisher details : "+b.publisher);
					System.out.println("Enter new publisher");
					String newp=sc.next();
					int key=setb.getKey();
					b.publisher=newp;
					hm.put(key,b);
				}
			}
		}
}

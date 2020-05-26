class Book
{
	String title,author,publisher;
	double price;
	Book(String t,String a,String p,double pr)
	{
		title=t;
		author=a;
		publisher=p;
		price=pr;
	}
	public String toString()
	{
		return ("Title : "+title+"\tAuthor : "+author+"\tPublisher : "+publisher+"\tPrice : "+price);
	}
}









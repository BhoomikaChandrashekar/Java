class PGStudent extends Student
{
	int sem,credits;
	PGStudent(String name,String usn,String addr,String type,int age,int sem,int credits)
	{
		super(name,usn,addr,type,age);
		this.sem=sem;
		this.credits=credits;
	}
	void check()
	{
		try
		{
			throw new CreditException(credits,type);
		}
		catch(CreditException e)
		{
			System.out.println(e);
		}
	}
}

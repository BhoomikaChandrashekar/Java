class UGStudent extends Student
{
	int sem,credits;
	UGStudent(String name,String usn,String addr,String type,int age,int sem,int credits)
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
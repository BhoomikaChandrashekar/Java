public class CreditException extends Exception 
{
	int credits;
	String type;
	CreditException(int credits,String type)
	{
		this.credits=credits;
		this.type=type;
	}
	public String toString()
	{
	if(type.equals("Regular") || type.equals("Change_Of_Branch"))
		if(credits<200)
			return "Student cannot graduate as subjects are not cleared";
		else
			return "Student Graduated successfully";
	else if(type.equals("Diploma"))
		if(credits<150)
			return "Student cannot graduate as as subjects are not cleared";
		else
			return "Student Graduated successfully";
	else
		return "Invalid Credentials";
	}
}

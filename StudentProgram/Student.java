public class Student {
	String name,usn,addr,type;
	int age;
	Student(String name,String usn,String addr,String type,int age)
	{
		this.name=name;
		this.usn=usn;
		this.addr=addr;
		this.type=type;
		this.age=age;
	}
	void display()
	{
		System.out.println("NAME : "+(name)+"\nUSN : "+(usn.toUpperCase())+"\nADDRESS : "+addr+"\nTYPE : "+type+"\nAGE : "+age);
	}
}


























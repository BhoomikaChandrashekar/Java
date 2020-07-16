package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*class PrintColumnTypes  {

  public static void printColTypes(ResultSetMetaData rsmd)
                            throws SQLException {
    int columns = rsmd.getColumnCount();
    for (int i = 1; i <= columns; i++) {
      int jdbcType = rsmd.getColumnType(i);
      String name = rsmd.getColumnTypeName(i);
      System.out.print("Column " + i + " is JDBC type " + jdbcType);
      System.out.println(", which the DBMS calls " + name);
    }
  }
}
*/

public class Admin 
{
    Scanner sc=new Scanner(System.in);
    Connection con=MakeConnection.connect();
    public void printDetails(ResultSetMetaData rsmd,ResultSet rs)
    {
        try {
            //PrintColumnTypes.printColTypes(rsmd);
            
            
            int numberOfColumns = rsmd.getColumnCount();
            
            for (int i = 1; i <= numberOfColumns; i++) {
                if (i > 1) System.out.print("\t \t \t");
                String columnName = rsmd.getColumnName(i);
                System.out.print(columnName);
            }
            System.out.println("");
            
            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    if (i > 1) System.out.print("\t \t \t");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");
            }
            System.out.println("--------------------------------------");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void enterDetails()
    {
        try {
            int r=0;
            //Connection con=MakeConnection.connect();
            System.out.println("--------------Welcome to the Admin Login of the Library!!---------------");
            System.out.println("Enter the user name");
            String name=sc.next();
            System.out.println("Enter your Email id");
            String email=sc.next();
            System.out.println("Enter phone number");
            long phone=sc.nextLong();
            System.out.println("Enter password");
            String pwd=sc.next();
            String s=("select * from admin where (email=? or phone=?) and password=?");
            PreparedStatement ps=con.prepareStatement(s);
            ps.setString(1, email);
            ps.setLong(2, phone);
            ps.setString(3, pwd);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                r=rs.getRow();
            }
            if(r>0)
            {
                System.out.println("-----------Welcome "+name+"!!------------");
            }
            else
            {
                System.out.println("Invalid Admin Details");
                System.exit(0);
            }
            options();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void options()
    {
        int op=0;
        while(op!=4)
        {
            
            System.out.println("\nEnter Choice");
            System.out.println("1.View Customer Details \n2.View Book Type Details \n3.View Issued details \n4.Finish");
            op=sc.nextInt();
            if(op==1)
            {
                customerDetails();
            }
            else if(op==2)
            {
                bookType();
            }
            else if(op==3)
            {
                issueDetails();
            }
            else if(op!=4)
            {
                System.out.println("Invalid choice, Please try again");
            } 
        }
    }
    
    public void customerDetails()
    {
        try {
            System.out.println("--------------------------------------");
            System.out.println("Customer Details: ");
            String s=("select cid,fname,email from customer");
            PreparedStatement ps=con.prepareStatement(s);
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            printDetails(rsmd,rs);
            
            }catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void bookType()
    {
        try {
            System.out.println("--------------------------------------");
            System.out.println("Book Type Details:");
            String s=("select * from Book_type;");
            PreparedStatement ps=con.prepareStatement(s);
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            printDetails(rsmd,rs);
            int op=0;
            while(op!=5)
            {
                System.out.println();
                System.out.println("Enter choice: \n1.Add Types of Books \n2.Remove Types of Books \n3.Update the type of books \n4.View books under each type \n5.Finish");
                op=sc.nextInt();
                if(op==1)
                {
                    System.out.println("Enter type of book to add");
                    String b=sc.next();
                    String c="insert into Book_type(typename) values(?)";
                    PreparedStatement ps1=con.prepareStatement(c);
                    ps1.setString(1, b);
                    int i=ps1.executeUpdate();
                    if(i>0)
                    {
                        /*String x="select tid from Book_type where typename=\"'?'\"";
                        PreparedStatement ps5=con.prepareStatement(x);
                        ps5.setString(1, b);
                        ResultSet rs1=ps5.executeQuery();
                        rs1.first();
                        ResultSetMetaData r2 = rs1.getMetaData();
                        int tid;
                        tid = rs1.getInt(1);*/
                        System.out.println("Added Successfully");
                        //System.out.println("type id(tid) of the book added is: "+tid);
                    }
                    else
                    {
                        System.out.println("Error occured");
                    }
                }
                else if(op==2)
                {
                    System.out.println("Enter type of book to remove");
                    String b=sc.next();
                    String c="delete from Book_type where typename=?";
                    PreparedStatement ps1=con.prepareStatement(c);
                    ps1.setString(1, b);
                    int i=ps1.executeUpdate();
                    if(i>0)
                    {
                        System.out.println("Removed Successfully");
                    }
                    else
                    {
                        System.out.println("Error occured");
                    }                   
                }
                else if(op==3)
                {
                    System.out.println("Enter type of book to be changed");
                    String b=sc.next();
                    System.out.println("Enter the new updated name");
                    String b2=sc.next();
                    
                    String c="update Book_type set typename=? where typename=?";
                    PreparedStatement ps1=con.prepareStatement(c);
                    ps1.setString(1, b2);
                    ps1.setString(2, b);
                    int i=ps1.executeUpdate();
                    if(i>0)
                    {
                        System.out.println("Updated Successfully");
                    }
                    else
                    {
                        System.out.println("Error occured");
                    }                   
                }
                else if(op==4)
                {
                    System.out.println("--------------------------------------");
                    System.out.println("Book Details:");
                    String s1=("select * from book;");
                    PreparedStatement ps1=con.prepareStatement(s1);
                    ResultSet rs1=ps1.executeQuery();
                    ResultSetMetaData rsmd1 = rs1.getMetaData();
                    printDetails(rsmd1,rs1);
                    bookDetails();
                }
                else if(op!=5)
                {
                    System.out.println("Invalid choice, Please try again");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void issueDetails()
    {
        try {
            System.out.println("--------------------------------------");
            System.out.println("Issued Details:");
            String s=("select * from issue;");
            PreparedStatement ps=con.prepareStatement(s);
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            printDetails(rsmd,rs);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void bookDetails()
    {
        int op=0;
        while(op!=4)
        {
            System.out.println();
            System.out.println("Enter choice:");
            System.out.println("1.Add Books \n2.Remove Books \n3.Update the books \n4.Finish");
            op=sc.nextInt();
            if(op==1)
            {
                try {
                    System.out.println("Enter the book to be added");
                    String b1=sc.next();
                    
                    System.out.println("Enter the author of the book");
                    //sc.next();
                    String a1=sc.next();
                    
                    System.out.println("Enter the typename and tid of the book respectively");
                    
                    String t1=sc.next();
                    int tid=sc.nextInt();
                    System.out.println("Enter version if exists otherwise enter null");
                    //sc.next();
                    String v1=sc.next();
                    String q=("insert into book(book,tid,author,version) values(?,?,?,?)");
                    PreparedStatement ps=con.prepareStatement(q);
                    ps.setString(1,b1);
                    ps.setInt(2,tid);
                    ps.setString(3,a1);
                    ps.setString(4,v1);
                    int i=ps.executeUpdate();
                    if(i>0)
                    {
                        String q2="update Book_type set number=number+1 where tid=? or typename=?";
                        PreparedStatement ps2=con.prepareStatement(q2);
                        ps2.setInt(1,tid);
                        ps2.setString(2, t1);
                        int j=ps2.executeUpdate();
                        if(j>0)
                        {
                            System.out.println("Successfully Added");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(op==2)
            {
                try {
                    System.out.println("Enter the bookname and bookId (bid) to be removed respectively");
                    String b1=sc.next();
                    int bid=sc.nextInt();
                    String q3="update Book_type set number=number-1 where tid=(select tid from book where bid=? or book=?)";
                    PreparedStatement ps3=con.prepareStatement(q3);
                    ps3.setInt(1,bid);
                    ps3.setString(2, b1);
                    int j=ps3.executeUpdate();
                    
                    String q2="delete from book where bid=? or book=?";
                    PreparedStatement ps2=con.prepareStatement(q2);
                    ps2.setInt(1,bid);
                    ps2.setString(2, b1);
                    int i=ps2.executeUpdate();
                    if(i>0 && j>0)
                    {
                        System.out.println("Successfully Removed");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(op==3)
            {
                int o=0;
                while(o!=5)
                {
                    System.out.println("Press \n1.Update bookname \n2.Update type(tid) \n3.Update Author \n4.Version \n5.Finish");
                    o=sc.nextInt();
                    if(o==1)
                    {
                        try {
                            System.out.println("Enter book id(bid) ");
                            int bid=sc.nextInt();
                            System.out.println("Enter the new bookname ");
                            String b1=sc.next();
                            String q="update book set book=? where bid=?";
                            PreparedStatement ps2=con.prepareStatement(q);
                            ps2.setString(1, b1);
                            ps2.setInt(2,bid);
                            int i=ps2.executeUpdate();
                            if(i>0)
                            {
                                System.out.println("Successfully updated");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(o==2)
                    {
                        try {
                            System.out.println("Enter book id(bid) of the type to be updated");
                            int bid=sc.nextInt();
                            System.out.println("Enter the new type id (tid) and the type name respectively");
                            int tid=sc.nextInt();
                            String type=sc.next();
                            
                            String qu="select tid from book where bid=?";
                            PreparedStatement pst=con.prepareStatement(qu);
                            pst.setInt(1, bid);
                            ResultSet rt=pst.executeQuery();
                            rt.first();
                            ResultSetMetaData rsmd = rt.getMetaData();
                            int ct;
                            ct = rt.getInt(1);
                            
                            String qu2="update Book_type set number=number-1 where tid=?";
                            PreparedStatement pst2=con.prepareStatement(qu2);
                            pst2.setInt(1,ct);
                            int k=pst2.executeUpdate();
                            
                            
                            String q="update book set tid=? where bid=?";
                            PreparedStatement ps2=con.prepareStatement(q);
                            ps2.setInt(1, tid);
                            ps2.setInt(2,bid);
                            int i=ps2.executeUpdate();
                            if(i>0)
                            {
                                String q2="update Book_type set number=number+1 where tid=? or typename=?";
                                PreparedStatement ps=con.prepareStatement(q2);
                                ps.setInt(1,tid);
                                ps.setString(2, type);
                                int j=ps.executeUpdate();
                                if(j>0)
                                {
                                    System.out.println("Successfully updated");
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(o==3)
                    {
                        try {
                            System.out.println("Enter book id(bid) ");
                            int bid=sc.nextInt();
                            System.out.println("Enter the new author name ");
                            String a1=sc.next();
                            String q="update book set author=? where bid=?";
                            PreparedStatement ps2=con.prepareStatement(q);
                            ps2.setString(1, a1);
                            ps2.setInt(2,bid);
                            int i=ps2.executeUpdate();
                            if(i>0)
                            {
                                System.out.println("Successfully updated");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(o==4)
                    {
                        try {
                            System.out.println("Enter book id(bid) ");
                            int bid=sc.nextInt();
                            System.out.println("Enter the new version ");
                            String v1=sc.next();
                            String q="update book set version=? where bid=?";
                            PreparedStatement ps2=con.prepareStatement(q);
                            ps2.setString(1, v1);
                            ps2.setInt(2,bid);
                            int i=ps2.executeUpdate();
                            if(i>0)
                            {
                                System.out.println("Successfully updated");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(o!=5)
                    {
                        System.out.println("Invalid choice, Please try again");
                    }
                }
            }
            else if(op!=4)
            {
                System.out.println("Invalid choice, Please try again");
            }
        }
    }
}

package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
//import java.time.*;
import java.util.logging.Logger;
//import java.time.temporal.ChronoUnit;
import java.time.LocalDate;


public class Customer {
    Scanner sc=new Scanner(System.in);
    public void enterDetails()
    {
        System.out.println("-------------Welcome to the User login of the Library-------------");
        System.out.println("Enter choice");
        System.out.println("1.First User \n2.Member Login");
        int op=sc.nextInt();
        if(op==1)
        {
            firstUser();
        }
        else if(op==2)
        {
            try {
                member();
            } catch (Exception ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void firstUser()
    {
        try {
           
            Connection con=MakeConnection.connect();
            System.out.println("Enter your First name");
            String fn=sc.next();
            System.out.println("Enter your Last name");
            String ln=sc.next();
            System.out.println("Enter your Email Id");
            String em=sc.next();
            
            String qr=("insert into customer(fname,lname,email) values(?,?,?)");
            PreparedStatement ps=con.prepareStatement(qr);
            ps.setString(1, fn);
            ps.setString(2, ln);
            ps.setString(3,em);
            int i=ps.executeUpdate();
            if(i>0) 
            {
               String q=("select cid from customer where email=?");
                PreparedStatement ps2=con.prepareStatement(q);
                ps2.setString(1, em);
                ResultSet rs1=ps2.executeQuery();
                rs1.first();
                ResultSetMetaData rsmd = rs1.getMetaData();
                int cid;
                cid = rs1.getInt(1);
                System.out.println("Registered Successfully \n Your customer id is "+cid+"\n");
                issue(cid);
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void member()
    {
        try {
            //System.out.println("------------------------------------------------")
            System.out.println("Enter name :");
            String name=sc.next();
            System.out.println("Enter your Email Id");
            String em=sc.next();
            String qr=("select * from customer where email=?");
            Connection con=MakeConnection.connect();
            PreparedStatement ps=con.prepareStatement(qr);
            ps.setString(1, em);
            ResultSet rs=ps.executeQuery();
            int r=0;
            while(rs.next())
            {
                r=rs.getRow();
            }
            if(r<=0)
                System.out.println("Invalid user");
            else {
                String q=("select cid from customer where email=?");
                PreparedStatement ps2=con.prepareStatement(q);
                ps.setString(1, em);
                ResultSet rs1=ps.executeQuery();
                rs1.first();
                ResultSetMetaData rsmd = rs1.getMetaData();
                int cid;
                cid = rs1.getInt(1);
                System.out.println("Hello "+name+"\nCustomer id is "+cid+"\n");
                System.out.println("Press \n1.Issue \n2.Return");
                int op=sc.nextInt();
                if(op==1)
                    issue(cid);
                else
                    returning(cid);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     public void issue(int cid)
     {
        try {
            System.out.println("Enter tid of the type of book");
            Connection con=MakeConnection.connect();
            String qr="select tid,typename from Book_type";
            PreparedStatement ps=con.prepareStatement(qr);
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            while (rs.next()) 
            {
                for (int i = 1; i <= colnum; i++)
                {
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " : " +columnValue+"\t");
                    if (i%2==0) System.out.println();
                }
            }
            int type=sc.nextInt();
            System.out.println("Enter bid of the book to be issued");
            String qq=("select bid,book,author,version from book where tid=?");
            PreparedStatement ps1=con.prepareStatement(qq);
            ps1.setInt(1,type);
            ResultSet rs1=ps1.executeQuery();
            ResultSetMetaData rsmd1 = rs1.getMetaData();
            int colnum1 = rsmd1.getColumnCount();
            
            while (rs1.next()) 
            {
                for (int i = 1; i <= colnum1; i++)
                {
                    try {
                        String columnValue;
                        columnValue = rs1.getString(i);
                        System.out.println(rsmd1.getColumnName(i) + ":" +columnValue );
                        if (i%4==0)
                            System.out.println();
                    } catch (SQLException ex) {
                        Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            int bid=sc.nextInt();  
            String b=("select book from book where bid=?");
            PreparedStatement pq=con.prepareStatement(b);
            pq.setInt(1,bid);
            ResultSet r2=pq.executeQuery();
            r2.first();
            ResultSetMetaData rsmd2 = r2.getMetaData();
            String book;
            book = r2.getString(1);
            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
            //Date date = new Date();
            //long millis=System.currentTimeMillis();
            //java.sql.Date date=new java.sql.Date(millis);
            System.out.println("Enter issue date in the format(yyyy-mm-dd)");
            String date=sc.next();
            LocalDate date1=LocalDate.parse(date);
            LocalDate returnvalue;
            returnvalue = date1.plusDays(15);
            String date2=returnvalue.toString();
            String c=("insert into issue(bid,cid,bookname,idate,exdate) values(?,?,?,?,?)");
            PreparedStatement cq=con.prepareStatement(c);
            cq.setInt(1,bid);
            cq.setInt(2,cid);
            cq.setString(3,book);
            cq.setString(4,date);
            cq.setString(5,date2);
            int j=cq.executeUpdate();
            if(j>0)
            {
                System.out.println("------------------------------------------------");
                System.out.println("\nIssue successful");
                System.out.println("Customer is (cid): "+cid);
                System.out.println("Book id (bid): "+bid);
                System.out.println("Book Name: "+book);
                System.out.println("Issue Date: "+date);
                System.out.println("Return date: "+date2);
                System.out.println("In case of delay in returning the book, fine=Rs1/day ");
            }
            else
            {
                System.out.println("Try again later.. Some issues occured");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public void returning(int cid)
    {
        try {
            Connection con=MakeConnection.connect();
            System.out.println("Enter return date in the format(yyyy-mm-dd)");
            String date=sc.next();
            System.out.println("Enter the book_id(bid) to be returned");
            int bid=sc.nextInt();
            LocalDate date1=LocalDate.parse(date);
                String d1="select exdate from issue where cid=? and bid=?";
                PreparedStatement pqrs=con.prepareStatement(d1);
                pqrs.setInt(1,cid);
                pqrs.setInt(2,bid);
                ResultSet r3=pqrs.executeQuery();
                r3.first();
                ResultSetMetaData rsmd = r3.getMetaData();
                String dat;
                dat = r3.getString(1);
                LocalDate date2=LocalDate.parse(dat);
                long fine=date1.toEpochDay()-date2.toEpochDay();
                if(fine<=0)
                    fine=0;
                String d="delete from issue where cid=? and bid=?";           
                PreparedStatement pqr=con.prepareStatement(d);
                pqr.setInt(1,cid);
                pqr.setInt(2,bid);
                int j=pqr.executeUpdate();
                if(j>0)
                {
                    System.out.println("\nReturn Successful");
                    System.out.println("Book Id returned: "+bid);
                    System.out.println("Return Date: "+date1);
                    System.out.println("Fine to be paid: "+fine);
                }
                else
                {
                    System.out.println("Try again later.. Some issues occured");
                }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ResultSet executeQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


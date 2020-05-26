<%-- 
    Document   : viewDetails
    Created on : May 4, 2020, 6:49:13 PM
    Author     : NAGARAJ
--%>

<%@page import="java.sql.*"%>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View</title>
    </head>
    <body>
        <% 
            Connection con;
            PreparedStatement ps;
            ResultSet rs=null;
            int fno=Integer.parseInt(request.getParameter("fno"));
            
            try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight_reservation","root","");
            ps=con.prepareStatement("select * from flight where flight_no = ?");
            ps.setInt(1,fno);
            rs=ps.executeQuery();
            if(rs.next())
            {
                String date=request.getParameter("date");
                String name=request.getParameter("name");
                String phno=request.getParameter("phno");
                Random rand=new Random();
                int seat=-1;
                while(true){
                    seat=rand.nextInt(500);
                    ps=con.prepareStatement("select * from seatreservation where flight_no=? and seat_no=?");
                    ps.setInt(1,fno);
                    ps.setInt(2,seat);
                    rs=ps.executeQuery();
                    if(rs.next())
                    {
                        
                    }
                    else
                    {
                        break;
                    }
                }
                ps=con.prepareStatement("insert into seatreservation values(?,?,?,?,?) ");
                ps.setInt(1,fno);
                ps.setString(2,date);
                ps.setInt(3,seat);
                ps.setString(4,name);
                ps.setString(5,phno);
                int y=ps.executeUpdate();
                   %>
                   
                    <p>Customer Name : <%=name%></p>
                    <p> Customer Phone_no : <%=phno%></p>
                    <p>Flight Number : <%=fno%></p>
                    <p>Seat Number : <%=seat%></p>
                    <p>Date : <%=date%></p>
                    <a href="reservation.jsp">Exit</a>
                   <%
                
            }
            else
            {
               %> <jsp:forward page="reservation.jsp">
                        <jsp:param name="Error" value="Flight number does not exist"/>
            </jsp:forward><%
            }

            }
            catch(Exception e)
            {
                out.println(e);
            }

            
           %>
    </body>
</html>

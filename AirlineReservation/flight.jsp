<%-- 
    Document   : flight
    Created on : May 4, 2020, 1:27:06 PM
    Author     : NAGARAJ
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Details</title>
    </head>
    <body>
     
    <% 
        Connection con;
        PreparedStatement ps;
        if(request.getParameter("view").equals("View"))
        {
            String day=request.getParameter("day");
            try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight_reservation","root","");
            ps=con.prepareStatement("select * from flight where weekdays like ?");
            ps.setString(1,day);
            ResultSet rs=ps.executeQuery();
    %>
    <table border="5px"><tr><th>FLIGHT_NO</th><th>AIRLINE_NAME</th><th>DAY</th></tr>
    <%
            while(rs.next())
            {
     %>
        <tr><td><%=rs.getString(1)%></td><td><%=rs.getString(2)%></td><td><%=rs.getString(3)%></td></tr>
     <%           
            }
            }
            catch(Exception ex)
            {
                out.println(ex);
            }
            
        } 
    %>
    </table>
    </body>
</html>

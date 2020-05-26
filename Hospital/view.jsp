<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View</title>
    </head>
    <body>
        <%@ page import="java.io.IOException,java.io.PrintWriter,java.sql.*,java.util.logging.*,
       javax.servlet.ServletException,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse;"%>
        
        <%
        Connection con=null;
            try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            out.println(ex);
        }
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital","root","");
            PreparedStatement ps=con.prepareStatement("select * from patient order by id");
            ResultSet rs=ps.executeQuery();
            %>
            
    <center><h1>Patient Details!</h1><table border="1px"><tr><th>ID</th><th>Patient Name</th><th>Age</th><th>Date of Admission</th><th>Cause</th><th>Doctor</th><th>Treatment</th></tr>
            
            <%
            while(rs.next())
            {
             %>
             <tr><td><%=rs.getString(1)%></td><td><%=rs.getString(2)%></td><td><%=rs.getString(3)%></td>
                 <td><%=rs.getString(4)%></td><td><%=rs.getString(5)%></td><td><%=rs.getString(6)%></td>
                 <td><%=rs.getString(7)%></td></tr>
             <%
            }
                %>
        </table>
             <%
        } catch (SQLException ex) {
            out.println(ex);
        }
        %>
    <a href="index.jsp">Exit</a></center>
    </body>
</html>

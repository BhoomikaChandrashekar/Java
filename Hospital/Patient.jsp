<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert</title>
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
        } catch (SQLException ex) {
            out.println(ex);
        }
        if("insert".equals(request.getParameter("submit"))){
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String age=request.getParameter("age");
        String date=request.getParameter("date");
        String cause=request.getParameter("cause");
        String doctor=request.getParameter("doctor");
        String treat=request.getParameter("treat");
        
        try {
            String query="insert into Patient values(?,?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,age);
            ps.setString(4,date);
            ps.setString(5,cause);
            ps.setString(6,doctor);
            ps.setString(7,treat);
            int y=ps.executeUpdate();
            if(y>0)
            {
                %>
                <script>
                    alert('Details entered successfully');
                </script>
                <%
            }
            else
            {
            %>
                <script>
                    alert('Details not entered successfully');
                </script>
                <%
            }
            getServletContext().getRequestDispatcher("/insert.jsp").include(request,response);
            
        } catch (SQLException ex) {
            out.println(ex);
        }
        }

        else if("delete".equals(request.getParameter("submit")))
        {
            String id=request.getParameter("id");
        try{
        PreparedStatement ps1=con.prepareStatement("delete from patient where id=?");
            ps1.setString(1,id);
            int y=ps1.executeUpdate();
            if(y>0)
            {
            %>
                <script>
                    alert('Details deleted successfully');
                </script>
                <%
        }
        else
            {
            %>
                <script>
                    alert('Details not deleted successfully');
                </script>
                <%
            }
            getServletContext().getRequestDispatcher("/delete.jsp").include(request,response);
        
        } catch (SQLException ex) {
            out.println(ex);
        }
        }
                
        else if("update".equals(request.getParameter("submit")))
        {
            String id=request.getParameter("id");
            try{
                PreparedStatement ps=con.prepareStatement("select * from patient where id=?");
                ps.setString(1,id);
                ResultSet rs=ps.executeQuery();
                if(rs.next())
                {
                    %>
                    <form action="Patient.jsp" method="post">
                        Patient ID <input type="text" name="id" readOnly value="<%=rs.getString(1)%>"/></br></br>
                        Patient Name <input type="text" name="name" readOnly value="<%=rs.getString(2)%>"/></br></br>
                        Age <input type="text" name="age" value="<%=rs.getString(3)%>"/></br></br>
                        Date of Admission <input type="text" name="date" readOnly value="<%=rs.getString(4)%>"/></br></br>
                        Cause of Admission <input type="text" name="cause" value="<%=rs.getString(5)%>"/></br></br>
                        Doctor Diagnosed <input type="text" name="doctor" value="<%=rs.getString(6)%>"/></br></br>
                        Treatment Proposed <input type="text" name="treat" value="<%=rs.getString(7)%>"/></br></br></br>
                        <input type="submit" name="Update" value="Update"/>
                    </form>
                        <a href="index.jsp">Exit</a>
                   <% 
                }
            }
            catch (SQLException ex) {
                out.println(ex);
            }
        }
                
        else if("Update".equals("Update"))
        {
            String id=request.getParameter("id");
            String age=request.getParameter("age");
            String cause=request.getParameter("cause");
            String doc=request.getParameter("doctor");
            String treat=request.getParameter("treat");

            try{
                PreparedStatement ps=con.prepareStatement("update patient set age=?,cause=?,doctor=?,treat=?");
                ps.setString(1,age);
                ps.setString(2,cause);
                ps.setString(3,doc);
                ps.setString(4,treat);
                int y=ps.executeUpdate();
                if(y>0)
                {
                    %>
                    <script>
                        alert('Details updated successfully');
                    </script>
                    <%
                }
                else
                {
                    %>
                    <script>
                        alert('Details not updated... try again');
                    </script>
                <%
                }
                getServletContext().getRequestDispatcher("/update.jsp").include(request,response);
            }
            catch (SQLException ex) {
                out.println(ex);
            }   
        }        
        %>
    </body>
</html>

<%-- 
    Document   : reservation
    Created on : May 4, 2020, 6:36:41 PM
    Author     : NAGARAJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reservation Form</h1>
        <form action="viewDetails.jsp" method="post">
            Flight Number : <input type="text" name="fno"/><br><br>
            Date : <input type="text" name="date"/><br><br>
            Customer Name : <input type="text" name="name"/><br><br>
            Customer Phone_No : <input type="text" name="phno"/><br><br>
            <input type="submit" value="View Details" name="view"/>
        </form>
        <%
          String error=request.getParameter("Error");
          if(error!=null)
          {
              %>
              
              <p><%=error%></p>
              
              <%
          }
            
        %>
    </body>
</html>

<%-- 
    Document   : logout
    Created on : May 3, 2020, 3:40:09 PM
    Author     : NAGARAJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LogOut</title>
    </head>
    <body>
        <%
          response.setHeader("Cache-Control", "no-cache");
          response.setHeader("Cache-Control", "no-store");
          response.setHeader("Pragma", "no-cache");
          response.setDateHeader("Expires", 0);
            if(request.getParameter("logout").equals("LOGOUT"))
            {
                
                if(session!=null)
                {
                    session.invalidate();
                    out.println("You have been logged out successfully");
                }
                
            }
         %>
         <a href="login.jsp">Login</a>
        
    </body>
</html>

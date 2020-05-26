<%-- 
    Document   : login
    Created on : May 1, 2020, 3:56:47 PM
    Author     : NAGARAJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%
          response.setHeader("Cache-Control", "no-cache");
          response.setHeader("Cache-Control", "no-store");
          response.setHeader("Pragma", "no-cache");
          response.setDateHeader("Expires", 0);
            
            %>
        <form action="dataCapture.jsp" method="post">
            Name:<input type="text" name="name"/><br><br>
            Password:<input type="password" name="pwd"/><br><br>
            <input type="submit" value="Login"/>
        </form>
        <%
          String reason=request.getParameter("Error");
          if(reason!=null){
        %>
        <h3><%=reason%></h3>
        <%}%>
    </body>
</html>

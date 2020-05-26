<%-- 
    Document   : dataCapture
    Created on : May 1, 2020, 4:11:48 PM
    Author     : NAGARAJ
--%>
<%@page import="java.util.*,java.time.*"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <%
            if(request.getParameter("name")!=null && request.getParameter("pwd")!=null){
            HashMap hm=new HashMap();
            hm.put("John","John");
            hm.put("James","James");
            String name=request.getParameter("name");
            String pwd=request.getParameter("pwd");
            session.setAttribute("user",name);
            if(hm.containsKey(name))
            {
                String pwd1=(String)hm.get(name);
                if(pwd1.equals(pwd))
                {
        %>
                   
    <center> <h1>Welcome <%=name%></h1>
        <%
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
          LocalDateTime now = LocalDateTime.now();      
        %>
        <h3><%=dtf.format(now)%></h3>
        <form action="calculateTax.jsp" method="post">
            Name : <input type="text" name="name" value=<%=name%> readOnly/><br><br>
            Place of Work : <select name="work">
                    <option value="">Select place of work</option>
                    <option value="Adobe">Adobe</option>
                    <option value="Amazon">Amazon</option>
                    <option value="Google">Google</option>
                    <option value="Paytm">Paytm</option>
                    <option value="JPMorgan">JPMorgan</option>
                    <option value="FlipKart">FlipKart</option>
                </select><br><br>
            Gender : <input type="radio" name="gen" value="M"/>Male
                <input type="radio" name="gen" value="F"/>Female<br><br>
            Annual Income : <input type="text" name="income"/><br><br>
            <input type="submit" name="submit" value="Calculate Tax"/>   
        </form>
    </center>
        <%
                }
                else
                {
                    %>
                    <jsp:forward page="login.jsp">
                        <jsp:param name="Error" value="Wrong Password"/>
                    </jsp:forward>
                    <%
                }
            }
            else
            {
        %>
            <jsp:forward page="login.jsp">
                        <jsp:param name="Error" value="Wrong User Name"/>
            </jsp:forward>
        <%
            }
        }
    else{
            String output=request.getParameter("Details");
            if(output!=null)
            {
        %>
    <h3>Welcome <%=request.getParameter("Detail")%></h3>
    <p><%=request.getParameter("Details")%></p>
    <p><%=request.getParameter("Details1")%></p>
    <p><%=request.getParameter("Details2")%></p>
    <p><%=request.getParameter("Details3")%></p>
    <p><%=request.getParameter("Details4")%></p>
    <p><%=request.getParameter("Details5")%></p>
    <form action="logout.jsp" method="post">
        <input type="submit" name="logout" value="LOGOUT"/>
    </form> 
    <% } 
    }
    %>
    
        
    </body>
</html>

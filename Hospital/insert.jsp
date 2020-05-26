<%-- 
    Document   : insert
    Created on : Apr 27, 2020, 6:57:36 PM
    Author     : NAGARAJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert</title>
    </head>
    <body>
        
        <h2>Patient Details</h2>
        <form action="Patient.jsp" method="post">
            Patient ID <input type="text" name="id"/></br></br>
            Patient Name <input type="text" name="name"/></br></br>
            Age <input type="text" name="age"/></br></br>
            Date of Admission <input type="text" name="date"/></br></br>
            Cause of Admission <input type="text" name="cause"/></br></br>
            Doctor Diagnosed <input type="text" name="doctor"/></br></br>
            Treatment Proposed <input type="text" name="treat"/></br></br></br>
            <input type="submit" name="submit" value="insert"/>
        </form>
        <a href="index.jsp">EXIT</a>
    </body>
</html>

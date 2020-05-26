<%-- 
    Document   : index
    Created on : Apr 24, 2020, 3:52:47 PM
    Author     : NAGARAJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IT Returns</title>
    </head>
    <body>
        <form action="ITReturns" method="post">
            <h1>Personal Information</h1>
            Name : <input type="text" name="name"/><br><br>
            Phone Number : <input type="text" name="phno"/><br><br>
            Email Id : <input type="text" name="email"/><br><br><br>
            <h1>Salary Details</h1>
            Salary : <input type="text" name="salary"/><br><br>
            <h1>Tax Details</h1>
            Tax : <input type="text" name="tax"/><br><br>
            <input type="submit" name="submit" value="Submit"/>
        </form>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete</title>
    </head>
    <body>
        <form action="Patient.jsp" method="post">
            Patient ID:<input type="text" name="id"/>
            <input type="submit" name="submit" value="delete"/>
        </form>
        <a href="index.jsp">EXIT</a>
    </body>
</html>

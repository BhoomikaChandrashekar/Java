<%-- 
    Document   : index
    Created on : Apr 25, 2020, 1:07:35 PM
    Author     : NAGARAJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>T-Shirt Sales</title>
    </head>
    <body>
        <h1>Online T-Shirt Selling Application</h1>
        <form action="TShirt" method="post">
            <label><b>Additional Accessories : </b></label>
                <input type="checkbox" name="acc" value="Belt"/>Belt
                <input type="checkbox" name="acc" value="Cap"/>Cap
                <input type="checkbox" name="acc" value="Hairband"/>Hair band <br><br>
                <label><b>T-Shirt Tag Line : </b></label>
            <input type="text" name="tagline"/><br><br>
            <label><b>Pocket : </b></label>
                <input type="radio" name="pocket" value="Yes"/>Yes
                <input type="radio" name="pocket" value="No"/>No<br><br>
                <label><b>T-Shirt color : </b></label>
                <select name="colors">
                    <option value="">Select color</option>
                    <option value="Red">Red</option>
                    <option value="White">White</option>
                    <option value="Black">Black</option>
                    <option value="Green">Green</option>
                    <option value="Blue">Blue</option>
                    <option value="Yellow">Yellow</option>
                </select><br><br>
            <input type="submit" value="Click Me"/>
        </form>
    </body>
</html>

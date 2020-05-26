/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ITReturns extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File fname=new File("C:\\Users\\NAGARAJ\\Documents\\java programs\\ITReturns\\Details.txt");
        fname.createNewFile();
        PrintWriter out=response.getWriter();
        String name=request.getParameter("name");
        String phno=request.getParameter("phno");
        String email=request.getParameter("email");
        String sal=request.getParameter("salary");
        String tax=request.getParameter("tax");
        BufferedWriter filewriter=new BufferedWriter(new FileWriter("C:\\Users\\NAGARAJ\\Documents\\java programs\\ITReturns\\Details.txt"));
        filewriter.write("Name : "+name);
        filewriter.newLine();
        filewriter.write("Phno : "+phno);
        filewriter.newLine();
        filewriter.write("Email : "+email);
        filewriter.newLine();
        filewriter.write("Salary : "+sal);
        filewriter.newLine();
        filewriter.write("Tax : "+tax);
        filewriter.close();
        out.println("Details");
        out.println("Name : "+name+"\nPhno : "+phno+"\nEmail : "+email+"\nSalary : "+sal+"\nTax : "+tax); 
    }
}

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Patient extends HttpServlet {
    Connection con;
    public void init()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital","root","");
        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
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
                request.setAttribute("msg","Details Inserted Successfully");
            }
            else
            {
                request.setAttribute("msg","Details has not been entered successfully, plz try again");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}

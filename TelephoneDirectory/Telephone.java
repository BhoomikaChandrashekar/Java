import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Telephone extends HttpServlet {
    Connection con;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String button=request.getParameter("submit");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Telephone.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineDircetory","root","");
        } catch (SQLException ex) {
            Logger.getLogger(Telephone.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
        String det=request.getParameter("detail");
        PreparedStatement ps=null;
        if(button.equals("View")){
            try{
                try{
                    long phno=Long.parseLong(det);
                    try {
                        ps=con.prepareStatement("select * from telephone_directory where phno=?");
                        ps.setLong(1,phno);
                    } catch (SQLException ex) {
                        Logger.getLogger(Telephone.class.getName()).log(Level.SEVERE, null, ex);
                        out.println(ex);
                    }
                    
                }
                catch(NumberFormatException e)
                {
                    try {
                        ps=con.prepareStatement("select * from telephone_directory where name=?");
                        ps.setString(1,det);
                    } catch (SQLException ex) {
                        Logger.getLogger(Telephone.class.getName()).log(Level.SEVERE, null, ex);
                        out.println(ex);
                    }
                }
                ResultSet rs;
                rs = ps.executeQuery();
                if(rs.next())
                {
                    out.println("Name : "+rs.getString(1)+"<br>");
                    out.println("Phno : "+rs.getLong(2)+"<br>");
                    out.println("Address : "+rs.getString(3)+"<br>");
                    out.println("Company : "+rs.getString(4)+"<br>");
                    out.println("Pin Code : "+rs.getString(5)+"<br>");
                }
                out.println("<a href='index.html'>Exit</a>");
            }
            catch(SQLException ex)
            {
                Logger.getLogger(Telephone.class.getName()).log(Level.SEVERE, null, ex);
                out.println(ex);
            }
        }
        else if(button.equals("Insert"))
        {
            try {
                String name=request.getParameter("name");
                long phno=Long.parseLong(request.getParameter("phno"));
                String addr=request.getParameter("addr");
                String comp=request.getParameter("comp");
                String pin=request.getParameter("pin");
                ps=con.prepareStatement("insert into telephone_directory values(?,?,?,?,?)");
                ps.setString(1,name);
                ps.setLong(2,phno);
                ps.setString(3,addr);
                ps.setString(4,comp);
                ps.setString(5,pin);
                int y=ps.executeUpdate();
                if(y>0)
                {
                    out.println("Entered successfully");
                }
                else
                {
                    out.println("Not Entered successfully");
                }
                out.println("<a href='index.html'>Exit</a>");
            } catch (SQLException ex) {
                Logger.getLogger(Telephone.class.getName()).log(Level.SEVERE, null, ex);
                out.println(ex);
            }
        }
    }
}

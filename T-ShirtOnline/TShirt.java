import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TShirt extends HttpServlet {
    Connection con;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acc[]=request.getParameterValues("acc");
        String accessories="";
        String tagline=request.getParameter("tagline");
        String pocket=request.getParameter("pocket");
        String color=request.getParameter("colors");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TShirt.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/TShirtDetails","root","");
        } catch (SQLException ex) {
            Logger.getLogger(TShirt.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
        for(String a:acc)
        {
            if(a!=null)
                accessories+=a+" ";
        }
        try {
            PreparedStatement ps=con.prepareStatement("select * from tshirt order by OrderNo desc");
            ResultSet rs=ps.executeQuery();
            int order=0;
            if(rs.next())
            {
                order=Integer.parseInt(rs.getString("OrderNo"));
                order++;
                ps=con.prepareStatement("insert into TShirt values(?,?,?,?,?)");
                ps.setString(1,String.valueOf(order));
                ps.setString(2,accessories);
                ps.setString(3,tagline);
                ps.setString(4,pocket);
                ps.setString(5,color);
                int y=ps.executeUpdate();
                if(y>0)
                {
                    out.println("<script>alert(\"Details entered successfully\")</script>");
                }
                else
                {
                    out.println("<script>alert(\"Details not entered successfully\")</script>");
                }
            }
            else
            {
                ps=con.prepareStatement("insert into tshirt values(?,?,?,?,?)");
                ps.setString(1,100+"");
                ps.setString(2,accessories);
                ps.setString(3,tagline);
                ps.setString(4,pocket);
                ps.setString(5,color);
                int y=ps.executeUpdate();
                if(y>0)
                {
                    out.println("<script>alert(\"Details entered successfully\")</script>");
                }
                else
                {
                    out.println("<script>alert(\"Details not entered successfully\")</script>");
                }
            }
            ps=con.prepareStatement("select * from tshirt order by OrderNo");
            rs=ps.executeQuery();
            out.println("<table border=\"1px\" align='center' cellpadding='25px'><tr>");
            out.println("<th>OrderNo</th><th>Accessories</th><th>TagLine</th><th>Pocket</th><th>Color</th>");
            while(rs.next())
            {
                out.println("<tr><td>"+rs.getString(1)+"</td>");
                out.println("<td>"+rs.getString(2)+"</td>");
                out.println("<td>"+rs.getString(3)+"</td>");
                out.println("<td>"+rs.getString(4)+"</td>");
                out.println("<td>"+rs.getString(5)+"</td></tr>");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TShirt.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
    }

}

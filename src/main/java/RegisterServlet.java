import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            
            System.out.println("Registration attempt: " + name + ", " + email);
            
            // Check if parameters are received
            if (name == null || email == null || password == null) {
                out.println("<h2>Error: Missing form data</h2>");
                out.println("<a href='register.html'>Go Back</a>");
                return;
            }
            
            Connection conn = DatabaseUtil.getConnection();
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            
            int result = stmt.executeUpdate();
            System.out.println("Insert result: " + result);
            
            if (result > 0) {
                out.println("<html><body>");
                out.println("<h2>Registration Successful!</h2>");
                out.println("<p>Welcome " + name + "!</p>");
                out.println("<a href='login.html'>Login Now</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h2>Registration Failed</h2>");
                out.println("<p>No rows were inserted.</p>");
                out.println("<a href='register.html'>Try Again</a>");
                out.println("</body></html>");
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h2>Database Error</h2>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("<a href='register.html'>Try Again</a>");
            out.println("</body></html>");
        } catch (Exception e) {
            System.err.println("General error: " + e.getMessage());
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h2>System Error</h2>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("<a href='register.html'>Try Again</a>");
            out.println("</body></html>");
        }
    }
}
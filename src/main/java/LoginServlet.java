import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            
            System.out.println("Login attempt: " + email);
            
            if (email == null || password == null) {
                out.println("<h2>Error: Missing login data</h2>");
                out.println("<a href='login.html'>Go Back</a>");
                return;
            }
            
            Connection conn = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String userName = rs.getString("name");
                System.out.println("Login successful for: " + userName);
                
                HttpSession session = req.getSession();
                session.setAttribute("userName", userName);
                session.setAttribute("userEmail", email);
                
                res.sendRedirect("profile");
            } else {
                System.out.println("Login failed for: " + email);
                out.println("<html><body>");
                out.println("<h2>Login Failed</h2>");
                out.println("<p>Invalid email or password!</p>");
                out.println("<a href='login.html'>Try Again</a>");
                out.println("</body></html>");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h2>Database Error</h2>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("<a href='login.html'>Try Again</a>");
            out.println("</body></html>");
        } catch (Exception e) {
            System.err.println("General error: " + e.getMessage());
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h2>System Error</h2>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("<a href='login.html'>Try Again</a>");
            out.println("</body></html>");
        }
    }
}
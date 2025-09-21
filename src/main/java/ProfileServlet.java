import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        res.setContentType("text/html");
        
        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("userName") == null) {
            res.sendRedirect("login.html");
            return;
        }
        
        String name = (String) session.getAttribute("userName");
        String email = (String) session.getAttribute("userEmail");
        
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h2>Welcome " + name + "!</h2>");
        out.println("<p>Email: " + email + "</p>");
        out.println("<form action='logout' method='post'>");
        out.println("<input type='submit' value='Logout'>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
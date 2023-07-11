import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("h2 { text-align: center; }");
        out.println("form { width: 300px; margin: 0 auto; }");
        out.println("input[type=text], input[type=password] { width: 100%; padding: 10px; margin-bottom: 10px; }");
        out.println("input[type=submit] { width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h2>Login Form</h2>");
        out.println("<form method=\"POST\" action=\"/login\">");
        out.println("ID: <input type=\"text\" name=\"id\"><br>");
        out.println("Password: <input type=\"password\" name=\"password\"><br>");
        out.println("<input type=\"submit\" value=\"Login\">");
        out.println("</form>");
        out.println("</body></html>");
    }
}

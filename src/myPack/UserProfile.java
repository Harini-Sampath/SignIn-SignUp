package myPack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserProfile extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		String n = (String) session.getAttribute("usernm");
		String uname = n.toUpperCase();
		out.print("<center><h2>Welcome " + uname + "\n\n</h2></center>");
		req.getRequestDispatcher("Logout.html").include(req, res); 
	}
}

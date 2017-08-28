package myPack;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
//import com.google.appengine.repackaged.com.google.datastore.v1.Entity;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String userE = req.getParameter("uEmail");
		String password = req.getParameter("psd");

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query("User").setFilter(new FilterPredicate("userEmail", FilterOperator.EQUAL,userE ));
		PreparedQuery pq = datastore.prepare(q);
		com.google.appengine.api.datastore.Entity storedUser = pq.asSingleEntity();
		if (storedUser != null) {
		String storedPassword = (String) storedUser.getProperty("passWord");
		
		String storedUserName = (String) storedUser.getProperty("userName");
		HttpSession s = req.getSession();
		s.setAttribute("usernm", storedUserName);
		
		if (storedPassword.equals(password)) {
			req.getRequestDispatcher("/profile").include(req, res);
		} else {
			out.print("<h2><center>Sorry Password Error!</center><h2>");
			req.getRequestDispatcher("/index.html").forward(req, res);

		}

	}else{
		out.print("<h2><center>Sorry UserEmail Error!</center><h2>");
                        req.getRequestDispatcher("/index.html").forward(req, res);	
	}

}
}

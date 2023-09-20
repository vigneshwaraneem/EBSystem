

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginM
 */
@WebServlet("/loginM")
public class loginM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginM() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String s = request.getParameter("submit");
		String n = request.getParameter("uName");
		String p = request.getParameter("pass");
		String m = request.getParameter("mobile");
		String sn = request.getParameter("server");
		if(s.equals("admin")) {
			if (n.equals("Admin")&&p.equals("Admin@123")) {
				RequestDispatcher rd = request.getRequestDispatcher("adminHome.html");
				rd.forward(request, response);
			} else {
				out.print(" invalid user name or password");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		}
		else if(s.equals("consumerLogin")) {
			 if (connectivity.login(n, p)) {
					RequestDispatcher rd = request.getRequestDispatcher("uBill.html");
					rd.forward(request, response);
				 } else {
						out.print(" invalid user name or password");
						RequestDispatcher rd = request.getRequestDispatcher("login.html");
						rd.include(request, response);
			 }
		}
		else {
			if (connectivity.loginWithout(m, sn)) {
				RequestDispatcher rd = request.getRequestDispatcher("uBill.html");
				rd.forward(request, response);
			 } else {
					out.print(" invalid user name or password");
					RequestDispatcher rd = request.getRequestDispatcher("login.html");
					rd.include(request, response);
		 }
		}
			
	}

	public static boolean admin(String n, String p) {
		
		return true;
	}

}

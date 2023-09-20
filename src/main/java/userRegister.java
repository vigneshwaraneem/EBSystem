

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class userBill
 */
@WebServlet("/userRegister")
public class userRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userRegister() {
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
		String name = request.getParameter("fname").concat(" "+request.getParameter("lname"));
		String uName = request.getParameter("uname");
		String pass = String.valueOf(request.getParameter("password"));
		String number = request.getParameter("number");
		String service = request.getParameter("Server");
		String city = request.getParameter("city");
		if (connectivity.registerUser(uName, pass,  service)) {
	        out.print(" successfully registered");
			RequestDispatcher rd = request.getRequestDispatcher("uBill.html");
			rd.forward(request, response);
			}
		else {
	        out.print(" unsuccessfully registered");
			RequestDispatcher rd = request.getRequestDispatcher("uRegister.html");
			rd.include(request, response);
		}
	}

}

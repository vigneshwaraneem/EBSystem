

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class userBill
 */
@WebServlet("/userBill")
public class userBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userBill() {
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
		String submit = request.getParameter("submit");
		String amount = request.getParameter("amountid");
		String serviceNo = request.getParameter("serviceNo");
		if (submit.equals("billSearch")) {
			String textBox = connectivity.fach(serviceNo);
			response.sendRedirect("uBill.html?amount="+textBox+"&server="+serviceNo);
			}
		else {
			if (!amount.isEmpty()) {
				if(connectivity.update(serviceNo)) {
					out.print(" Amount Paid");
					RequestDispatcher rd = request.getRequestDispatcher("uBill.html");
					rd.include(request, response);
				}
				else {
					out.print(" Alread paid");
					RequestDispatcher rd = request.getRequestDispatcher("uBill.html");
					rd.include(request, response);
				}
			}
		}
	}

}

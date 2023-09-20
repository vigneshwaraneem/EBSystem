

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class aBill
 */
@WebServlet("/aBill")
public class aBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aBill() {
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
		String service = request.getParameter("service");
		String serviceNo = request.getParameter("serviceNo");
		String date = request.getParameter("date");
		String unit = request.getParameter("unit");
		String amount = request.getParameter("amountid");
		if(submit.equals("billAdd")) {
			if (connectivity.billRegister(service, date, unit)) {
				out.print(" Added");
				RequestDispatcher rd = request.getRequestDispatcher("adminBill.html");
				rd.include(request, response);
			} else {
				out.print(" Alread added");
				RequestDispatcher rd = request.getRequestDispatcher("adminBill.html");
				rd.include(request, response);
			}
		}
		else if (submit.equals("billSearch")) {
			String textBox = connectivity.fach(serviceNo);
			response.sendRedirect("adminBill.html?amount="+textBox+"&server="+serviceNo);
			}
		else {
			if (!amount.isEmpty()) {
				if(connectivity.update(serviceNo)) {
					out.print(" Amount Paid");
					RequestDispatcher rd = request.getRequestDispatcher("adminBill.html");
					rd.include(request, response);
				}
				else {
					out.print(" Alread paid");
					RequestDispatcher rd = request.getRequestDispatcher("adminBill.html");
					rd.include(request, response);
				}
			}
		}
	}
}

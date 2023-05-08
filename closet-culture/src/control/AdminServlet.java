package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ArticoloBean;
import model.ArticoloDAO;
import model.UserBean;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(false);
		UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));

		if (action != null) {
			
			switch (action) {

			case "utente":
				if (currentUser != null && currentUser.getRuolo().equals("admin")) {
					session.setAttribute("sessionType", "utente");
					response.sendRedirect("home.jsp"); // logged-in page
				}
				break;

			case "admin":
				if (currentUser != null && currentUser.getRuolo().equals("admin")) {
					session.setAttribute("sessionType", "admin");
					response.sendRedirect("home.jsp"); // logged-in page
				}
				break;

			case "getArticolo": {
				int id = Integer.parseUnsignedInt(request.getParameter("id"));

				ArticoloBean articolo = ArticoloDAO.idRicerca(id);

				if (articolo != null) {

					request.setAttribute("articolo", articolo);

					RequestDispatcher dispatcher = request.getRequestDispatcher("dettaglio_articoloAdmin.jsp");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("errorPage.jsp");
				}

			}
			break;
			
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

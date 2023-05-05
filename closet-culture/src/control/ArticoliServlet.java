package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ArticoloBean;
import model.ArticoloDAO;
import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class RicercaArticoli
 */
@WebServlet("/ArticoliServlet")
public class ArticoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticoliServlet() {
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

		if (action != null) {
			ArticoloDAO pdao = new ArticoloDAO();
			if (action.equalsIgnoreCase("getArticoli")) {

				ArrayList<ArticoloBean> articoli = new ArrayList<ArticoloBean>();

				articoli = ArticoloDAO.ricerca("");

				request.setAttribute("articoli", articoli);

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);

			} else if (action.equalsIgnoreCase("getArtCat")) {

				ArrayList<ArticoloBean> articoli = new ArrayList<ArticoloBean>();

				articoli = ArticoloDAO.ricerca_per_categoria(request.getParameter("idCat"));

				request.setAttribute("articoli", articoli);

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
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

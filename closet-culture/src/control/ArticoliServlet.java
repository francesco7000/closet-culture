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
import model.TagliaBean;
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
		response.setContentType("text/html;charset=UTF-8");

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

				PrintWriter out = response.getWriter();
				
				for (ArticoloBean articolo : articoli) {
					
					out.print("<div class=\"product-item col-lg-3 col-md-6 col-sm-6\">\n" + 
							"  <div class=\"image-holder\">\n" + 
							"    <img src=\"images/selling-products1.jpg\" alt=\"Books\" class=\"product-image\">\n" + 
							"  </div>\n" + 
							"  <div class=\"cart-concern\">\n" + 
							"    <div class=\"cart-button d-flex justify-content-between align-items-center\">\n" + 
							"      <button type=\"button\" class=\"btn-wrap cart-link d-flex align-items-center\">\n" + 
							"        Aggiungi al Carrello <i class=\"icon icon-arrow-io\"></i>\n" + 
							"      </button>\n" + 
							"    </div>\n" + 
							"  </div>\n" + 
							"  <div class=\"product-detail\">\n" + 
							"    <h3 class=\"product-title\">\n" + 
							"      <a href=\"dettaglio_articolo.jsp?id="+ articolo.getId() + "\">" + articolo.getNome() +"</a>\n" + 
							"    </h3>\n" + 
							"    <div class=\"item-price text-primary\">€"+ articolo.getPrezzo() + "</div>\n" + 
							"  </div>\n" + 
							"</div>");
					
				}
				
				out.close();
			}
			else if(action.equalsIgnoreCase("getArticolo")) {
				

				int id = Integer.parseUnsignedInt(request.getParameter("id"));

				ArticoloBean articolo = ArticoloDAO.idRicerca(id);
				
				if(articolo != null) {
					
					request.setAttribute("articolo", articolo);

					RequestDispatcher dispatcher = request.getRequestDispatcher("dettaglio_articolo.jsp");
					dispatcher.forward(request, response);
				}
				else {
					response.sendRedirect("errorPage.jsp");
				}
			}else if(action.equalsIgnoreCase("getTaglia")) {
				ArrayList<TagliaBean> taglie = new ArrayList<TagliaBean>();
				taglie = ArticoloDAO.getTagliaByColore(request.getParameter("idcol"),request.getParameter("idart"));

				String idArticolo = request.getParameter("idart");
				String idColore = request.getParameter("idcol");
				PrintWriter out = response.getWriter();
				
				for (TagliaBean taglia : taglie) {

					out.print("<a href=\"#\" data-idart=\"" + idArticolo + "\" data-idcol=\"" + idColore + "\" class=\"taglia\" data-val=\"" + taglia.getId() + "\" data-id=\"" + taglia.getId() + "\"><li id=\"" + taglia.getId() + "\" class=\"select-item\">" + taglia.getNome() + "</li></a>");
				}
				
				
				out.close();
				
			}else {
				if(action.equalsIgnoreCase("getQt")) {
					System.out.println(action.equalsIgnoreCase("getQt"));
					System.out.println(request.getParameter("idt")+request.getParameter("idart")+request.getParameter("idcolore"));
					
				}
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

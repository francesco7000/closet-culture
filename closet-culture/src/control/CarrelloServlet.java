package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ArticoloBean;
import model.ArticoloDAO;
import model.CarrelloBean;
import model.CarrelloDao;
import model.ElementoCarrello;
import model.VarianteDAO;
import model.VariantiBean;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarrelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
		if (action != null) {

			if (action.equalsIgnoreCase("aggiungiAlCarrello")) {

				VariantiBean v = VarianteDAO.getVariante(request.getParameter("idart"), request.getParameter("idcol"),
						request.getParameter("idtaglia"));

				int qta = Integer.parseInt(request.getParameter("qta"));

				boolean result = CarrelloDao.addToCart(v, 1, qta);

			} else if (action.equalsIgnoreCase("getAll")) {

				CarrelloBean carrello = CarrelloDao.caricaCarrello(1);
				request.setAttribute("carrello", carrello);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
				dispatcher.forward(request, response);
			} else if (action.equalsIgnoreCase("removeVar")) {

				int idRow = Integer.parseInt(request.getParameter("idRow"));
				
				System.out.println(idRow);


				

				CarrelloDao.rimuoviVarianteDalCarrello(idRow, 1); //1 = admin
				
				CarrelloBean carrello = CarrelloDao.caricaCarrello(1);

				PrintWriter out = response.getWriter();

				int totale = 0;

				Map<Integer, ElementoCarrello> elementiCarrello = carrello.getCarrello();

				for (Map.Entry<Integer, ElementoCarrello> entry : elementiCarrello.entrySet()) {
					ElementoCarrello elemento = entry.getValue();
					VariantiBean variante = elemento.getVariante();
					ArticoloBean articolo = elemento.getArticolo();
					int quantita = elemento.getQuantita();

					totale += articolo.getPrezzo() * quantita;

					out.print("<div class=\"row\"> " + "        <div class=\"col-lg-4 col-md-3\">\n"
							+ "          <div class=\"row cart-info d-flex flex-wrap\">\n"
							+ "            <div class=\"col-lg-5\">\n" + "              <div class=\"card-image\">\n"
							+ "                <img src=\"images/selling-products6.jpg\" alt=\"cloth\" class=\"img-fluid\">\n"
							+ "              </div>\n" + "            </div>\n"
							+ "            <div class=\"col-lg-7\">\n" + "              <div class=\"card-detail\">\n"
							+ "                <h3 class=\"card-title\">\n" + "                  <a href=\"#\">"
							+ variante.getDescrizione() + "</a>\n" + "                </h3>\n"
							+ "                <div class=\"card-price\">\n"
							+ "                  <span class=\"money text-primary\" data-currency-usd=\""
							+ articolo.getPrezzo() + "\">$" + articolo.getPrezzo() + "</span>\n"
							+ "                </div>\n" + "              </div>\n" + "            </div>\n"
							+ "          </div>\n" + "        </div>\n" + "\n"
							+ "        <div class=\"col-lg-6 col-md-7\">\n" + "          <div class=\"row d-flex\">\n"
							+ "            <div class=\"col-md-6\">\n"
							+ "              <div class=\"qty-number d-flex align-items-center justify-content-start\">\n"
							+ "                <button class=\"decrement-button\">-</button>\n"
							+ "                <input type=\"text\" name=\"quantity\" class=\"spin-number-output\" value=\""
							+ quantita + "\" min=\"1\" max=\"100\">\n"
							+ "                <button class=\"increment-button\">+</button>\n"
							+ "              </div>\n" + "            </div>\n"
							+ "            <div class=\"col-md-4\">\n" + "              <div class=\"total-price\">\n"
							+ "                <span class=\"money text-primary\">$" + articolo.getPrezzo() * quantita
							+ "</span>\n" + "              </div>\n" + "            </div>   \n"
							+ "          </div>             \n" + "        </div>\n" + "\n"
							+ "        <div class=\"col-lg-1 col-md-2\">\n" + "          <div class=\"cart-remove\">\n"
							+ "            <a href=\"#\" class=\"eliminaVar\" data-id=\"" + entry.getKey()
							+ " \"><i class=\"icon icon-close\"></i></a>\n" + "          </div>\n" + "        </div>"
							+ "</div>" + "</div>");

				}

				out.close();

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

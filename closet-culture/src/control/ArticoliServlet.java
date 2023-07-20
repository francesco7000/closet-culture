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
			if (action.equalsIgnoreCase("getArticoli")) {

				ArrayList<ArticoloBean> articoli;

				articoli = ArticoloDAO.ricerca("");

				request.setAttribute("articoli", articoli);

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);

			} else if (action.equalsIgnoreCase("getArtCat")) {

				ArrayList<ArticoloBean> articoli;

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
							"      <a   href=\"ArticoliServlet?action=getArticolo&id="+articolo.getId()+"\" type=\"button\" class=\"btn-wrap cart-link d-flex align-items-center\">\n" + 
							"        Visualizza <i class=\"icon icon-arrow-io\"></i>\n" + 
							"      </a>\n" + 
							"    </div>\n" + 
							"  </div>\n" + 
							"  <div class=\"product-detail\">\n" + 
							"    <h3 class=\"product-title\">\n" + 
							"      <a href=\"ArticoliServlet?action=getArticolo&id="+ articolo.getId() + "\">" + articolo.getNome() +"</a>\n" + 
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
				ArrayList<TagliaBean> taglie;
				taglie = ArticoloDAO.getTagliaByColore(request.getParameter("idcol"),request.getParameter("idart"));

				String idArticolo = request.getParameter("idart");
				String idColore = request.getParameter("idcol");
				PrintWriter out = response.getWriter();
				
				for (TagliaBean taglia : taglie) {

					out.print("<li data-value=\"" + taglia.getNome() + "\" class=\"select-item\">");
					out.print("<a href=\"#\" id=\"taglia\" data-idart=\"" + idArticolo + "\" data-idcol=\"" + idColore + "\" class=\"taglia\" data-val=\"" + taglia.getId() + "\" data-id=\"" + taglia.getId() + "\">" + taglia.getNome() + "</a>");
					out.print("</li>");
				}
				
				
				out.close();
				
			}else {
				if(action.equalsIgnoreCase("getQt")) {
					int qta=ArticoloDAO.getQta(request.getParameter("idcolore"),request.getParameter("idart"),request.getParameter("idt"));
			
					PrintWriter out = response.getWriter();

				out.print( "<input type=\"number\" style=\"width:100px!Important\" id=\"quantity\" name=\"quantity\" class=\"spin-number-output\" value=\"1\" min=\"1\" max=\""+qta+"\">");
				
				}else if (action.equalsIgnoreCase("getRicercaArticoli")) {

					ArrayList<ArticoloBean> articoli;

					articoli = ArticoloDAO.ricerca("");

					request.setAttribute("articoli", articoli);

					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ricerca-prodotti.jsp");
					dispatcher.forward(request, response);

				} else if (action.equalsIgnoreCase("getArtCatRicArt")) {

					ArrayList<ArticoloBean> articoli;

					articoli = ArticoloDAO.ricerca_per_categoria(request.getParameter("idCat"));

					request.setAttribute("articoli", articoli);

					PrintWriter out = response.getWriter();
					
					for (ArticoloBean articolo : articoli) {
						
						
						out.print("\n" + 
								"                    <div class=\"product-item col-lg-4 col-md-6 col-sm-6\">\n" + 
								"                      <div class=\"image-holder\">\n" + 
								"                        <img src=\"images/selling-products1.jpg\" alt=\"Books\" class=\"product-image\">\n" + 
								"                      </div>\n" + 
								"                      <div class=\"cart-concern\">\n" + 
								"								<div\n" + 
								"									class=\"cart-button d-flex justify-content-between align-items-center\">\n" + 
								"								\n" + 
								"									<a href=\"ArticoliServlet?action=getArticolo&id="+articolo.getId()+"\" type=\"button\" \n" + 
								"										class=\"btn-wrap cart-link d-flex align-items-center\">\n" + 
								"										Visualizza <i class=\"icon icon-arrow-io\"></i>\n" + 
								"									</a>\n" + 
								"								\n" + 
								"								</div>\n" + 
								"							</div>\n" + 
								"                      <div class=\"product-detail\">\n" + 
								"                        <h3 class=\"product-title\">\n" + 
								"                          <a href=\"ArticoliServlet?action=getArticolo&id="+articolo.getId()+"\" >"+articolo.getNome()+"</a>\n" + 
								"                        </h3>\n" + 
								"                        <div class=\"item-price text-primary\">€"+articolo.getPrezzo()+"</div>\n" + 
								"                      </div>\n" + 
								"                    </div>\n" + 
								""
								);
						
						
					}
					
					out.close();
				}else {
					if (action.equalsIgnoreCase("getArtLineaRicArt")) {
						ArrayList<ArticoloBean> articoli;

						articoli = ArticoloDAO.ricerca_per_linea(request.getParameter("idlin"));

						request.setAttribute("articoli", articoli);

						PrintWriter out = response.getWriter();
						
						for (ArticoloBean articolo : articoli) {
							

							out.print("\n" + 
									"                    <div class=\"product-item col-lg-4 col-md-6 col-sm-6\">\n" + 
									"                      <div class=\"image-holder\">\n" + 
									"                        <img src=\"images/selling-products1.jpg\" alt=\"Books\" class=\"product-image\">\n" + 
									"                      </div>\n" + 
									"                      <div class=\"cart-concern\">\n" + 
									"								<div\n" + 
									"									class=\"cart-button d-flex justify-content-between align-items-center\">\n" + 
									"								\n" + 
									"									<a href=\"ArticoliServlet?action=getArticolo&id="+articolo.getId()+"\" type=\"button\" \n" + 
									"										class=\"btn-wrap cart-link d-flex align-items-center\">\n" + 
									"										Visualizza <i class=\"icon icon-arrow-io\"></i>\n" + 
									"									</a>\n" + 
									"								\n" + 
									"								</div>\n" + 
									"							</div>\n" + 
									"                      <div class=\"product-detail\">\n" + 
									"                        <h3 class=\"product-title\">\n" + 
									"                          <a href=\"ArticoliServlet?action=getArticolo&id="+articolo.getId()+"\" >"+articolo.getNome()+"</a>\n" + 
									"                        </h3>\n" + 
									"                        <div class=\"item-price text-primary\">€"+articolo.getPrezzo()+"</div>\n" + 
									"                      </div>\n" + 
									"                    </div>\n" + 
									""
									);
							
						}
						
						out.close();
				}					
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
		doGet(request, response);
	}

}

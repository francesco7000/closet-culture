package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ArticoloBean;
import model.ArticoloDAO;
import model.OrdineBean;
import model.OrdineDao;
import model.UserBean;
import model.UserDAO;

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
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
		if(request==null ) response.sendRedirect("errorPage.jsp");
		String action = request.getParameter("action");

		response.setContentType("text/html;charset=UTF-8");
		String admin="admin";
		
		HttpSession session = request.getSession(false);
		UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));
		if(currentUser==null) response.sendRedirect("errorPage.jsp"); // logged-in page

		
		if (action != null) {

			switch (action) {
			
			
			case "utente":
				if (currentUser != null && currentUser.getRuolo().equals(admin)) {
					session.setAttribute("sessionType", "utente");
					response.sendRedirect("home.jsp"); // logged-in page
				}
				break;

			case "admin":
				if (currentUser != null && currentUser.getRuolo().equals(admin)) {
					session.setAttribute("sessionType",admin);
					response.sendRedirect("home.jsp"); // logged-in page
				}
				break;

			case "getArticolo": {
				int id = Integer.parseUnsignedInt(request.getParameter("id"));

				ArticoloBean articolo = ArticoloDAO.idRicerca(id);

				if (articolo != null) {

					request.setAttribute("articolo", articolo);

					RequestDispatcher dispatcher = request
							.getRequestDispatcher("CaratteristicheServlet?action=getAllAdmin");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("errorPage.jsp");
				}

			}
				break;

			case "delArticolo": {
				int id = Integer.parseUnsignedInt(request.getParameter("idArt"));
				Boolean deleted = false;

				try {
					deleted = ArticoloDAO.eliminaArticolo(id);
				} catch (Exception e) {
					 				}
				if (deleted) {
					PrintWriter out = response.getWriter();
					out.print("articolo eliminato");

				} else {
					response.sendRedirect("errorPage.jsp");
				}

			}
				break;

			case "modifica": {

				int id = Integer.parseInt(request.getParameter("idArt"));
				boolean artActive = Boolean.parseBoolean(request.getParameter("artActive"));
				String artCod = request.getParameter("artCod");
				String artBarCod = request.getParameter("artBarCod");
				String artNome = request.getParameter("artNome");
				String artDescr = request.getParameter("artDescr");
				double artPrz = Double.parseDouble(request.getParameter("artPrz"));
				int artSconto = Integer.parseInt(request.getParameter("artSconto"));
				String artStag = request.getParameter("artStag");
				int artCat = Integer.parseInt(request.getParameter("artCat"));
				int artLin = Integer.parseInt(request.getParameter("artLin"));
				int artMat = Integer.parseInt(request.getParameter("artMat"));
				
				Boolean result = false;
				try {
					result = ArticoloDAO.modificaArticolo(id, artActive, artCod, artBarCod, artNome, artDescr, artPrz, artSconto, artStag, artCat, artLin, artMat);

					if(result) {
						response.sendRedirect("home.jsp");
					} else {
						response.sendRedirect("errorPage.jsp");
					}
				} catch (Exception e) {
				response.sendRedirect("errorPage.jsp");
				}


			}
				break;
				
			case "nuovoArt": {

				boolean artActive = Boolean.parseBoolean(request.getParameter("artActive"));
				String artCod = request.getParameter("artCod");
				String artBarCod = request.getParameter("artBarCod");
				String artNome = request.getParameter("artNome");
				String artDescr = request.getParameter("artDescr");
				double artPrz = Double.parseDouble(request.getParameter("artPrz"));
				int artSconto = Integer.parseInt(request.getParameter("artSconto"));
				String artStag = request.getParameter("artStag");
				int artCat = Integer.parseInt(request.getParameter("artCat"));
				int artLin = Integer.parseInt(request.getParameter("artLin"));
				int artMat = Integer.parseInt(request.getParameter("artMat"));
				
				Boolean result = false;
				

				try {
					
					result = ArticoloDAO.nuovoArticolo(artActive, artCod, artBarCod, artNome, artDescr, artPrz, artSconto, artStag, artCat, artLin, artMat);

					if(result) {
						response.sendRedirect("home.jsp");
					} else {
						response.sendRedirect("errorPage.jsp");
					}
				} catch (Exception e) {
					 					response.sendRedirect("errorPage.jsp");
				}


			}
				break;
				
			case "ricercaUtenti": {
				
				try {
					ArrayList<UserBean> users ;
					users = UserDAO.ricercautenti(request.getParameter("query"));
					
					PrintWriter out = response.getWriter();
					
					for (UserBean user : users) {
						out.print("<li><a href="+"UserServlet?action=getUtenteById&id="+user.getId()+">"+user.getUsername() +"</a></li>");
					}
					if(users.isEmpty()) {
						out.print("<li>Nessun utente trovato</li>");
					}
					
					out.close();

				} catch (Exception e) {
				response.sendRedirect("errorPage.jsp");
				}

				
			}
			break;
			
			case "ricercaOrdini": {
				
				try {
					ArrayList<OrdineBean> ordini ;
					ordini = OrdineDao.ricercaOrdini(request.getParameter("query"));
					
					PrintWriter out = response.getWriter();
					
					for (OrdineBean ordine : ordini) {
						out.print("<li><a href="+"GeneraFatturaDaOrdineServlet?id="+ordine.getId()+">"+"Numero Ordine: "+" "+ordine.getNumOrdine()+" "+"Totale: "+" "+ordine.getTotale()+"</a></li>");
					}
					if(ordini.isEmpty()) {
						out.print("<li>Nessun Ordine trovato</li>");
					}
					
					out.close();

				} catch (Exception e) {
				response.sendRedirect("errorPage.jsp");
				}


			}
			break;
			default:
				response.sendRedirect("errorPage.jsp"); 
			break;
				
			}

		}
		} catch (Exception e) {
			 			response.sendRedirect("errorPage.jsp");
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

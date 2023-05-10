package control;

import java.io.IOException;
import java.io.PrintWriter;

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

					RequestDispatcher dispatcher = request.getRequestDispatcher("CaratteristicheServlet?action=getAllAdmin");
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
					 System.out.println(deleted);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			

				if (deleted == true) {
					
					System.out.println("aaaaaa");
					
					PrintWriter out = response.getWriter();
					
					out.print("articolo eliminato");
					
				}
				else {
					response.sendRedirect("errorPage.jsp");
				}

			}
			break;
			
			case "modifica": {
				

		        boolean artActive = Boolean.parseBoolean(request.getParameter("artActive"));
				String artCod = request.getParameter("artCod");
		        String artBarCod = request.getParameter("artBarCod");
		        String artNome = request.getParameter("artNome");
		        String artDescr = request.getParameter("artDescr");
		        double artPrz = Double.parseDouble(request.getParameter("artPrz"));
		        int artSconto = Integer.parseInt(request.getParameter("artSconto"));
		        String artStag = request.getParameter("artStag");
		        String artCat = request.getParameter("artCat");
		        String artLin = request.getParameter("artLin");
		        String artMat = request.getParameter("artMat");
		        
		        

				
		        
				
				try {
					//to do
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			

				if (true) {
					
					 System.out.println("artActive: " + artActive);
				        System.out.println("artCod: " + artCod);
				        System.out.println("artBarCod: " + artBarCod);
				        System.out.println("artNome: " + artNome);
				        System.out.println("artDescr: " + artDescr);
				        System.out.println("artPrz: " + artPrz);
				        System.out.println("artSconto: " + artSconto);
				        System.out.println("artStag: " + artStag);
				        System.out.println("artCat: " + artCat);
				        System.out.println("artLin: " + artLin);
				        System.out.println("artMat: " + artMat);

					
				}
				else {
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

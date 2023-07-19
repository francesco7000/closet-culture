package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ArticoloDAO;
import model.CategoriaBean;
import model.CategoriaDao;
import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			if(action.equalsIgnoreCase("profilo")) {
		
				UserDAO userD = new UserDAO();

				UserBean us;
				
				us = userD.dettagli_profilo();

				request.setAttribute("profilo", us);
				RequestDispatcher dispatcher = request.getRequestDispatcher("modifica-profilo.jsp");
			    dispatcher.forward(request, response);
			}else {
					if(action.equalsIgnoreCase("modificaprofilo")) {
						HttpSession session = request.getSession();
						UserBean utente=(UserBean)session.getAttribute("currentSessionUser");
						Integer id=Integer.parseInt(request.getParameter("idUtente"));
						Integer idPersona=Integer.parseInt(request.getParameter("idPersona"));
						String nome = request.getParameter("nome");
						String cognome = request.getParameter("cognome");
						String email = request.getParameter("email");
						String cellulare = request.getParameter("cellulare");
						String via = request.getParameter("via");
						String provincia = request.getParameter("provincia");
						String citta = request.getParameter("citta");
						String cap = request.getParameter("cap");
						String numero = request.getParameter("numero");
						utente.setId(id);
						utente.setIdPersona(idPersona);
						utente.setNome(nome);
						utente.setCognome(cognome);
						utente.setEmail(email);
						utente.setCellulare(cellulare);
						utente.setVia(via);
						utente.setProvincia(provincia);
						utente.setCitta(citta);
						utente.setCap(cap);
						utente.setNumero(numero);
						System.out.println(utente.getIdPersona());
						try {
							UserDAO.updateUser(utente);
					    
							session.setAttribute("currentSessionUser",utente);
							response.sendRedirect("home.jsp");
						} catch (SQLException e) {
							response.sendRedirect("errorPage.jsp");
						}
					}else {
						if(action.equalsIgnoreCase("getUtenteById")) {


							UserBean us = new UserBean();
							us = UserDAO.utenteByID(Integer.parseInt(request.getParameter("id")));
							ServletContext context = request.getServletContext();
							context.setAttribute("userDaModificare", us);
							response.sendRedirect("dettaglioProfiloAdmin.jsp");
							
						}else {
							if(action.equalsIgnoreCase("modificaprofiloAdmin")) {
								
								UserBean utente=UserDAO.utenteByID(Integer.parseInt(request.getParameter("idUtente")));
								Integer id=Integer.parseInt(request.getParameter("idUtente"));
								Integer idPersona=Integer.parseInt(request.getParameter("idPersona"));
								String nome = request.getParameter("nome");
								String cognome = request.getParameter("cognome");
								String email = request.getParameter("email");
								String cellulare = request.getParameter("cellulare");
								String via = request.getParameter("via");
								String provincia = request.getParameter("provincia");
								String citta = request.getParameter("citta");
								String cap = request.getParameter("cap");
								String numero = request.getParameter("numero");
								utente.setId(id);
								utente.setIdPersona(idPersona);
								utente.setNome(nome);
								utente.setCognome(cognome);
								utente.setEmail(email);
								utente.setCellulare(cellulare);
								utente.setVia(via);
								utente.setProvincia(provincia);
								utente.setCitta(citta);
								utente.setCap(cap);
								utente.setNumero(numero);
								try {
									UserDAO.updateUser(utente);
									response.sendRedirect("home.jsp");
								} catch (SQLException e) {
									response.sendRedirect("errorPage.jsp");
								}
							}
						}
					}
				}
		
			
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

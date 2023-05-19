package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			if(action.equalsIgnoreCase("profilo")) {
		
				UserDAO userD = new UserDAO();

				UserBean us = new UserBean();
				
				us = userD.dettagli_profilo();
				System.out.println(us.getNome());

				request.setAttribute("profilo", us);
				RequestDispatcher dispatcher = request.getRequestDispatcher("modifica-profilo.jsp");
			    dispatcher.forward(request, response);
	
				
			
			}else {
				if(action.equalsIgnoreCase("nuovoprofilo")) {
				

				String nome = request.getParameter("nome");
				String cognome = request.getParameter("cognome");
				String email = request.getParameter("email");
				String cellulare = request.getParameter("cellulare");

				
				Boolean result = false;
				

				try {
					
					result = UserDao.nuovoprofilo(nome, cognome, email, cellulare);

					if(result) {
						response.sendRedirect("home.jsp");
					} else {
						response.sendRedirect("errorPage.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("errorPage.jsp");
				}
			}
			
		}
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	    

		     UserBean user =  new UserBean();
		     if(UserDAO.checkUsernameAvaiable(request.getParameter("un")) && UserDAO.checkEmailAvaiable(request.getParameter("email"))) {
		    	 
		    	 user.setUsername(request.getParameter("un"));
		    	 String pw = request.getParameter("pw");
		    	 String cpw = request.getParameter("cpw");
		    	 
		    	 if(pw.equals(cpw)) {
		    		 user.setPassword(request.getParameter("pw"));
		    		 user.setEmail(request.getParameter("email"));
		    		 
		    		 String cellulareString = request.getParameter("cellulare");
		    		 user.setCellulare(cellulareString);
		    		 
		    		 user.setNome(request.getParameter("nome"));
		    		 user.setCognome(request.getParameter("cognome"));
		    		 
		    		 user.setProvincia(request.getParameter("prov"));
		    		 user.setVia(request.getParameter("via"));
		    		 user.setCap(request.getParameter("cap"));
		    		 user.setCitta(request.getParameter("citta"));
		    		 user.setNumero(request.getParameter("num"));
		    		 
		    		 
		    		 user.setRuolo("utente");
		    		 
				     if(UserDAO.saveUser(user)) response.sendRedirect("authenticate.jsp");
				     else{
				    	 request.setAttribute("errorMessage", "Errore durante il salvataggio!");
				         RequestDispatcher dispatcher = request.getRequestDispatcher("authenticate.jsp");
				         dispatcher.forward(request, response);
				     }
		    		 
		    	 }
		    	 else {
		    		 request.setAttribute("errorMessage", "Le due password non corrispondono!");
			         RequestDispatcher dispatcher = request.getRequestDispatcher("authenticate.jsp");
			         dispatcher.forward(request, response);
		    	 }
		    	 
		     }
		     else {
		            request.setAttribute("errorMessage", "Username o Email non disponibile!");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("authenticate.jsp");
		            dispatcher.forward(request, response);
		     }
		         
		} 	
		catch (Throwable theException) 	    
		{
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

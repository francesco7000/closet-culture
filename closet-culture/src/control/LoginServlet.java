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
 * Servlet implementation class LoginServletV2
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{	    

		     UserBean user =  new UserBean();
		     user.setUsername(request.getParameter("un"));
		     user.setPassword(request.getParameter("pw"));
		     user = UserDAO.doRetrieve(user);
			   		    
		     if (user.isValid())
		     {
			        
		          HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentSessionUser",user); 
		          session.setAttribute("guest", false);
		          response.sendRedirect("home.jsp"); //logged-in page      		
		     }
			        
		     else {
		    	 //  response.sendRedirect("invalidLogin.jsp"); //error page 
		    	 
		    	// Imposta l'attributo di richiesta con il messaggio di errore
		            request.setAttribute("errorMessage", "Username o password non validi");
		            // Forward alla pagina di invalid.jsp
		            RequestDispatcher dispatcher = request.getRequestDispatcher("authenticate.jsp");
		            dispatcher.forward(request, response);
		     }
		         
		} 
				
				
		catch (Throwable theException) 	    
		{
		     System.out.println(theException); 
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

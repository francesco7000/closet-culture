package control;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/Articoli")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticoloDAO pdao = new ArticoloDAO();

		 String idProdotto = request.getParameter("id");
		 String idCategoria=request.getParameter("id_categoria");
		 
		 if(idCategoria!=null && !idCategoria.isEmpty()) {
			   // Call the ricerca method to get the list of matching articles
				Collection<ArticoloBean> matchingArticles = ArticoloDAO.ricerca_per_categoria(request.getParameter("id_categoria"));
			    // Set the response content type to JSON
			    if(matchingArticles.size()>0) {
		    	request.setAttribute("errorMessage", matchingArticles);
				request.setAttribute("prodotti", matchingArticles); 

		    	   RequestDispatcher dispatcher = request.getRequestDispatcher("ricerca-prodotti.jsp");
			       dispatcher.forward(request, response);
			    }else {
			    	request.setAttribute("errorMessage", "Articoli in Presenti");
			    	   RequestDispatcher dispatcher = request.getRequestDispatcher("ricerca-prodotti.jsp");
				         dispatcher.forward(request, response);
			    }
			 
		 }
		 
		 
		 
		 
		 
		 
	        if (idProdotto == null || idProdotto.isEmpty()) {
		try
		{	    

		    // Call the ricerca method to get the list of matching articles
			Collection<ArticoloBean> matchingArticles = ArticoloDAO.ricerca(request.getParameter("search"));
		    // Set the response content type to JSON
		    if(matchingArticles.size()>0) {
	    	request.setAttribute("errorMessage", matchingArticles);
			request.setAttribute("prodotti", matchingArticles); 

	    	   RequestDispatcher dispatcher = request.getRequestDispatcher("ricerca-prodotti.jsp");
		       dispatcher.forward(request, response);
		    }else {
		    	request.setAttribute("errorMessage", "Articoli in Presenti");
		    	   RequestDispatcher dispatcher = request.getRequestDispatcher("ricerca-prodotti.jsp");
			         dispatcher.forward(request, response);
		    }

		} catch (Throwable theException) {
		     System.out.println(theException); 
		}
	   }else {
		   ArticoloBean articolo =  ArticoloDAO.idRicerca(Integer.parseInt(idProdotto));
		   if(articolo!=null ) {
			   request.setAttribute("articolo", articolo); 
			   RequestDispatcher dispatcher = request.getRequestDispatcher("dettaglio_articolo.jsp");
		       dispatcher.forward(request, response);
		   }
		   else {
		    	request.setAttribute("errorMessage", "Articolo non Presente");
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("ricerca-prodotti.jsp");
			     dispatcher.forward(request, response);
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

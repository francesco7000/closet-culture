package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.CategoriaBean;
import model.CategoriaDao;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		if(action != null) {
			CategoriaDao catDao = new CategoriaDao();
			if(action.equalsIgnoreCase("getCategorie")) {
				
				
				ArrayList<CategoriaBean> categorie = new ArrayList<CategoriaBean>();
				
				categorie = catDao.doRetrieveAll();

				request.setAttribute("categorie", categorie);
			    
			    RequestDispatcher dispatcher = request.getRequestDispatcher("ArticoliServlet?action=getArticoli");
			    dispatcher.forward(request, response);
			
				
			}
			else if(action.equalsIgnoreCase("getCategoria")) {
					
					ArrayList<CategoriaBean> categorie = new ArrayList<CategoriaBean>();
					
					categorie = catDao.doRetrieveAll();

					request.setAttribute("categorie", categorie);
					
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("ArticoliServlet?action=getArtCat&idCat="+request.getParameter("idCat"));
				    dispatcher.forward(request, response);
					
			}else if (action.equalsIgnoreCase("getRicerca")){
				ArrayList<CategoriaBean> categorie = new ArrayList<CategoriaBean>();
				
				categorie = catDao.doRetrieveAll();

				request.setAttribute("categorie", categorie);
			    
			    RequestDispatcher dispatcher = request.getRequestDispatcher("CaratteristicheServlet?action=getLineeRicerca");
			    dispatcher.forward(request, response);
			
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

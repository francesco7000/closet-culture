package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoriaBean;
import model.CategoriaDao;
import model.LineaBean;
import model.LineaDao;
import model.MaterialeBean;
import model.MaterialeDao;

/**
 * Servlet implementation class CaratteristicheServlet
 */
@WebServlet("/CaratteristicheServlet")
public class CaratteristicheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaratteristicheServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action != null) {
			LineaDao linDao = new LineaDao();
			MaterialeDao matDao = new MaterialeDao();
			if(action.equalsIgnoreCase("getLineeRicerca")) {
				
				ArrayList<LineaBean> linee;
				
				linee = linDao.doRetrieveAll();

				request.setAttribute("linee", linee);
			    
			    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ArticoliServlet?action=getRicercaArticoli");
			    dispatcher.forward(request, response);
			
			}
			else if(action.equalsIgnoreCase("getAllAdmin")) {
				
				ArrayList<LineaBean> linee ;
				
				linee = linDao.doRetrieveAll();

				request.setAttribute("linee", linee);
				
				
				ArrayList<MaterialeBean> materiali;
				
				materiali = matDao.doRetrieveAll();

				request.setAttribute("materiali", materiali);
				
				
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/dettaglio_articoloAdmin.jsp");
			    dispatcher.forward(request, response);
			    
			    
			    
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

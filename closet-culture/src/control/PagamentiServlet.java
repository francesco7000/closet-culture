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
import model.PagamentoBean;
import model.PagamentoDao;

/**
 * Servlet implementation class Pagamenti
 */
@WebServlet("/PagamentiServlet")
public class PagamentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagamentiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action != null) {
			PagamentoDao pagDao = new PagamentoDao();
			if(action.equalsIgnoreCase("getTipiPag")) {
				
				
				ArrayList<PagamentoBean> tipiPag = new ArrayList<PagamentoBean>();
				
				tipiPag = PagamentoDao.chekTipiPagamentoDisponibili();
				

				request.setAttribute("tipiPag", tipiPag);
			    
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
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

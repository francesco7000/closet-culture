package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ArticoloBean;
import model.ArticoloDAO;
import model.CarrelloDao;
import model.VarianteDAO;
import model.VariantiBean;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		response.setContentType("text/html;charset=UTF-8");
		if (action != null) {
		
			if (action.equalsIgnoreCase("aggiungiAlCarrello")) {
			
				VariantiBean v=VarianteDAO.getVariante(request.getParameter("idart"), request.getParameter("idcol"), request.getParameter("idtaglia"));
			
				int qta=Integer.parseInt(request.getParameter("qta"));
				System.out.println("variante");
				System.out.println("PARSE***"+qta);
				boolean result=CarrelloDao.addToCart(v,1,qta);
				/*System.out.println("RISULTATO"+result);
				if(result) {
						response.sendRedirect("/closet-culture/cart.jsp");
				}*/
			
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

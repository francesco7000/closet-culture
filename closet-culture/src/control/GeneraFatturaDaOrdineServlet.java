package control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.logging.Logger;

import model.OrdineBean;
import model.OrdineDao;

/**
 * Servlet implementation class GeneraFatturaDaOrdine
 */
@WebServlet("/GeneraFatturaDaOrdineServlet")
public class GeneraFatturaDaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneraFatturaDaOrdineServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 final Logger logger = Logger.getLogger("MyLogger");

		int id=Integer.parseInt(request.getParameter("id"));
		try {
			OrdineBean ordine=OrdineDao.getOrdineById(id);
			String htmlContent=OrdineDao.generaFattura(ordine);

	        // Invia il contenuto HTML come risposta HTTP
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=\"fattura.pdf\"");
	        // Crea un nuovo documento PDF
	        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
	        Document document = new Document();
	        try {
	            PdfWriter writer = PdfWriter.getInstance(document, pdfStream);
	            document.open();

	            Paragraph paragraph = new Paragraph(htmlContent);
	            document.add(paragraph);

	            // Chiudi il documento PDF
	            document.close();
	            // Invia il PDF come risposta HTTP
	            response.setContentLength(pdfStream.size());
	            response.getOutputStream().write(pdfStream.toByteArray());
	            response.getOutputStream().flush(); 
	        } catch (DocumentException e) {
	            response.sendRedirect("errorPage.jsp");
	        }
	    
		} catch (SQLException e) {
			logger.log(null, "Eccezione non gestita: ");
		}
		
		
	}

}

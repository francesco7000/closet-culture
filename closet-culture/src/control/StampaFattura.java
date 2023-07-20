package control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class StampaFattura
 */
@WebServlet("/StampaFattura")
public class StampaFattura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StampaFattura() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

		String htmlContent=request.getParameter("fattura");
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
    
  }
}

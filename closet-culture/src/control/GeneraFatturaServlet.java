package control;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.itextpdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
@WebServlet("/GeneraFattura")
public class GeneraFatturaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String provincia=request.getParameter("provincia");
    	String citta=request.getParameter("citta");
    	String cap=request.getParameter("cap");
    	String via=request.getParameter("via");
    	String numero=request.getParameter("numero");
    
    	
        // Crea il contenuto HTML della fattura
        String htmlContent = "<html><head><title>Fattura</title></head><body><h1>Fattura</h1><p>Contenuto della fattura...</p></body></html>";
        // Invia il contenuto HTML come risposta HTTP
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"output.pdf\"");

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
            e.printStackTrace();
        }
    }

}
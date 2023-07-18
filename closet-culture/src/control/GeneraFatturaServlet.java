package control;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.itextpdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;

import model.CarrelloBean;
import model.CarrelloDao;
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
    	
    	String descrizionePagamento=request.getParameter("tipoPag");
    	String htmlContent="Fattura \n";
    	htmlContent+="--------------------------------------------- \n";
    	htmlContent+="Indirizzo di Spedizione:"+" "+provincia+" "+via+" "+numero+" "+cap+" "+citta+" "+"\n";
    	htmlContent+="---------------------------------------------"+"\n";
       	CarrelloBean carrello = CarrelloDao.caricaCarrello(1);
        // Crea il contenuto HTML della fattura
        htmlContent+= carrello.stringa()+"\n";
    	htmlContent+="Metodo di pagamento utilizzato:"+" "+descrizionePagamento+"\n";
    	HttpSession session = request.getSession();
		session.setAttribute("pdfFileName", htmlContent);
        CarrelloDao.svuotaCarrello(1);
        response.sendRedirect("thanksyou.jsp");
       
    }

}
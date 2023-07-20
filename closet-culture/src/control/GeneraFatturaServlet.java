package control;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

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

import model.ArticoloBean;
import model.CarrelloBean;
import model.CarrelloDao;
import model.ElementoCarrello;
import model.PagamentoDao;
import model.UserBean;
import model.VariantiBean;
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
    	HttpSession session = request.getSession();
		UserBean user=(UserBean) session.getAttribute("currentSessionUser");
    	String descrizionePagamento=request.getParameter("tipoPag");
    	String htmlContent="Fattura \n";
    	htmlContent+="--------------------------------------------- \n";
    	htmlContent+="Indirizzo di Spedizione:"+" "+provincia+" "+via+" "+numero+" "+cap+" "+citta+" "+"\n";
    	htmlContent+="---------------------------------------------"+"\n";
       	CarrelloBean carrello = CarrelloDao.caricaCarrello(user.getId());
       	String[] parts = descrizionePagamento.split("_");
       	String testo = parts[0];
       	int numeri = Integer.parseInt(parts[1]);
        htmlContent+= carrello.generaOrdine(numeri,user.getId())+"\n";
    	htmlContent+="Metodo di pagamento utilizzato:"+" "+testo+"\n";
    	
		session.setAttribute("pdfFileName", htmlContent);
        CarrelloDao.svuotaCarrello(user.getId());
        response.sendRedirect("thanksyou.jsp");
       
    }

}
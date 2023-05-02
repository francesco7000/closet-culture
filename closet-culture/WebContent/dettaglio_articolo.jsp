<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>

<%
// Recupera il valore dell'id del prodotto dalla query string
String idProdotto = request.getParameter("id");
ArticoloBean articolo=null;
if (idProdotto == null || idProdotto.isEmpty()) {
    // In questo caso, l'id del prodotto non è stato passato come parametro. Gestisci l'errore di conseguenza.
} else {
    // Converti l'id del prodotto in un intero
    int id = Integer.parseInt(idProdotto);
	if(request.getAttribute("articolo")!=null && request.getAttribute("articolo")!="" ){
		request.setAttribute("articolo", null);
	}else{
         articolo = ArticoloDAO.idRicerca(id);
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/dettaglio_articolo.css">

</head>
<body>

 <div class="product-details">
    <h1><%=articolo.getNome()%></h1>
    <img src="images/dett_art.jpeg" alt="Immagine dell'articolo" >
    <p>Descrizione dell'articolo:</p>
    <p><%=articolo.getDescrizione()%></p>
    <p>Prezzo: <%=articolo.getPrezzo()%></p>
    <form action="aggiungi_al_carrello.php" method="post">
        <label for="quantita">Quantità:</label>
        <input type="number" id="quantita" name="quantita" min="1" max="10" value="1">
        <br>
        <label for="colore">Colore:</label>
        <select id="colore" name="colore">
            <option value="rosso">Rosso</option>
            <option value="blu">Blu</option>
            <option value="verde">Verde</option>
        </select>
        <br>
        <label for="taglia">Taglia:</label>
        <select id="taglia" name="taglia">
            <option value="s">S</option>
            <option value="m">M</option>
            <option value="l">L</option>
            <option value="xl">XL</option>
        </select>
        <br>
        <input type="submit" value="Aggiungi al carrello">
    </form>
</div>
	  
	  <footer>
  <%@ include file = "fragments/footer.jsp" %>
  </footer>
</body>
</html>
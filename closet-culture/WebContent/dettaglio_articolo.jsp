<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/dettaglio_articolo.css">

</head>
<body>
 <div class="product-details">
    <h1>Nome dell'articolo</h1>
    <img src="images/dett_art.jpeg" alt="Immagine dell'articolo" >
    <p>Descrizione dell'articolo:</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla ac ante eget velit commodo vehicula. Nullam sollicitudin sapien eu purus commodo, ac lacinia libero gravida. Sed rhoncus turpis eget leo vehicula, sit amet mollis enim bibendum. Sed luctus, lectus ut hendrerit fermentum, sem nunc congue diam, ac blandit velit risus vel nibh. Aenean vestibulum nunc non enim ornare, in aliquet sapien sagittis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Praesent eu purus ac felis consequat bibendum.</p>
    <p>Prezzo: 50€</p>
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
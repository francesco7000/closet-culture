<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  import="java.util.*, model.*"%>

<%
	// Provo a prendere dalla request l'attributo prodotti:
	// Se � nullo, richiama la servlet Catalogo per farlo riempire, altrimenti procede alla generazione della pagina

	Collection<ArticoloBean> obj = (Collection<ArticoloBean>) request.getAttribute("prodotti");
	request.setAttribute("prodotti", null);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
     <!-- basic -->
         <link rel="stylesheet" href="css/ricerca-prodotti.css">
</head>


<body>
	
	<!--  <div id="custom-alert" class="alert-danger">
	<%
	if (request.getAttribute("errorMessage") != null) {
	%>
	<h2><%=request.getAttribute("errorMessage")%></h2>
	<%
	}
	%>-->
	</div>
    <h1>Ricerca articoli</h1>
    <form action="Articoli" method="post">
        <label for="search">Cerca articoli:</label>
        <input type="text" id="search" name="search">
        <button type="submit">Cerca</button>
    </form>
    <%
			// Il for crea una variabile del tipo ProdottoBean ed ad ogni iterazione va ad assegnare a quella variabile il contenuto di obj all'i-esima posizione 
			if (obj != null)
				for (ArticoloBean var : obj) {
					if (var.getVisibile() == 1) {
						continue;
					}
			%>
	
      <!-- banner bg main end -->
      <!-- fashion section start -->
     <main>
  <section class="featured-products">
      
  <h2>Prodotti in evidenza</h2>
  <div class="product-grid" >
    <div class="product">
      <img src="images/prodotto1.jpg" alt="Prodotto 1">
      <h3>Nome prodotto:      <%=var.getNome()%></h3>
      <p class="price">Prezzo:   <%=var.getPrezzo()%></p>
     <form action="dettaglio_articolo.jsp" method="get">
  <input type="hidden" name="id" value="<%=var.getId()%>">
  <button type="submit">Aggiungi al carrello</button>
</form>
    </div>
  </div>
  
</section>
</main>
      
        <%
				}
			%>
      
</body>
</html>
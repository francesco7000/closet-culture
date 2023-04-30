<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="model.UserBean"
   %>
<% 
// Check user credentials
  UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));
if ((currentUser==null)||(!currentUser.isValid()))
{	
    response.sendRedirect("./authenticate.jsp");
    return;
}

%>

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Negozio di abbigliamento - Home</title>
  <link rel="stylesheet" href="css/home.css">
</head>

<body>
  <header>
    <nav>
      <ul>
        <li><a href="index.jsp">Home</a></li>
        <li><a href="prodotti.jsp">Prodotti</a></li>
        <li><a href="contatti.jsp">Contatti</a></li>
      </ul>
    </nav>
  </header>
  <main>
    <div class="slideshow-container">
      <div class="mySlides">
        <img src="images/img1.jpg" alt="Slide 1">
      </div>

      <div class="mySlides">
        <img src="images/img3.jpg" alt="Slide 2">
      </div>

      <div class="mySlides">
        <img src="images/img3.jpg" alt="Slide 3">
      </div>
    </div>

    <div class="dot-container">
      <span class="dot" onclick="currentSlide(1)"></span>
      <span class="dot" onclick="currentSlide(2)"></span>
      <span class="dot" onclick="currentSlide(3)"></span>
    </div>

<section class="featured-products">
  <h2>Prodotti in evidenza</h2>
  <div class="product-grid">
    <div class="product">
      <img src="images/prodotto1.jpg" alt="Prodotto 1">
      <h3>Nome prodotto 1</h3>
      <p class="price">Prezzo: €39,99</p>
      <button>Aggiungi al carrello</button>
    </div>
    <div class="product">
      <img src="images/prodotto1.jpg" alt="Prodotto 2">
      <h3>Nome prodotto 2</h3>
      <p class="price">Prezzo: €49,99</p>
      <button>Aggiungi al carrello</button>
    </div>
    <div class="product">
      <img src="images/prodotto1.jpg" alt="Prodotto 3">
      <h3>Nome prodotto 3</h3>
      <p class="price">Prezzo: €29,99</p>
      <button>Aggiungi al carrello</button>
    </div>
        <div class="product">
      <img src="images/prodotto1.jpg" alt="Prodotto 3">
      <h3>Nome prodotto 3</h3>
      <p class="price">Prezzo: €29,99</p>
      <button>Aggiungi al carrello</button>
    </div>
        <div class="product">
      <img src="images/prodotto1.jpg" alt="Prodotto 3">
      <h3>Nome prodotto 3</h3>
      <p class="price">Prezzo: €29,99</p>
      <button>Aggiungi al carrello</button>
    </div>
        <div class="product">
      <img src="images/prodotto1.jpg" alt="Prodotto 3">
      <h3>Nome prodotto 3</h3>
      <p class="price">Prezzo: €29,99</p>
      <button>Aggiungi al carrello</button>
    </div>
  </div>
</section>
  </main>
  
  <footer class="footer">
  <%@ include file = "fragments/footer.jsp" %>
  </footer>
  <!--  
  <footer>
    <p>&copy; 2023 - Negozio di abbigliamento. Tutti i diritti riservati.</p>
  </footer>
  -->

 <script src="scripts/home.js"></script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*,model.*" %>

    
    <%    
   CarrelloBean carrello = (CarrelloBean) request.getAttribute("carrello");
    
    if(carrello == null){
    	
    	response.sendRedirect("CarrelloServlet?action=getAll");
    	
    }
    
    %>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Closet Culture | Carrello</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="icomoon/icomoon.css">
    <link rel="stylesheet" type="text/css" media="all" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/vendor.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <!-- script
    ================================================== -->
    <script src="js/modernizr.js"></script>
  </head>
  <body>
	<%@ include file="fragments/header.jsp"%>

    <section class="site-banner padding-small bg-light-grey">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="breadcrumbs">
              <span class="item">
                <a href="home.jsp">Home /</a>
              </span>
              <span class="item">
                <a href="ricerca-prodotti.jsp">Shop /</a>
              </span>
              <span class="item">Cart</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="shopify-cart padding-large">
      <div class="container">
        <div class="cart-table">
          <div class="cart-header border-bottom ">
            <div class="row d-flex">
              <h3 class="cart-title col-lg-4">Prodotti</h3>
              <h3 class="cart-title col-lg-3">Quantità</h3>
              <h3 class="cart-title col-lg-4">Subtotal</h3>
            </div>
          </div>	
        	      
        	  
          
        
        <% if(carrello != null){ 
    Map<Map<VariantiBean, ArticoloBean>, Integer> varianteCarrello = carrello.getCarrello();
    
    for (Map.Entry<Map<VariantiBean, ArticoloBean>, Integer> entry : varianteCarrello.entrySet()) {
        Map<VariantiBean, ArticoloBean> elemento = entry.getKey();
        VariantiBean variante = null;
        ArticoloBean articolo = null;
        int quantita = entry.getValue();
        for (Map.Entry<VariantiBean, ArticoloBean> entryElemento : elemento.entrySet()) {
            variante = entryElemento.getKey();
            articolo = entryElemento.getValue();
        }
%>
    <div class="cart-item border-bottom padding-small">
      <div class="row"> 

        <div class="col-lg-4 col-md-3">
          <div class="row cart-info d-flex flex-wrap">
            <div class="col-lg-5">
              <div class="card-image">
                <img src="images/selling-products6.jpg" alt="cloth" class="img-fluid">
              </div>
            </div>
            <div class="col-lg-7">
              <div class="card-detail">
                <h3 class="card-title">
                  <a href="#"><%= variante.getDescrizione() %></a>
                </h3>
                <div class="card-price">
                  <span class="money text-primary" data-currency-usd="<%= articolo.getPrezzo() %>">$<%= articolo.getPrezzo() %></span>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-lg-6 col-md-7">
          <div class="row d-flex">
            <div class="col-md-6">
              <div class="qty-number d-flex align-items-center justify-content-start">
                <button class="decrement-button">-</button>
                <input type="text" name="quantity" class="spin-number-output" value="<%= quantita %>" min="1" max="100">
                <button class="increment-button">+</button>
              </div>
            </div>
            <div class="col-md-4">
              <div class="total-price">
                <span class="money text-primary">$<%= articolo.getPrezzo() * quantita %></span>
              </div>
            </div>   
          </div>             
        </div>

        <div class="col-lg-1 col-md-2">
          <div class="cart-remove">
            <a href="CarrelloServlet?action=removeVar&id_var=<%= variante.getId()%>"><i class="icon icon-close"></i></a>
          </div>
        </div>

      </div>
    </div>
<% 
    }
} 
%>
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
          
        </div>
        <div class="cart-totals">
          <h2 class="section-title">Totale Carrello</h2>
          <div class="total-price">
            <table cellspacing="0" class="table">
              <tbody>
                <tr class="subtotal">
                  <th>Subtotal</th>
                  <td data-title="Subtotal">
                    <span class="price-amount amount text-primary">
                      <bdi>
                        <span class="price-currency-symbol">$</span>2,370.00
                      </bdi>
                    </span>
                  </td>
                </tr>
                <tr class="order-total">
                  <th>Totale</th>
                  <td data-title="Total">
                    <span class="price-amount amount text-primary">
                      <bdi>
                        <span class="price-currency-symbol">$</span>2,370.00</bdi>
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="button-wrap">
            <a href="cart.jsp"><button class="btn btn-dark btn-medium">Aggiorna Carrello</button></a>
            <a href="ricerca-prodotti.jsp"><button class="btn btn-dark btn-medium">Continua Acquisti</button></a>
            <a href="checkout.jsp"><button class="btn btn-dark btn-medium">Procedi al CheckOut</button></a>
          </div>
        </div>
      </div>
    </section>




	<section id="shipping-information">
		<hr>
		<div class="container">
			<div
				class="row d-flex flex-wrap align-items-center justify-content-between">
				<div class="col-md-3 col-sm-6">
					<div class="icon-box">
						<i class="icon icon-truck"></i>
						<h4 class="block-title">
							<strong>Spedizione</strong> in 2/3 giorni lavorativi
						</h4>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="icon-box">
						<i class="icon icon-return"></i>
						<h4 class="block-title">
							<strong>Rimborso</strong> in giorni lavorativi
						</h4>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="icon-box">
						<i class="icon icon-tags1"></i>
						<h4 class="block-title">
							<strong>Acquista i nostri prodotti</strong> migliori
						</h4>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="icon-box">
						<i class="icon icon-help_outline"></i>
						<h4 class="block-title">
							<strong> Hai altre domande?</strong> contatta un esperto
						</h4>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</section>

	<%@ include file="fragments/footer.jsp"%>

	<%@ include file="fragments/miniFooter.jsp"%>

    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>
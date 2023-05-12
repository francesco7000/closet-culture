<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>

<%

ArticoloBean articolo = (ArticoloBean) request.getAttribute("articolo");

	if(articolo == null ){
		response.sendRedirect("errorPage.jsp");
	}else{
		//reset articolo
		request.setAttribute("articolo", null);	
	}
%>



<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Closet Culture</title>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <script>
$(document).ready(function() {
	  // Aggiungi un evento di click ai link delle categorie
	  $('.colore').click(function(event) {
	    event.preventDefault(); // Impedisce al browser di seguire il link

	    // Ottieni l'ID della categoria dal data-id dell'elemento
	    var idColore = $(this).data('id');
	    var idArticolo = $(this).data('idart');
	    console.log('ID articolo:', idArticolo);

	    // Invia la richiesta AJAX al server
	    $.ajax({
	      type: 'GET',
	      url: 'ArticoliServlet',
	      data: { action: 'getTaglia', idcol: idColore,idart:idArticolo},
	      success: function(data) {
	        // Aggiorna il contenuto della sezione dei prodotti
	        $('#taglieAjax').html(data);
	      }
	    });
	  });
	});

</script>
<script>
$(document).ready(function() {
	  // Aggiungi un evento di click ai link delle taglie
	  $('#taglie-container').on('click', '.taglia', function(event) {
	    event.preventDefault(); // Impedisce al browser di seguire il link
	    
	    // Ottieni l'ID della categoria dal data-id dell'elemento
	    var idtaglia = $(this).data('id');
	    var idArticolo = $(this).attr('data-idart');
	    var idcol =  $(this).attr('data-idcol');
	    // Invia la richiesta AJAX al server


	    $.ajax({
	      type: 'GET',
	      url: 'ArticoliServlet',
	      data: { action: 'getQt', idt: idtaglia, idart: idArticolo, idcolore: idcol },
	      success: function(data) {
	        // Aggiorna il contenuto della sezione dei prodotti
	        $('#qtAjax').html(data);
	      }
	    });
	  });
	});
</script>


    <script >
      var initIncrementDecrement = function() {
        const decrementButton = document.querySelector('.decrement-button');
        const incrementButton = document.querySelector('.increment-button');
        const quantityInput = document.querySelector('#quantity');

        decrementButton.addEventListener('click', () => {
          if (quantityInput.value > 1) {
            quantityInput.value--;
          }
        });

        incrementButton.addEventListener('click', () => {
          if (quantityInput.value < 100) {
            quantityInput.value++;
          }
        });
      }
      initIncrementDecrement();
    </script>
  </head>
  <%@ include file="fragments/header.jsp"%>
  <body>
    <section class="site-banner padding-small bg-light-grey">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="breadcrumbs">
              <span class="item">
                <a href="index.html">Home /</a>
              </span>
              <span class="item">
                <a href="shop.html">Shop /</a>
              </span>
              <span class="item">Shop Single</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="single-product padding-large">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <div class="row product-preview">
              <div thumbsSlider="" class="swiper thumb-swiper col-md-3 col-xs-3">
                <div class="swiper-wrapper d-flex flex-wrap">
                  <div class="swiper-slide">
                    <img src="images/product-thumbnail-1.jpg" alt="">
                  </div>
                  <div class="swiper-slide">
                    <img src="images/product-thumbnail-2.jpg" alt="">
                  </div>
                  <div class="swiper-slide">
                    <img src="images/product-thumbnail-3.jpg" alt="">
                  </div>
                </div>
              </div>
              <div class="swiper large-swiper overflow-hidden col-md-9 col-xs-9">
                <div class="swiper-wrapper">
                  <div class="swiper-slide">
                    <img src="images/product-large-1.jpg" alt="single-product">
                  </div>
                  <div class="swiper-slide">
                    <img src="images/product-large-2.jpg" alt="single-product">
                  </div>
                  <div class="swiper-slide">
                    <img src="images/product-large-3.jpg" alt="single-product">
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="product-info">
              <div class="element-header">
                <h2 itemprop="name" class="product-title"><%=articolo.getNome()%></h2>
                <div class="rating-container d-flex align-items-center">
                  <div class="rating" data-rating="1" onclick=rate(1)>
                    <i class="icon icon-star-full"></i>
                  </div>
                  <div class="rating" data-rating="2" onclick=rate(1)>
                    <i class="icon icon-star-full"></i>
                  </div>
                  <div class="rating" data-rating="3" onclick=rate(1)>
                    <i class="icon icon-star-full"></i>
                  </div>
                  <div class="rating" data-rating="4" onclick=rate(1)>
                    <i class="icon icon-star-half"></i>
                  </div>
                  <div class="rating" data-rating="5" onclick=rate(1)>
                    <i class="icon icon-star-empty"></i>
                  </div>
                  <span class="rating-count">(3.5)</span>
                </div>
              </div>
              <div class="product-price">
                <strong><%=articolo.getPrezzo()%>€</strong>
                <!-- <del>$54.00</del> -->
              </div>
              <p><%=articolo.getDescrizione()%></p>

              <div class="cart-wrap margin-small">
                <div class="color-options product-select">
                  <div class="color-toggle" data-option-index="0">
                    <h4 class="item-title no-margin">Color:</h4>
                    <ul class="select-list list-unstyled d-flex">
                    
   
				<%
					if (articolo != null) {
						
						for (ColoreBean colore : articolo.getListaColori()) {
							if(colore!=null)
				%>
				
				
				
				<a href="#" class="colore" data-val="<%=colore.getId()%>" data-idart="<%=articolo.getId()%>" data-id="<%=colore.getId()%>"><li
					id="<%=colore.getId()%>" class="select-item"><%=colore.getNome()%></li></a>
			
								
				<%
					}
					}
				%>
                    
                     
                      
                      
                      
                    </ul>
                    
                    
                  </div>
                </div>
                
                
                <div class="swatch product-select" data-option-index="1">
                  <h4 class="item-title no-margin">Size:</h4>
                  <div id="taglie-container">
                 
                  <ul class="select-list list-unstyled d-flex" id="taglieAjax">
                   <!-- <li data-value="S" class="select-item">
                      <a href="#">S</a>
                    </li>
                    <li data-value="M" class="select-item">
                      <a href="#">M</a>
                    </li>
                    <li data-value="L" class="select-item">
                      <a href="#">L</a>
                    </li>-->
                  </ul> 
                </div>
                </div> 
                 <div class="product-quantity">
                  <div class="item-title">
                    <strong>2 in stock</strong>
                  </div>
                  <div class="stock-button-wrap">
                    <div class="product-quantity d-flex align-items-center">
                      <h4 class="item-title no-margin">Quantity:</h4>                
                      <div class="qty-field d-flex">
                        <div class="qty-number d-flex justify-content-start align-items-center">
                            <button class="decrement-button">-</button>
                            <div id="qtAjax">
                              <input type="text" id="quantity" name="quantity" class="spin-number-output" value="1" min="1" max="100">
                            
                            </div>
                            <button class="increment-button">+</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="action-buttons">
                  <button type="submit" class="btn btn-medium btn-dark">Buy now</button>
                  <button type="submit" name="add" id="add-to-cart" class="btn btn-medium btn-dark">
                    <span id="add-to-cart">Aggiungi al Carrello</span>
                  </button>
                  <button type="submit" class="btn btn-medium btn-dark"><i class="icon icon-heart"></i></button>
                </div>
              </div>
              <div class="meta-product margin-small">
                <div class="meta-item d-flex flex-wrap align-items-baseline">
                  <h4 class="item-title no-margin">Barcode:</h4>
                  <ul class="select-list list-unstyled d-flex">
                    <li data-value="S" class="select-item"><%=articolo.getBarcode()%></li>
                  </ul>
                </div>
                <div class="meta-item d-flex flex-wrap align-items-baseline">
                  <h4 class="item-title no-margin">Composizione:</h4>
                  <ul class="select-list list-unstyled d-flex">
                    <li data-value="S" class="select-item">
                      <a href="#" class="select-item"><%=articolo.getComposizione()%></a>
                    </li>
                  
                  </ul>
                </div>
                <div class="meta-item d-flex flex-wrap align-items-baseline">
                  <h4 class="item-title no-margin">Stagione:</h4>
                  <ul class="select-list list-unstyled d-flex">
                    <li data-value="S" class="select-item">
                      <a href="#"><%=articolo.getStagione()%></a>
                    </li>
                   
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="product-tabs">
      <div class="container">
        <div class="row">
          <div class="tabs-listing">
            <nav>
              <div class="nav nav-tabs d-flex justify-content-center" id="nav-tab" role="tablist">
                <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">Come nascono i nostri prodotti</button>
                <button class="nav-link" id="nav-shipping-tab" data-bs-toggle="tab" data-bs-target="#nav-shipping" type="button" role="tab" aria-controls="nav-shipping" aria-selected="false">Spedizione e Resi</button>
              </div>
            </nav>
            <div class="tab-content" id="nav-tabContent">
              <div class="tab-pane fade active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
              <p>
              
              
              I prodotti di Closet Culture nascono attraverso un processo che comprende diversi step, come la progettazione, la produzione e la distribuzione.

Innanzitutto, la progettazione del prodotto inizia con l'ideazione del concept e lo sviluppo di bozzetti e schizzi che rappresentano l'aspetto e lo stile del capo d'abbigliamento. In questa fase vengono prese in considerazione le tendenze di moda, le preferenze dei clienti e le possibili sfide tecniche che potrebbero sorgere durante la produzione.

Successivamente, viene avviata la produzione del capo d'abbigliamento. Questa fase può variare a seconda del tipo di prodotto e del processo produttivo utilizzato, ma di solito include la selezione dei tessuti e dei materiali, la realizzazione dei prototipi e dei campioni e la produzione in massa.

Infine, i prodotti finiti vengono distribuiti tramite il tuo sito di abbigliamento e altri canali di vendita, come i negozi fisici o altri siti di e-commerce.

In tutto questo processo, la qualità e l'attenzione ai dettagli sono fondamentali per creare prodotti che soddisfino le esigenze e i gusti dei tuoi clienti e che si distinguano sul mercato.
              </p>
              </div>
            
              <div class="tab-pane fade" id="nav-shipping" role="tabpanel" aria-labelledby="nav-shipping-tab">
                <p>
                Le consegne avvengo dal lunedì al venerdì.

				Successivamente alla ricezione dell’ordine i tempi di elaborazione dell’ordine variano dalle 24 alle 48 ore.

				La consegna avviene in circa  1-2 giorni lavorativi.

				Durante il periodo di promozioni e festività le tempistiche potranno subire dei ritardi.

				Gli ordini saranno consegnati all'indirizzo di spedizione specificato dall'Utente. La data della consegna è da intendersi come indicativa e non vincolante per Closet Culture.

				I clienti effettuando un ordine stabiliscono un rapporto commerciale con Closet Culture e si impegnano ad accettare la consegna del pacco.

				Qualora un pacco non venga recapitato  per incompletezza o errata compilazione dell’indirizzo/numero di telefono/destinatario o altri dati forniti dall'utente o nel caso in cui la spedizione venga rifiutata, il pacco verrà restituito a IN-CON-TRO a carico del cliente comprese eventuali spese ed oneri doganali, queste spese saranno dedotte dal rimborso dovuto.
	
				Le spese di spedizione saranno a carico del cliente e variano in base al luogo della consegna.

				La consegna gratuita è prevista solo tramite il raggiungimento delle soglie spesa.

				Sotto sono indicate le spese di spedizione divise per paese e le relative soglie spesa.
			   </p>
           
              </div>
        
            </div>
          </div>
        </div>
      </div>
    </section>
    
   
				<%
					if (articolo != null) {
						
						for (VariantiBean variante : articolo.getListaVarianti()) {
							if(variante!=null)
				%>
				<p><%=variante.getDescrizione()%></p>
				<%
					}
					}
				%>



    <section id="brand-collection" class="padding-medium bg-light-grey">
      <div class="container">
        <div class="d-flex flex-wrap justify-content-between">
          <img src="images/brand1.png" alt="phone" class="brand-image">
          <img src="images/brand2.png" alt="phone" class="brand-image">
          <img src="images/brand3.png" alt="phone" class="brand-image">
          <img src="images/brand4.png" alt="phone" class="brand-image">
          <img src="images/brand5.png" alt="phone" class="brand-image">
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

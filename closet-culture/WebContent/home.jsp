<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,model.*"%>


<%
	UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));
	Boolean adminMode = false;
	
	
	//currentUser nullo o non valido allora accesso come ospite
	if ((currentUser == null) || (!currentUser.isValid())) {
		session.setAttribute("guest", true);

	}
	//altrimenti controllo se l'utente loggato è admin e che tipo di visualizzazione vuole
	else if(currentUser.getRuolo().equals("admin")){
			String sessionType = (String) session.getAttribute("sessionType");
			if(sessionType != null && sessionType.equals("admin")){
				adminMode = true;
			}
		
	}


	//cerco di ottenere le categorie
	ArrayList<CategoriaBean> categorie = (ArrayList<CategoriaBean>) request.getAttribute("categorie");

	//se le categorie sono vuote allora chiamo la servlet per ottenerle
	if (categorie == null) {
		//redirect alla servlet come parametro getCategorie per dirgli cosa deve fare
		response.sendRedirect("CategoriaServlet?action=getCategorie");
	} else {
		ServletContext context = request.getServletContext();
		context.setAttribute("categorie", categorie);
	}

	//cerco di ottenere tutti gli articoli
	ArrayList<ArticoloBean> articoli = (ArrayList<ArticoloBean>) request.getAttribute("articoli");

	//reset attributo per le prossime chiamate
	request.setAttribute("articoli", null);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Closet Culture | Homepage</title>
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
<link rel="stylesheet" type="text/css" media="all"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/vendor.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">
<!-- script
    ================================================== -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/modernizr.js"></script>

<script>
$(document).ready(function() {
	  // Aggiungi un evento di click ai link delle categorie
	  $('.categoria').click(function(event) {
	    event.preventDefault(); // Impedisce al browser di seguire il link

	    // Ottieni l'ID della categoria dal data-id dell'elemento
	    var idCategoria = $(this).data('id');

	    // Invia la richiesta AJAX al server
	    $.ajax({
	      type: 'GET',
	      url: 'ArticoliServlet',
	      data: { action: 'getArtCat', idCat: idCategoria },
	      success: function(data) {
	        // Aggiorna il contenuto della sezione dei prodotti
	        $('#articoliAjax').html(data);
	      }
	    });
	  });
	});
</script>



</head>
<%@ include file="fragments/header.jsp"%>

<body>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<section id="billboard" class="overflow-hidden">

		<button class="button-prev">
			<i class="icon icon-chevron-left"></i>
		</button>
		<button class="button-next">
			<i class="icon icon-chevron-right"></i>
		</button>
		<div class="swiper main-swiper">
			<div class="swiper-wrapper">
				<div class="swiper-slide"
					style="background-image: url('images/banner1.jpg'); background-repeat: no-repeat; background-size: cover; background-position: center;">
					<div class="banner-content">
						<div class="container">
							<div class="row">
								<div class="col-md-6">
									<h2 class="banner-title">Summer Collection</h2>
									<p>Esplora il nostro catalogo. Imparerai a conoscere tutto
										il meglio della nostra collezione estiva</p>
									<div class="btn-wrap">
										<a href="ricerca-prodotti.jsp"
											class="btn btn-light btn-medium d-flex align-items-center"
											tabindex="0">Acquista Ora <i class="icon icon-arrow-io"></i>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="swiper-slide"
					style="background-image: url('images/banner2.jpg'); background-repeat: no-repeat; background-size: cover; background-position: center;">
					<div class="banner-content">
						<div class="container">
							<div class="row">
								<div class="col-md-6">
									<h2 class="banner-title">Casual Collection</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
										Sed eu feugiat amet, libero ipsum enim pharetra hac.</p>
									<div class="btn-wrap">
										<a href="ricerca-prodotti.jsp"
											class="btn btn-light btn-light-arrow btn-medium d-flex align-items-center"
											tabindex="0">Acquista Ora<i class="icon icon-arrow-io"></i>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>



	<section id="selling-products"
		class="product-store bg-light-grey padding-large">
		<div class="container">
			<div class="section-header">
				<h2 class="section-title">Prodotti</h2>
			</div>
			<ul class="tabs list-unstyled">


				<%
					if (categorie != null) {
						for (CategoriaBean categoria : categorie) {
				%>

				<a href="#" class="categoria" data-id="<%=categoria.getId()%>"><li
					id="<%=categoria.getId()%>" class="tab categoria"><%=categoria.getDescrizione()%></li></a>
				<%
					}
					}
				%>

			</ul>

			<div class="tab-content">
				<div data-tab-content class="active">
					<div id="articoliAjax" class="row d-flex flex-wrap">

						<%
							if (articoli != null) {
								for (ArticoloBean articolo : articoli) {
						%>
						<div class="product-item col-lg-3 col-md-6 col-sm-6">
							<div class="image-holder">
								<img src="images/selling-products1.jpg" alt="Books"
									class="product-image">
							</div>
							<div class="cart-concern">
								<div
									class="cart-button d-flex justify-content-between align-items-center">
								<% 	if(!adminMode){ %>
									<a href="ArticoliServlet?action=getArticolo&id=<%=articolo.getId()%>" type="button" 
										class="btn-wrap cart-link d-flex align-items-center">
										Visualizza <i class="icon icon-arrow-io"></i>
									</a>
									<% } else { %>
									<a href="AdminServlet?action=getArticolo&id=<%= articolo.getId() %>" type="button" 
										class="btn-wrap cart-link d-flex align-items-center">
										Modifica <i class="icon icon-arrow-io"></i>
									</a>
									<% } %>
								</div>
							</div>
							<div class="product-detail">
								<h3 class="product-title">
									<a href="ArticoliServlet?action=getArticolo&id=<%=articolo.getId()%>"><%=articolo.getNome()%></a>
								</h3>
								<div class="item-price text-primary">
									€
									<%=articolo.getPrezzo()%></div>
							</div>
						</div>
						<%
							}
							}
						%>
						
					</div>
				</div>
			</div>
		</div>
	</section>


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
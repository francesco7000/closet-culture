<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.*"%>

<%
//cerco di ottenere le categorie
UserBean user = (UserBean) request.getAttribute("profilo");
//se le categorie sono vuote allora chiamo la servlet per ottenerle
if (user == null) {
	//redirect alla servlet come parametro getCategorie per dirgli cosa deve fare
	response.sendRedirect("CategoriaServlet?action=getRicerca");
} else {
	ServletContext context = request.getServletContext();
	context.setAttribute("profilo", user);
}

%>



<!DOCTYPE html>
<html lang="en">
<head>
<title>Modifica Profilo</title>
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
<script src="js/modernizr.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	$(document).ready(function() {
		// Aggiungi un evento di click ai link delle categorie
		$('#elimina').click(function(event) {
			event.preventDefault(); // Impedisce al browser di seguire il link

			// Ottieni l'ID della categoria dal data-id dell'elemento
			var idArt = $(this).data('id');

			// Invia la richiesta AJAX al server
			$.ajax({
				type : 'GET',
				url : 'AdminServlet?action=delArticolo&idArt=' + idArt,
				success : function(data) {
					window.location.href = "home.jsp";

				},
				error : function(jqXHR, textStatus, errorThrown) {
					window.location.href = "errorPage.jsp";
				}
			});
		});
	});
</script>




</head>
<%@ include file="fragments/header.jsp"%>
<body>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<section class="site-banner padding-small bg-light-grey">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumbs">
						<span class="item"> <a href="home.jsp">Home /</a>
						</span> <span class="item"> <a href="ricerca-prodotti.jsp">Shop
								/</a>
						</span> <span class="item">Mio Profilo</span>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="single-product padding-large">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
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
				<div class="col-md-7">
					<div class="product-info">
						<div class="element-header">
							<div class="tab-pane fade" id="nav-register" role="tabpanel"
								aria-labelledby="nav-register-tab">
								<form method="post" action="AdminServlet">

									
									<div class="form-group">
										<label for="artActive">Nome &nbsp; <input
											type="checkbox" name="artActive" value="true"
											checked>
										</label>
									</div>

									<div class="row">
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artCod">Email</label> <input
													type="text" name="artCod" required
											>
											</div>
										</div>
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artBarCod">Barcode Articolo</label> <input
													type="text" name="artBarCod"
													class="u-medium-width bg-light mx-2" required
													>
											</div>
										</div>
										<div class="col-md-4 col-sm-12">

											<div class="form-group">
												<label for="artQta">Quantità Articolo</label> <input
													type="number" class="u-small-width bg-light mx-2" disabled
													>
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="artNome">Nome Articolo</label> <input type="text"
											name="artNome" class="u-full-width bg-light" required
											>
									</div>

									<div class="form-group">
										<label for="artDescr">Descrizione Articolo</label> <input
											type="text" name="artDescr" class="u-full-width bg-light"
											required >
									</div>


									<div class="row">
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artPrz">Prezzo Articolo</label> <input
													type="number" name="artPrz" class="u-small-width bg-light"
													required >
											</div>
										</div>

										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artSconto">Sconto Articolo</label> <input
													type="number" name="artSconto"
													class="u-small-width bg-light" required
													>
											</div>
										</div>

										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artStag">Stagione Articolo</label> <input
													type="text" name="artStag" class="u-small-width bg-light"
													required >
											</div>
										</div>
									</div>


									<div class="action-buttons">

										<button id="nuovo" name="action" value="nuovoArt" type="submit"
											class="btn btn-medium btn-dark">
											<span id="add-to-cart">Inserisci</span>
										</button>

									</div>

								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
	</section>


	<%@ include file="fragments/footer.jsp"%>
	<%@ include file="fragments/miniFooter.jsp"%>


	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/script.js"></script>
</body>
</html>

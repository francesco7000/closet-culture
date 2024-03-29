<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.*"%>

<%
	ArticoloBean art = new ArticoloBean();
UserBean currentUser = (UserBean) session.getAttribute("currentSessionUser");
if (currentUser == null) {
		//se l'utente è guest allora si deve prima registrare
		response.sendRedirect("errorPage.jsp");
}else{
	if (!currentUser.getRuolo().equals("admin")) {
		response.sendRedirect("errorPage.jsp");
	}
}
	ServletContext context = request.getServletContext();
	if(context==null ) response.sendRedirect("errorPage.jsp");
	ArrayList<CategoriaBean> categorie = (ArrayList<CategoriaBean>) context.getAttribute("categorie");

	
	if (categorie == null) {
		response.sendRedirect("errorPage.jsp");
	} else {
		//reset attributo per le prossime chiamate
		request.setAttribute("categorie", null);
	}

	ArrayList<LineaBean> linee = (ArrayList<LineaBean>) context.getAttribute("linee");

	ArrayList<MaterialeBean> materiali = (ArrayList<MaterialeBean>) context.getAttribute("materiali");

	if (linee == null || materiali == null) {
		response.sendRedirect("errorPage.jsp");

	} else {
		//reset linee
		request.setAttribute("linee", null);

		//reset materiali
		request.setAttribute("materiali", null);
	}
%>



<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin | Nuovo Articolo</title>
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
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>

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

	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>

	<section class="site-banner padding-small bg-light-grey">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumbs">
						<span class="item"> <a href="home.jsp">Home /</a>
						</span> <span class="item"> <a href="ricerca-prodotti.jsp">Shop
								/</a>
						</span> <span class="item">Nuovo Articolo</span>
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
										<label for="artActive">Articolo Attivo &nbsp; <input
											type="checkbox" name="artActive" value="true" checked>
										</label>
									</div>

									<div class="row">
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artCod">Codice Articolo</label> <input
													type="text" name="artCod" required>
											</div>
										</div>
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artBarCod">Barcode Articolo</label> <input
													type="text" name="artBarCod"
													class="u-medium-width bg-light mx-2" required>
											</div>
										</div>
										<div class="col-md-4 col-sm-12">

											<div class="form-group">
												<label for="artQta">Quantità Articolo</label> <input
													type="number" class="u-small-width bg-light mx-2" disabled>
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="artNome">Nome Articolo</label> <input type="text"
											name="artNome" class="u-full-width bg-light" required>
									</div>

									<div class="form-group">
										<label for="artDescr">Descrizione Articolo</label> <input
											type="text" name="artDescr" class="u-full-width bg-light"
											required>
									</div>


									<div class="row">
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artPrz">Prezzo Articolo</label> <input
													type="number" name="artPrz" class="u-small-width bg-light"
													required>
											</div>
										</div>

										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artSconto">Sconto Articolo</label> <input
													type="number" name="artSconto"
													class="u-small-width bg-light" required>
											</div>
										</div>

										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artStag">Stagione Articolo</label> <input
													type="text" name="artStag" class="u-small-width bg-light"
													required>
											</div>
										</div>
									</div>


									<div class="row">
										<div class="col-md-4 col-sm-12">
											<label for="artCat">Categoria Articolo</label> <select
												class="u-medium-width bg-light" name="artCat">

												<%
													if (categorie != null) {
														for (CategoriaBean categoria : categorie) {
												%>
												<option value="<%=categoria.getId()%>"
													<%=art.getCategoria() != null && art.getCategoria().getId() == categoria.getId() ? "selected"
							: ""%>><%=categoria.getDescrizione()%></option>
												<%
													}
													}
												%>
											</select>
										</div>

										<div class="col-md-4 col-sm-12">
											<label for="artLin">Linea Articolo</label> <select
												class="u-medium-width bg-light" name="artLin">

												<%
													if (linee != null) {
														for (LineaBean linea : linee) {
												%>
												<option value="<%=linea.getId()%>"
													<%=art.getLinea() != null && art.getLinea().getId() == linea.getId() ? "selected" : ""%>><%=linea.getDescrizione()%></option>
												<%
													}
													}
												%>
											</select>
										</div>

										<div class="col-md-4 col-sm-12">
											<label for="artMat">Materiale Articolo</label> <select
												class="u-medium-width bg-light" name="artMat">

												<%
													if (materiali != null) {
														for (MaterialeBean materiale : materiali) {
												%>
												<option value="<%=materiale.getId()%>"
													<%=art.getMateriale() != null && art.getMateriale().getId() == materiale.getId() ? "selected"
							: ""%>><%=materiale.getTipo()%></option>
												<%
													}
													}
												%>
											</select>
										</div>
									</div>

									<div class="action-buttons">

										<button id="nuovo" name="action" value="nuovoArt"
											type="submit" class="btn btn-medium btn-dark">
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

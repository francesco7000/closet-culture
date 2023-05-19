<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.*"%>

<%
UserBean user = (UserBean) request.getAttribute("profilo");
if (user == null) {
	//redirect alla servlet come parametro getCategorie per dirgli cosa deve fare
	response.sendRedirect("UserServlet?action=profilo");
} else {
	ServletContext context = request.getServletContext();
	context.setAttribute("profilo", user);
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
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

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

	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

	<section class="site-banner padding-small bg-light-grey">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumbs">
						<span class="item"> <a href="home.jsp">Home /</a>
						</span> <span class="item"> <a href="home.jsp">Utility
								/</a>
						</span> <span class="item">Modifica Profilo</span>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="single-product padding-large">
		<div class="container">
			<div class="row">
			
				<div class="col-md-7">
					<div class="product-info">
						<div class="element-header">
							<div class="tab-pane fade" id="nav-register" role="tabpanel"
								aria-labelledby="nav-register-tab">
								<form method="post" action="AdminServlet">

									
									<div class="row">
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label >Username</label> <input
													type="text" required
											name="username" value="<%=user.getUsername()%>" >
											</div>
										</div>
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artBarCod">Nome</label> <input
													type="text" name="nome"
													class="u-medium-width bg-light mx-2" required
												value="<%=user.getNome()%>" 	>
											</div>
										</div>
										<div class="col-md-4 col-sm-12">

											<div class="form-group">
												<label for="artQta">Cognome</label> <input
												value="<%=user.getCognome()%>"  name="cognome"	type="number" class="u-small-width bg-light mx-2" disabled
													>
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="artNome">Email</label> <input type="text"
										value="<%=user.getEmail()%>"	name="email" class="u-full-width bg-light" required
											>
									</div>

									<div class="form-group">
										<label for="artDescr">Cellulare</label> <input
										value="<%=user.getCellulare()%>"	type="text" name="cellulare" class="u-full-width bg-light"
											required >
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
